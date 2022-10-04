package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.actions.RecurringNightmareAction;

public class RecurringNightmare extends AbstractHexaCard {
    public final static String ID = makeID("RecurringNightmare");

    public RecurringNightmare() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
        tags.add(CardTags.HEALING);
    }

    public void upp() {
        upgradeMagicNumber(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RecurringNightmareAction(magicNumber));
    }
}