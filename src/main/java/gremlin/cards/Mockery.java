package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.MockeryAction;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class Mockery extends AbstractGremlinCard {
    public static final String ID = getID("Mockery");

    public Mockery() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseBlock = 10;
        this.baseMagicNumber = this.magicNumber = 1;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, magicNumber));
        atb(new MockeryAction(m, p, block));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}

