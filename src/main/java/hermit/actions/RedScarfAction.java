package hermit.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.purple.Omniscience;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.watcher.WaveOfTheHandPower;
import hermit.patches.WaveOfTheHandPatch;

import java.util.Iterator;

public class RedScarfAction extends AbstractGameAction {
    private AbstractPlayer p;

    public RedScarfAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            
            if (AbstractDungeon.player.hasPower(WaveOfTheHandPower.POWER_ID)) {
                WaveOfTheHandPatch.isActive = 1;
            }
            this.addToTop(new GainBlockAction(p, p, amount));
            this.isDone = true;
        }

        this.tickDuration();
    }
}