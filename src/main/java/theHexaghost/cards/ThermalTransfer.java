package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import theHexaghost.powers.BurnPower;

public class ThermalTransfer extends AbstractHexaCard {
    public final static String ID = makeID("ThermalTransfer");

    public ThermalTransfer() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseBlock = 6;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FireballEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (m.hasPower(BurnPower.POWER_ID)) {
            atb(new GainBlockAction(p, block));
            atb(new VFXAction(new FireballEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        burnGlowCheck();
    }
}