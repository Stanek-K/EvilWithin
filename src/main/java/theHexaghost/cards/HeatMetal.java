package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.powers.BurnPerTurnPower;

public class HeatMetal extends AbstractHexaCard {
    public final static String ID = makeID("HeatMetal");

    public HeatMetal() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        applyToEnemy(m, autoVuln(m, magicNumber));
    }
}