package gremlin.cards;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gremlin.characters.GremlinCharacter;
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

    public GremlinDance() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 6;
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

        this.baseDamage = 6;
        this.baseBlock = 4;
        this.baseMagicNumber = this.magicNumber = 2;
        this.gremlin = gremlin;
        updateContents();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String gremlin = "";
        boolean isNob = false;
        if(AbstractDungeon.player instanceof GremlinCharacter) {
            if(((GremlinCharacter) AbstractDungeon.player).nob){
                isNob = true;
            } else {
                gremlin = ((GremlinCharacter) AbstractDungeon.player).currentGremlin;
            }
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

        if(gremlin.equals("fat")){
            applyToEnemy(m, new StrengthPower(m, -this.magicNumber));
            if (m != null && !m.hasPower("Artifact")) {
                applyToEnemy(m, new GainStrengthPower(m, this.magicNumber));
            }
        }

        if(gremlin.equals("sneak")){
            atb(new DrawCardAction(p, this.magicNumber));
        }

        if(gremlin.equals("wizard")){
            applyToSelf(new WizPower(p, this.magicNumber));
        }

        if(isNob){
            applyToSelf(new StrengthPower(p, this.magicNumber));
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

    public AbstractCard makeCopy()
    {
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
        this.tags.remove(NOB_GREMLIN);
        this.rawDescription = EXTENDED_DESCRIPTION[0];
        if(!this.gremlin.equals("") || AbstractDungeon.player instanceof GremlinCharacter){
            if(this.gremlin.equals("") && ((GremlinCharacter) AbstractDungeon.player).nob){
                rawDescription += EXTENDED_DESCRIPTION[6];
                this.isMultiDamage = false;
                this.target = CardTarget.ENEMY;
                this.wizardry = false;
                this.tags.add(NOB_GREMLIN);
                setBackgrounds();
            } else {
                String gremlin = this.gremlin;
                if (gremlin.equals("")) {
                    gremlin = ((GremlinCharacter) AbstractDungeon.player).currentGremlin;
                }
                switch (gremlin) {
                    case "angry":
                        rawDescription += EXTENDED_DESCRIPTION[1];
                        this.isMultiDamage = true;
                        this.target = CardTarget.ALL_ENEMY;
                        this.wizardry = false;
                        this.tags.add(MAD_GREMLIN);
                        setBackgrounds();
                        break;
                    case "fat":
                        rawDescription += EXTENDED_DESCRIPTION[2];
                        this.isMultiDamage = false;
                        this.target = CardTarget.ENEMY;
                        this.wizardry = false;
                        this.tags.add(FAT_GREMLIN);
                        setBackgrounds();
                        break;
                    case "shield":
                        rawDescription += EXTENDED_DESCRIPTION[3];
                        this.isMultiDamage = false;
                        this.target = CardTarget.ENEMY;
                        this.wizardry = false;
                        this.tags.add(SHIELD_GREMLIN);
                        setBackgrounds();
                        break;
                    case "sneak":
                        rawDescription += EXTENDED_DESCRIPTION[4];
                        this.isMultiDamage = false;
                        this.target = CardTarget.ENEMY;
                        this.wizardry = false;
                        this.tags.add(SNEAKY_GREMLIN);
                        setBackgrounds();
                        break;
                    case "wizard":
                        rawDescription += EXTENDED_DESCRIPTION[5];
                        this.isMultiDamage = false;
                        this.target = CardTarget.ENEMY;
                        this.wizardry = false;
                        this.tags.add(WIZARD_GREMLIN);
                        setBackgrounds();
                        break;
                }
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
