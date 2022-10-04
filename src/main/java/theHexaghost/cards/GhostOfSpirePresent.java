package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.powers.GainEnergyAtTheStartOfTurnPower;
import theHexaghost.powers.StopFromAdvancingPower;

public class GhostOfSpirePresent extends AbstractHexaCard {
    public final static String ID = makeID("GhostOfSpirePresent");

    public GhostOfSpirePresent() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GainEnergyAtTheStartOfTurnPower(1));
        applyToSelf(new StopFromAdvancingPower());
    }
}