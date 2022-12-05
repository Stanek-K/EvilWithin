package champ.cards;

import champ.ChampMod;
import champ.actions.ModifyDamageAndBlockAction;
import champ.actions.PreciseThrustAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PreciseThrust extends AbstractChampCard {
    public final static String ID = makeID("PreciseThrust");

    public PreciseThrust() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseBlock = 6;
        tags.add(ChampMod.COMBO);
        tags.add(ChampMod.COMBODEFENSIVE);
        tags.add(ChampMod.COMBOBERSERKER);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (bcombo()) dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (dcombo()) blck();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = (bcombo() || dcombo()) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}