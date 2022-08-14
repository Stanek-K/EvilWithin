package gremlin.powers;

import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class SneakyGremlinPower extends GremlinPower {
    static final String POWER_ID = getID("SneakyGremlin");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public SneakyGremlinPower(int amount) {
        super();
        this.pot = amount;
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = (strings.DESCRIPTIONS[0] + 1 + strings.DESCRIPTIONS[1]);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {
            this.addToBot(new RetainCardsAction(this.owner, pot));
        }
    }
}
