package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;
import static gremlin.GremlinMod.getModID;

public class Astound extends AbstractGremlinCard {
    public static final String ID = getID("Astound");

    public Astound() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.baseMagicNumber = this.magicNumber = 3;
        this.cardsToPreview = new Ward();
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
        loadJokeCardImage(this, getModID(), "Astound.png");
    }

    public void upp(){
        upgradeBlock(2);
        upgradeMagicNumber(-1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.hasPower(WizPower.POWER_ID) && p.getPower(WizPower.POWER_ID).amount >= magicNumber) {
                    AbstractCard c = new Ward();
                    makeInHand(c);
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(WizPower.POWER_ID) && AbstractDungeon.player.getPower(WizPower.POWER_ID).amount >= magicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}

