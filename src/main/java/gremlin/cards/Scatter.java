package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.ScatterPower;
import sneckomod.SneckoMod;

public class Scatter extends AbstractGremlinCard {
    public static final String ID = getID("Scatter");

    public Scatter() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ScatterPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}

