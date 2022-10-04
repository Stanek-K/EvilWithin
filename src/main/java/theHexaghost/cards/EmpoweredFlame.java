package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.powers.EnhancePower;

public class EmpoweredFlame extends AbstractHexaCard {
    public final static String ID = makeID("EmpoweredFlame");

    public EmpoweredFlame() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnhancePower(magicNumber));
    }
}