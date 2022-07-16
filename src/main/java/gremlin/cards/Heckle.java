package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.HecklePower;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class Heckle extends AbstractGremlinCard {
    public static final String ID = getID("Heckle");

    public Heckle() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HecklePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}

