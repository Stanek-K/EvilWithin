package sneckomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import sneckomod.SneckoMod;

public class SoulExchangeAction extends AbstractGameAction {
    private static final String[] EXTENDED_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(SneckoMod.makeID("SoulExchange")).EXTENDED_DESCRIPTION;
    private final AbstractPlayer p;

    public SoulExchangeAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.group.size() > 2) {
                AbstractDungeon.handCardSelectScreen.open(EXTENDED_DESCRIPTION[0], 2, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 2) {
                swapCosts(p.hand.getTopCard(), p.hand.getNCardFromTop(1));
                this.isDone = true;
            }

            if (this.p.hand.group.size() < 2) {
                this.isDone = true;
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            swapCosts(AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0), AbstractDungeon.handCardSelectScreen.selectedCards.group.get(1));
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        this.tickDuration();
    }

    private void swapCosts(AbstractCard card1, AbstractCard card2) {
        int cc1 = getCurrentCost(card1),
            cc2 = getCurrentCost(card2);
        setCost(card1, cc2);
        setCost(card2, cc1);
    }

    private int getCurrentCost(AbstractCard card) {
        int cost;
        if (card.freeToPlayOnce) cost = 0;
        else if (card.cost == -1) cost = EnergyPanel.getCurrentEnergy();
        else cost = card.costForTurn;
        return cost;
    }

    private void setCost(AbstractCard card, int cost) {
        if (card.cost >= 0) {
            card.cost = card.costForTurn = cost;
            card.freeToPlayOnce = false;
        }
    }
}
