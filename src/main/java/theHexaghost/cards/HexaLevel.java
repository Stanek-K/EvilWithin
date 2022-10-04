package theHexaghost.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.powers.EnhancePower;
import theHexaghost.powers.HexalevelPower;

public class HexaLevel extends AbstractHexaCard {
    public final static String ID = makeID("HexaLevel");

    public HexaLevel() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseDownfallMagic = downfallMagic = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        tags.add(BaseModCardTags.FORM);
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) applyToSelf(new EnhancePower(downfallMagic));
        applyToSelf(new HexalevelPower(magicNumber));
    }
}