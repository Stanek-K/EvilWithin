package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TailWhip extends AbstractSneckoCard {

    public final static String ID = makeID("TailWhip");

    public TailWhip() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        int x = getRandomNum(magicNumber, 2, this);
        if (x > 0)
            applyToEnemy(m, autoWeak(m, x));
        int y = getRandomNum(magicNumber, 2, this);
        if (y > 0)
            applyToEnemy(m, autoVuln(m, y));
        // atb(new MuddleHandAction());
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}