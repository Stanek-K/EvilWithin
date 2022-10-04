package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.powers.ApocalypticArmorPower;

public class ApocalypticArmor extends AbstractHexaCard {
    public final static String ID = makeID("ApocalypticArmor");

    public ApocalypticArmor() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeMagicNumber(-1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ApocalypticArmorPower(magicNumber));
    }
}