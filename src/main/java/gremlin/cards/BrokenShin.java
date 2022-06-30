package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import gremlin.powers.AgonyPower;
import sneckomod.SneckoMod;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class BrokenShin extends AbstractGremlinCard {
    public static final String ID = getID("BrokenShin");

    public BrokenShin() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 4;
        this.exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.hasPower(WeakPower.POWER_ID)){
            int amount = m.getPower(WeakPower.POWER_ID).amount / this.magicNumber;
//            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "Weakened"));
            if(amount > 0){
                applyToEnemy(m, new AgonyPower(m, amount, false));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(-1);
    }
}

