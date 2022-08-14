package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import gremlin.actions.NecromancyAction;
import gremlin.orbs.GremlinStandby;
import sneckomod.SneckoMod;

public class Necromancy extends AbstractGremlinCard {
    public static final String ID = getID("Necromancy");

    public Necromancy() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 10;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        setBackgrounds();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new NecromancyAction(magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if(!deadGrem()) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
            return false;
        } else if(!hasRoom()) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[2];
            return false;
        }
        return true;
    }

    private boolean deadGrem() {
        int count = 0;
        for(AbstractOrb orb : AbstractDungeon.player.orbs) {
            if(orb instanceof GremlinStandby) {
                count++;
            }
        }
        int total = 4;
        return (count != total);
    }

    private boolean hasRoom() {
        return AbstractDungeon.player.maxOrbs >= 4;
    }
}
