package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.actions.AdvanceAction;
import theHexaghost.actions.ChargeCurrentFlameAction;
import theHexaghost.actions.ExtinguishCurrentFlameAction;

public class FastForward extends AbstractHexaCard {
    public final static String ID = makeID("FastForward");

    public FastForward() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
        //atb(new ExtinguishCurrentFlameAction());
        //atb(new ChargeCurrentFlameAction());
        atb(new AdvanceAction(false));
    }
}