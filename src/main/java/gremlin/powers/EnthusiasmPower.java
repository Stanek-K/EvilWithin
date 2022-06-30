package gremlin.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import gremlin.GremlinMod;

public class EnthusiasmPower extends AbstractGremlinPower {
    public static final String POWER_ID = getID("Enthusiasm");

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture IMG = new Texture(GremlinMod.getResourcePath("powers/enthusiasm.png"));
    public int activation_count = 0;

    public EnthusiasmPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;

        this.img = IMG;
        this.type = AbstractPower.PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        activation_count = 0;
        updateDescription();
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();

        if (amount == 1)
            sb.append(DESCRIPTIONS[0]);
        else
            sb.append(DESCRIPTIONS[1]).append(amount).append(DESCRIPTIONS[2]);

        sb.append(DESCRIPTIONS[3]).append(activation_count);
        if (activation_count == 1)
            sb.append(DESCRIPTIONS[4]);
        else
            sb.append(DESCRIPTIONS[5]);

        this.description = sb.toString();
    }

    @Override
    public void onGremlinSwap(){
        if (activation_count < amount) {
            this.flash();
            addToBot(new DrawCardAction(1));
        }
        activation_count++;
        updateDescription();
    }
}

