package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.actions.ChargeCurrentFlameAction;
import theHexaghost.actions.ExtinguishCurrentFlameAction;

public class SkipABeat extends AbstractHexaCard {
    public final static String ID = makeID("SkipABeat");

    public SkipABeat() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded){
            if (!GhostflameHelper.activeGhostFlame.charged)  atb(new ChargeCurrentFlameAction());
        } else {
            atb(new ExtinguishCurrentFlameAction());
            atb(new ChargeCurrentFlameAction());
        }
    }
}