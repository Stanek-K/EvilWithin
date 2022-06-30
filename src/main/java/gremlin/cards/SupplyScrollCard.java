package gremlin.cards;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static gremlin.relics.SupplyScroll.SUPPLY;

public class SupplyScrollCard extends AbstractGremlinCard {
    public static final String ID = "gremlin:SupplyScrollCard";

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> cardsList = new ArrayList<>();

    public SupplyScrollCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);

        this.baseMagicNumber = this.magicNumber = SUPPLY;
        this.exhaust = true;
        this.cardsToPreview = new Shiv();
        cardsList.add(new Shiv());
        cardsList.add(new Ward());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++){
            int shiv = AbstractDungeon.cardRandomRng.random(0, 1);
            if (shiv == 1)
                makeInHand(new Shiv(), 1);
            else
                makeInHand(new Ward(), 1);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
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
}

