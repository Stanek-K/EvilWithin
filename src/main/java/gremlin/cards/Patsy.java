package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;

public class Patsy extends AbstractGremlinCard {
    public static final String ID = getID("Patsy");

    public Patsy() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new GremlinSwapAction());
    }

    public void upp() {
        upgradeBlock(3);
    }
}

