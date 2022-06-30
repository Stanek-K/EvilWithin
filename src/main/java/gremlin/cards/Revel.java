package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import gremlin.GremlinMod;
import gremlin.orbs.GremlinStandby;
import sneckomod.SneckoMod;

public class Revel extends AbstractGremlinCard {
    public static final String ID = getID("Revel");

    public Revel() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        GremlinMod.loadJokeCardImage(this, "Revel.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 1;
        for(AbstractOrb orb : p.orbs){
            if(orb instanceof GremlinStandby)
                amount += 1;
        }
        atb(new GainEnergyAction(amount));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}

