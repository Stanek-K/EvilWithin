package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;

public class Rhythm extends AbstractGremlinCard {
    public static final String ID = getID("Rhythm");

    public Rhythm() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GremlinSwapAction());
        AbstractDungeon.actionManager.addToBottom(new FetchAction(p.drawPile, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}

