package expansioncontent.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Predicate;

public class DrawSpecificCardsAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final Predicate<AbstractCard> conditions;
    /*
    Few examples of how predicates could look:
     Skill: c -> c.type == CardType.SKILL
     Ethereal: c -> c.isEthereal
     X-Cost: c -> c.cost == -1
     Unplayable: c -> c.cost == -2
     */
    public DrawSpecificCardsAction(int amount, Predicate<AbstractCard> predicate) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.conditions = predicate;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            //Create list of possible cards.
            ArrayList<AbstractCard> tmp = new ArrayList<>();
            for (AbstractCard c: p.drawPile.group) {
                if (conditions.test(c)) tmp.add(c);
                if (tmp.size() == amount) break;
            }

            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }

            //Draw part, I am not sure
            for (AbstractCard c: tmp) {
                if (this.p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                    this.p.createHandIsFullDialog();
                } else {
                    p.drawPile.group.remove(c);
                    p.drawPile.addToTop(c);
                    this.addToTop(new DrawCardAction(1));
                }
            }
            this.isDone = true;
        }
        this.tickDuration();
    }
}
