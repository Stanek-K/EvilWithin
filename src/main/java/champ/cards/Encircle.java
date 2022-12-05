package champ.cards;

import champ.ChampMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class Encircle extends AbstractChampCard {
    public final static String ID = makeID("Encircle");

    public Encircle() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        isMultiDamage = true;
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_WHIRLWIND"));
        atb(new VFXAction(new WhirlwindEffect(), 0.0F));
        allDmg(AbstractGameAction.AttackEffect.NONE);

        for (AbstractMonster ignored : monsterList()) {
            techique();
        }
    }
}