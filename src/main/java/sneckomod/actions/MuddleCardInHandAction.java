package sneckomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sneckomod.SneckoMod;

public class MuddleCardInHandAction extends AbstractGameAction {
    private static final String[] EXTENDED_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(SneckoMod.makeID("SoulExchange")).EXTENDED_DESCRIPTION;
    private final AbstractPlayer p;
    private final boolean no3;
    private final boolean upTo;

    public MuddleCardInHandAction(int amount, boolean no3) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.no3 = no3;
        this.upTo = false;
    }

    public MuddleCardInHandAction(int amount, boolean no3, boolean upTo) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.no3 = no3;
        this.upTo = upTo;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (amount == -1 && !upTo) {
                for (AbstractCard c : p.hand.group)
                    SneckoMod.muddleACard(c, no3);
                this.isDone = true;
                return;
            }

            if (upTo || this.p.hand.group.size() > amount) {
                AbstractDungeon.handCardSelectScreen.open(EXTENDED_DESCRIPTION[0], 1, false, false, false, false, upTo);
                this.tickDuration();
                return;
            } else {
                for (AbstractCard c : p.hand.group)
                    SneckoMod.muddleACard(c, no3);
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                SneckoMod.muddleACard(c, this.no3);
            }
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        this.tickDuration();
    }
}