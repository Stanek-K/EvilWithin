package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.actions.ChargeAction;
import theHexaghost.actions.ExtinguishAction;
import theHexaghost.ghostflames.AbstractGhostflame;

public class UnlimitedPower extends AbstractHexaCard {
    public final static String ID = makeID("UnlimitedPower");

    public UnlimitedPower() {
        super(ID, 5, CardType.SKILL, CardRarity.RARE, CardTarget.ALL);
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeBaseCost(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractGhostflame gf : GhostflameHelper.hexaGhostFlames) {
            atb(new ExtinguishAction(gf));
            atb(new ChargeAction(gf));
        }
    }
}