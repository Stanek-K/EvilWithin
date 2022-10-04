package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.BurnPower;
import theHexaghost.vfx.HeatCrushEffect;

public class HeatCrush extends AbstractHexaCard {
    public final static String ID = makeID("HeatCrush");

    public HeatCrush() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 20;
        HexaMod.loadJokeCardImage(this, "HeatCrush.png");
    }

    public void upp(){
        upgradeDamage(10);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (mo.hasPower(BurnPower.POWER_ID))
            baseDamage += mo.getPower(BurnPower.POWER_ID).amount;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new HeatCrushEffect(m.hb.cX, m.hb.cY)));// 37
        }
        this.addToBot(new WaitAction(0.8F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }
}