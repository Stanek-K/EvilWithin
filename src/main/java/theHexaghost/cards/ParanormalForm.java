package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.powers.ParanormalFormPower;

public class ParanormalForm extends AbstractHexaCard {
    public final static String ID = makeID("ParanormalForm");

    public ParanormalForm() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ParanormalFormPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}