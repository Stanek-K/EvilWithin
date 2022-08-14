package gremlin.cards;

import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Regroup extends AbstractGremlinCard {
    public static String ID = getID("Regroup");

    public Regroup() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DiscardPileToTopOfDeckAction(p));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
