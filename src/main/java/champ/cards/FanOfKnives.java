package champ.cards;

import champ.ChampMod;
import champ.vfx.DaggerSprayAnyColorEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.loadJokeCardImage;

public class FanOfKnives extends AbstractChampCard {
    public final static String ID = makeID("FanOfKnives");

    public FanOfKnives() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 5;
        isMultiDamage = true;
        tags.add(ChampMod.COMBO);
        tags.add(ChampMod.COMBOBERSERKER);
        loadJokeCardImage(this, "FanOfKnives.png");
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new DaggerSprayAnyColorEffect(AbstractDungeon.getMonsters().shouldFlipVfx(), Color.FIREBRICK), 0.0F));
        allDmg(AbstractGameAction.AttackEffect.NONE);

        if (bcombo()) {
            atb(new VFXAction(new DaggerSprayAnyColorEffect(AbstractDungeon.getMonsters().shouldFlipVfx(), Color.RED), 0.0F));
            allDmg(AbstractGameAction.AttackEffect.NONE);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = bcombo() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}