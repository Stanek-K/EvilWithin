package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.UnforgivingPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Unforgiving extends AbstractGremlinCard {
    public static final String ID = getID("Unforgiving");

    public Unforgiving() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UnforgivingPower(p));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}

