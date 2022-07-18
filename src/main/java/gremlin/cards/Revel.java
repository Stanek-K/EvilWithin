package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;

public class Revel extends AbstractGremlinCard {
    public static final String ID = getID("Revel");

    public Revel() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        loadJokeCardImage(this, modID, "Revel.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void triggerWhenDrawn() {
        att(new GainEnergyAction(1));
        if (upgraded) att(new DrawCardAction(1));
    }

    public void upp() {
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

