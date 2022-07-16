package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.BangPower;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Stupend extends AbstractGremlinCard {
    public static final String ID = getID("Stupend");

    public Stupend() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseBlock = 8;
        this.baseMagicNumber = this.magicNumber = 3;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.hasPower(WizPower.POWER_ID) && p.getPower(WizPower.POWER_ID).amount >= magicNumber) {
                    blck();
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}