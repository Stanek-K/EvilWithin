package champ.cards;

import champ.powers.StrikeOfGeniusPower;
import champ.powers.StrikeOfGeniusUpgradedPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

public class StrikeOfGenius extends AbstractChampCard {
    public final static String ID = makeID("StrikeOfGenius");

    public StrikeOfGenius() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        tags.add(CardTags.STRIKE);
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
            applyToSelf(new StrikeOfGeniusUpgradedPower(1));
        else
            applyToSelf(new StrikeOfGeniusPower(1));
    }
}