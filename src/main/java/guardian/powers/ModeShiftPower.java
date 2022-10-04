package guardian.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import guardian.relics.ModeShifter;
import guardian.stances.DefensiveMode;


public class ModeShiftPower extends AbstractGuardianPower {
    public static final String POWER_ID = "Guardian:ModeShiftPower";
    private static final int STARTINGAMOUNT = 16;
    private static final int AMOUNTGAINPERACTIVATION = 8;
    private static final int MAXAMOUNT = 40;
    public static final int BLOCKONTRIGGER = 20;
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static String[] DESCRIPTIONS;
    private final boolean active;
    private int activations = 0;

    public ModeShiftPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.loadRegion("modeShift");
        this.type = POWER_TYPE;
        this.amount = STARTINGAMOUNT;
        this.active = true;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onSpecificTrigger(int brace) {
        this.amount -= brace;
        updateDescription();
        flash();
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, BLOCKONTRIGGER));
            AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(DefensiveMode.STANCE_ID));

            int turns;
            if (AbstractDungeon.actionManager.turnHasEnded)
                turns = 2;
            else
                turns = 1;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DontLeaveDefensiveModePower(AbstractDungeon.player, turns), turns));

            this.activations++;
            this.amount += Math.min(STARTINGAMOUNT + (AMOUNTGAINPERACTIVATION * activations), MAXAMOUNT); //Set max of 40 Brace
            updateDescription();
            if (this.amount <= 0){
                onSpecificTrigger(0);
            }

        }
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.active && !AbstractDungeon.player.hasPower(BufferPower.POWER_ID)) {
            onSpecificTrigger(damageAmount);
        }
        return super.onLoseHp(damageAmount);
    }
}