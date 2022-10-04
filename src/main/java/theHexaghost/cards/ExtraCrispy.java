package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.powers.CrispyPower;

public class ExtraCrispy extends AbstractHexaCard {
    public final static String ID = makeID("ExtraCrispy");

    public ExtraCrispy() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void upp() {
        upgradeMagicNumber(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CrispyPower(magicNumber));
    }
}