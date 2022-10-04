package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShieldOfDarkness extends AbstractHexaCard {
    public final static String ID = makeID("ShieldOfDarkness");

    public ShieldOfDarkness() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 3;
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ScryAction(magicNumber));
    }
}