package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.orbs.FatGremlin;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class BulkUp extends AbstractGremlinCard {
    public static final String ID = getID("BulkUp");

    public BulkUp() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, this.magicNumber));
        atb(new GremlinSwapAction(new FatGremlin(0)));
    }

    public void upp(){
        upgradeMagicNumber(2);
    }
}

