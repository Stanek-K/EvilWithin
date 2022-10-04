package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.HexaMod;
import theHexaghost.actions.ChargeAction;
import theHexaghost.actions.ExtinguishAction;

public class CatchUp extends AbstractHexaCard {
    public final static String ID = makeID("CatchUp");

    public CatchUp() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        this.exhaust = false;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new ExtinguishAction(GhostflameHelper.getPreviousGhostFlame()));
            atb(new ChargeAction(GhostflameHelper.getPreviousGhostFlame()));
        }
    }
}