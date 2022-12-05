package champ.cards;

import champ.powers.IronFortressPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IronFortress extends AbstractChampCard {
    public final static String ID = makeID("IronFortress");

    public IronFortress() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void upp() {
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IronFortressPower(1));
    }
}