package gremlin.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WhizAction extends AbstractGameAction {
    public static final String[] TEXT = { "test", " hardcoded", " no", " errors", " please"};
    private final AbstractPlayer player;
    private final int numberOfCards;

    public WhizAction(int numberOfCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.drawPile.isEmpty() && this.numberOfCards > 0) {
                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for (AbstractCard c : this.player.drawPile.group) {
                    temp.addToTop(c);
                }

                temp.sortAlphabetically(true);
                temp.sortByRarityPlusStatusCardType(false);
                if (this.numberOfCards == 1) {
                    AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[0], false);
                } else {
                    AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                }

                this.tickDuration();
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    this.player.drawPile.removeCard(c);
                    this.player.drawPile.addToTop(c);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            this.tickDuration();
        }
    }
}
