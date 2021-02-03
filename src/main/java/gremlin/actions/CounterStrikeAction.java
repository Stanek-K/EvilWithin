package gremlin.actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import gremlin.cards.Ward;

public class CounterStrikeAction extends AbstractGameAction
{
    private AbstractMonster m;
    private boolean isUpgraded;

    public CounterStrikeAction(final AbstractMonster m, final int amount, boolean isUpgraded) {
        this.actionType = ActionType.WAIT;
        this.amount = amount;
        this.m = m;
        this.isUpgraded = isUpgraded;
    }

    @Override
    public void update() {
        if (this.m != null && (this.m.intent == AbstractMonster.Intent.ATTACK || this.m.intent == AbstractMonster.Intent.ATTACK_BUFF || this.m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || this.m.intent == AbstractMonster.Intent.ATTACK_DEFEND)) {
            AbstractCard c = new Ward();
            if(isUpgraded){
                c.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, amount));
        }
        this.isDone = true;
    }
}

