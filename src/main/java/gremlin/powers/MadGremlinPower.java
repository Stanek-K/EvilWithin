package gremlin.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class MadGremlinPower extends GremlinPower {
    static final String POWER_ID = getID("MadGremlin");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public MadGremlinPower(int amount) {
        super();
        this.pot = amount;
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = (strings.DESCRIPTIONS[0] + this.pot + strings.DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.pot), this.pot));
            addToBot(new ApplyPowerAction(owner, owner,  new LoseStrengthPower(owner, this.pot), this.pot));
        }
    }
}
