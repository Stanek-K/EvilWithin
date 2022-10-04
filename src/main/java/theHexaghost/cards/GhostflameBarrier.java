package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.GhostFlameBarrierPower;
import theHexaghost.vfx.SpookyFlameBarrier;

public class GhostflameBarrier extends AbstractHexaCard {
    public final static String ID = makeID("GhostflameBarrier");

    public GhostflameBarrier() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 6;
        HexaMod.loadJokeCardImage(this, "GhostflameBarrier.png");
    }

    public void upp() {
        upgradeBlock(4);
        upgradeMagicNumber(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {// 38
            this.addToBot(new VFXAction(p, new SpookyFlameBarrier(p.hb.cX, p.hb.cY), 0.1F));// 39
        } else {
            this.addToBot(new VFXAction(p, new SpookyFlameBarrier(p.hb.cX, p.hb.cY), 0.5F));// 41
        }
        blck();
        applyToSelf(new GhostFlameBarrierPower(magicNumber));
    }
}