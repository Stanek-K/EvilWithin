package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Astound extends AbstractGremlinCard {
    public static final String ID = getID("Astound");

    public Astound() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Ward();
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
        GremlinMod.loadJokeCardImage(this, "Astound.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.hasPower(WizPower.POWER_ID) && p.getPower(WizPower.POWER_ID).amount >= 3) {
                    AbstractCard c = new Ward();
                    if (upgraded)
                        c.upgrade();
                    makeInHand(c, magicNumber);
                }
                this.isDone = true;
            }
        });
    }

    public void upp(){
        upgradeBlock(2);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        AbstractCard c = new Ward();
        c.upgrade();
        this.cardsToPreview = c;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(WizPower.POWER_ID) && AbstractDungeon.player.getPower(WizPower.POWER_ID).amount >= 3) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}

