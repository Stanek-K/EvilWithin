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
        if (this.upgraded) atb(new GremlinSwapAction());
    }

    @Override
    public void triggerWhenDrawn() {
        if (!this.upgraded) att(new GremlinSwapAction());
    }

    public void upp() {
        upgradeBlock(1);
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

