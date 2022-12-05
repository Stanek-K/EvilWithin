package sneckomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import sneckomod.SneckoMod;


public class MuddleAction extends AbstractGameAction {
    private final AbstractCard card;
    private final boolean no3;

    public MuddleAction(AbstractCard bruhCard, boolean no3cost) {
        card = bruhCard;
        this.no3 = no3cost;
    }

    public MuddleAction(AbstractCard bruhCard) {
        this(bruhCard, false);
    }

    public void update() {
        isDone = true;
        SneckoMod.muddleACard(card, no3);
    }
}
