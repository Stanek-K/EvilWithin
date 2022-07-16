package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;

public class Rhythm extends AbstractGremlinCard {
    public static final String ID = getID("Rhythm");

    public Rhythm() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new FetchAction(p.drawPile, 1));
        AbstractDungeon.actionManager.addToBottom(new GremlinSwapAction());
    }

    public void upp() {
        this.exhaust = false;
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

