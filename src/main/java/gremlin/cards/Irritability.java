package gremlin.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;
import gremlin.actions.GremlinSwapAction;
import gremlin.orbs.MadGremlin;
import guardian.powers.LoseThornsPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Irritability extends AbstractGremlinCard {
    public static final String ID = getID("Irritability");

    public Irritability() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber =  this.magicNumber = 3;
        this.baseBlock = 6;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FlameAnimationEffect(p.hb)));
        blck();
        applyToSelf(new ThornsPower(p, this.magicNumber));
        applyToSelf(new LoseThornsPower(p, this.magicNumber));
        atb(new GremlinSwapAction(new MadGremlin(0)));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(2);
    }
}

