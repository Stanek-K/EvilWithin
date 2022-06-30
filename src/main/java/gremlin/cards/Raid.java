package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import gremlin.orbs.GremlinStandby;
import sneckomod.SneckoMod;

public class Raid extends AbstractGremlinCard {
    public static final String ID = getID("Raid");

    public Raid() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 1;
        for(AbstractOrb orb : p.orbs){
            if(orb instanceof GremlinStandby)
                amount += 1;
        }
        atb(new DrawCardAction(p, amount));
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(-1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

