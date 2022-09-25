package sneckomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sneckomod.SneckoMod;

public class MuddleDrawnCardsFollowUpAction extends AbstractGameAction {
    //Use as argument to DrawCardAction
    public MuddleDrawnCardsFollowUpAction() {
        this.duration = 0.001F;
    }

    public void update() {
        this.tickDuration();
        if (this.isDone) {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (!c.hasTag(SneckoMod.SNEKPROOF)) {
                    AbstractDungeon.actionManager.addToTop(new MuddleAction(c));
                }
            }
        }
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));

    }
}
