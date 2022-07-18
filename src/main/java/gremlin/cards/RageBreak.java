package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class RageBreak extends AbstractGremlinCard {
    public static final String ID = getID("RageBreak");

    public RageBreak() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseDownfallMagic = downfallMagic = 2;
        this.exhaust = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FlameAnimationEffect(p.hb)));
        applyToSelf(new StrengthPower(p, this.magicNumber));
    }

    public void triggerWhenDrawn() {
        att(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, this.downfallMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeDownfall(1);
    }
}

