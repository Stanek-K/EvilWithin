package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.actions.ChargeCurrentFlameAction;
import theHexaghost.ghostflames.AbstractGhostflame;
import theHexaghost.ghostflames.InfernoGhostflame;

public class GhostflameInferno extends AbstractHexaCard {
    public final static String ID = makeID("GhostflameInferno");

    public GhostflameInferno() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        selfRetain = true;
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) atb(new ChargeCurrentFlameAction());
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractGhostflame gf : GhostflameHelper.hexaGhostFlames) {
                    if (gf instanceof InfernoGhostflame) {
                        gf.forceCharge();
                    }
                }
            }
        });
    }
}