package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class Mockery extends AbstractGremlinCard {
    public static final String ID = getID("Mockery");

    public Mockery() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseBlock = 5;
        this.baseMagicNumber = this.magicNumber = 1;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoVuln(m, magicNumber));
        blck();
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}

