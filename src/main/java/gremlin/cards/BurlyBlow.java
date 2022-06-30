package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class BurlyBlow extends AbstractGremlinCard {
    public static final String ID = getID("BurlyBlow");

    public BurlyBlow() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 13;
        this.baseMagicNumber = this.magicNumber = 3;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int trueBaseDamage = baseDamage;
        if (mo.hasPower(WeakPower.POWER_ID)) {
            baseDamage += mo.getPower(WeakPower.POWER_ID).amount * magicNumber;
        }
        super.calculateCardDamage(mo);
        baseDamage = trueBaseDamage;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}