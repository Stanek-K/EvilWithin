package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.AgonyPower;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class BrokenShin extends AbstractGremlinCard {
    public static final String ID = getID("BrokenShin");

    public BrokenShin() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, this.magicNumber));
        applyToEnemy(m, new AgonyPower(m, p));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}

