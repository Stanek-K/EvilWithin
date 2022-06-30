package gremlin.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.LimitBreakAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class RageBreak extends AbstractGremlinCard {
    public static final String ID = getID("RageBreak");

    public RageBreak() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.exhaust = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FlameAnimationEffect(p.hb)));
        if (this.upgraded)
            blck();
        atb(new LimitBreakAction());
    }

    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

