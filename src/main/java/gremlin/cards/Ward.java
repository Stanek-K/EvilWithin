package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;

public class Ward extends AbstractGremlinCard {
    public static final String ID = "gremlin:Ward";

    public Ward() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        this.baseBlock = 3;
        this.exhaust = true;
        GremlinMod.loadJokeCardImage(this, "Ward.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(2);
    }
}