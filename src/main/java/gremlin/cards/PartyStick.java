package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;

public class PartyStick extends AbstractGremlinCard {
    public static final String ID = getID("PartyStick");

    public PartyStick() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded)
            atb(new GainEnergyAction(1));
        else
            atb(new GainEnergyAction(2));
        atb(new GremlinSwapAction());
    }

    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

