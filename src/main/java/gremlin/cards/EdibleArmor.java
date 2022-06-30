package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.EatArmorAction;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class EdibleArmor extends AbstractGremlinCard {
    public static final String ID = getID("EdibleArmor");

    public EdibleArmor() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EatArmorAction(p));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}

