package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.BubbleBarrierPower;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class BubbleBarrier extends AbstractGremlinCard {
    public static final String ID = getID("BubbleBarrier");

    public BubbleBarrier() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BubbleBarrierPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}