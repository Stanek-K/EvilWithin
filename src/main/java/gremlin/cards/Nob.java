package gremlin.cards;

import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;
import gremlin.powers.GremlinNobPower;
import sneckomod.SneckoMod;

import java.util.ArrayList;

import static gremlin.GremlinMod.NOB_GREMLIN;

public class Nob extends AbstractGremlinCard implements SpawnModificationCard {
    public static final String ID = getID("Nob");

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> cardsList = new ArrayList<>();

    public Nob() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 20;

        cardsList.add(new Bellow());
        cardsList.add(new SkullBash());
        cardsList.add(new Rush());

        this.tags.add(BaseModCardTags.FORM);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(NOB_GREMLIN);
        setBackgrounds();
        GremlinMod.loadJokeCardImage(this, "Nob.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, magicNumber));
        applyToSelf(new GremlinNobPower(1));
    }

    public void upp() {
        upgradeMagicNumber(10);
    }

    @Override
    public void update() {
        super.update();
        if (hb.hovered) {
            if (rotationTimer <= 0F) {
                rotationTimer = 2F;
                if (cardsList.size() == 0) {
                    cardsToPreview = CardLibrary.cards.get("Madness");
                } else {
                    cardsToPreview = cardsList.get(previewIndex);
                }
                if (previewIndex == cardsList.size() - 1) {
                    previewIndex = 0;
                } else {
                    previewIndex++;
                }
            } else {
                rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    // Fun Fact: NOB has a 50% chance to actually spawn.
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        return AbstractDungeon.cardRng.randomBoolean();
    }
}

