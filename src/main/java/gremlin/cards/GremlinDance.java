package gremlin.cards;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinCharacter;
import gremlin.powers.WizPower;
import sneckomod.SneckoMod;

import java.util.ArrayList;

import static gremlin.GremlinMod.*;

public class GremlinDance extends AbstractGremlinCard {
    public static final String ID = getID("GremlinDance");

    private String gremlin;
    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> cardsList = new ArrayList<>();

    public static int WEAK_AMOUNT = 1;
    public static int WIZ_AMOUNT = 2;
    public static int DRAW_AMOUNT = 2;

    public GremlinDance() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.baseBlock = 4;
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);

        this.gremlin = "";
        cardsList.add(new GremlinDance("angry"));
        cardsList.add(new GremlinDance("fat"));
        cardsList.add(new GremlinDance("shield"));
        cardsList.add(new GremlinDance("sneak"));
        cardsList.add(new GremlinDance("wizard"));
    }

    public GremlinDance(String gremlin) {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);

        this.baseDamage = 5;
        this.baseBlock = 4;
        this.baseMagicNumber = this.magicNumber = 2;
        this.gremlin = gremlin;
        updateContents();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String gremlin = "";
        if(AbstractDungeon.player instanceof GremlinCharacter) {
            gremlin = ((GremlinCharacter) AbstractDungeon.player).currentGremlin;
        }

        if(gremlin.equals("shield")){
            blck();
        }

        if(gremlin.equals("angry")){
            allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        } else if(gremlin.equals("wizard")){
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        } else {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }

        switch (gremlin) {
            case "fat":
                applyToEnemy(m, autoWeak(m, this.magicNumber));
                break;
            case "sneak":
                atb(new DrawCardAction(p, this.magicNumber));
                break;
            case "wizard":
                applyToSelf(new WizPower(p, this.magicNumber));
                break;
        }
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

    public AbstractCard makeCopy() {
        if (this.gremlin.equals("")) {
            return new GremlinDance();
        } else {
            return new GremlinDance(this.gremlin);
        }
    }

    @Override
    public void applyPowers() {
        updateContents();
        super.applyPowers();
    }

    private void updateContents(){
        this.tags.remove(MAD_GREMLIN);
        this.tags.remove(FAT_GREMLIN);
        this.tags.remove(SHIELD_GREMLIN);
        this.tags.remove(SNEAKY_GREMLIN);
        this.tags.remove(WIZARD_GREMLIN);
        this.rawDescription = EXTENDED_DESCRIPTION[0];
        if(!this.gremlin.equals("") || AbstractDungeon.player instanceof GremlinCharacter) {
            String gremlin = this.gremlin;
            if (gremlin.equals("")) {
                gremlin = ((GremlinCharacter) AbstractDungeon.player).currentGremlin;
            }
            switch (gremlin) {
                case "angry":
                    rawDescription += EXTENDED_DESCRIPTION[1];
                    this.isMultiDamage = true;
                    this.target = CardTarget.ALL_ENEMY;
                    this.tags.add(MAD_GREMLIN);
                    setBackgrounds();
                    break;
                case "fat":
                    rawDescription += EXTENDED_DESCRIPTION[2];
                    this.baseMagicNumber = this.magicNumber = WEAK_AMOUNT;
                    this.isMultiDamage = false;
                    this.target = CardTarget.ENEMY;
                    this.tags.add(FAT_GREMLIN);
                    setBackgrounds();
                    break;
                case "shield":
                    rawDescription += EXTENDED_DESCRIPTION[3];
                    this.isMultiDamage = false;
                    this.target = CardTarget.ENEMY;
                    this.tags.add(SHIELD_GREMLIN);
                    setBackgrounds();
                    break;
                case "sneak":
                    rawDescription += EXTENDED_DESCRIPTION[4];
                    this.baseMagicNumber = this.magicNumber = DRAW_AMOUNT;
                    this.isMultiDamage = false;
                    this.target = CardTarget.ENEMY;
                    this.tags.add(SNEAKY_GREMLIN);
                    setBackgrounds();
                    break;
                case "wizard":
                    rawDescription += EXTENDED_DESCRIPTION[5];
                    this.baseMagicNumber = this.magicNumber = WIZ_AMOUNT;
                    this.isMultiDamage = false;
                    this.target = CardTarget.ENEMY;
                    this.tags.add(WIZARD_GREMLIN);
                    setBackgrounds();
                    break;
            }
        }
        initializeDescription();
    }

    @Override
    public void onGremlinSwapInDeck() {
        updateContents();
        super.applyPowers();
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
