package gremlin.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

public class SneakyAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final int damageIncrease;

    public SneakyAction(AbstractCreature source, int amountOfCards, int damageIncrease) {
        this.setValues(AbstractDungeon.player, source, amountOfCards);
        this.damageIncrease = damageIncrease;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
            this.addToBot(new WaitAction(0.25F));
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator<AbstractCard> var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); AbstractDungeon.player.hand.addToTop(c)) {
                    c = var1.next();
                    if (!c.isEthereal) c.retain = true;
                    if (c.type == AbstractCard.CardType.ATTACK) c.baseDamage += damageIncrease;
                    if (c.baseDamage < 0) c.baseDamage = 0;
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
        TEXT = uiStrings.TEXT;
    }
}
