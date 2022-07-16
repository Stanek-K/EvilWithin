package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;

public class Patsy extends AbstractGremlinCard {
    public static final String ID = getID("Patsy");

    public Patsy() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerWhenDrawn() {
        att(new GremlinSwapAction());
    }

    public void upp() {
        upgradeBlock(3);
    }
}

