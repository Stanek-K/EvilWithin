package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.HexaMod;
import theHexaghost.actions.RetractAction;
import theHexaghost.powers.PastPower;

public class GhostOfSpirePast extends AbstractHexaCard {
    public final static String ID = makeID("GhostOfSpirePast");

    public GhostOfSpirePast() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        HexaMod.loadJokeCardImage(this, "GhostOfSpirePast.png");
    }

    public void upp() {
        upgradeBaseCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PastPower(1));
        atb(new RetractAction());
    }
}