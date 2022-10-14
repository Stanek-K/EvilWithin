package downfall.events;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import downfall.downfallMod;
import downfall.cards.curses.Icky;

import java.util.ArrayList;

public class WorldOfGoop_Evil extends AbstractImageEvent {
    public static final String ID = downfallMod.makeID("WorldOfGoop");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final EventStrings eventStrings;
    private static final String DIALOG_1;
    private static final String GOLD_DIALOG;
    private static final String LEAVE_DIALOG;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        GOLD_DIALOG = DESCRIPTIONS[1];
        LEAVE_DIALOG = DESCRIPTIONS[2];
    }

    private CurScreen screen;
    private final int goldforCurse = 150;
    private final int goldForHp;
    private final int hpLoss = 11;

    public WorldOfGoop_Evil() {
        super(NAME, DIALOG_1, "images/events/goopPuddle.jpg");
        this.screen = CurScreen.INTRO;

        if (AbstractDungeon.ascensionLevel >= 15) {
            this.goldForHp = 50;
        } else {
            this.goldForHp = 75;
        }

        this.imageEventText.setDialogOption(OPTIONS[0] + this.goldforCurse + OPTIONS[2], new Icky());
        this.imageEventText.setDialogOption(OPTIONS[1] + this.goldForHp + OPTIONS[3] + this.hpLoss + OPTIONS[4]);
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SPIRITS");
        }
    }

    private void logMetricGoop() {
        ArrayList<String> icks = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            icks.add(Icky.ID);
        }
        logMetric(ID, "Full Harvest", icks, null, null, null,
                null, null, null,
                0, 0, 0, 0, this.goldforCurse, 0);
    }

    protected void buttonEffect(int buttonPressed) {
        if (this.screen == CurScreen.INTRO) {
            switch (buttonPressed) {
                case 0:
                    this.imageEventText.updateBodyText(GOLD_DIALOG);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    this.screen = CurScreen.RESULT;
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Icky(), (float) Settings.WIDTH * .5F + 10.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldforCurse));
                    AbstractDungeon.player.gainGold(this.goldforCurse);
                    logMetricGoop();
                    return;
                case 1:
                    this.imageEventText.updateBodyText(GOLD_DIALOG);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    this.screen = CurScreen.RESULT;
                    AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.hpLoss));
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                    AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldForHp));
                    AbstractDungeon.player.gainGold(this.goldForHp);
                    AbstractEvent.logMetricGainGoldAndDamage(ID, "Sap", this.goldForHp, this.hpLoss);
                    return;
                case 2:
                    this.imageEventText.updateBodyText(LEAVE_DIALOG);
                    this.imageEventText.clearAllDialogs();
                    this.imageEventText.setDialogOption(OPTIONS[5]);
                    this.screen = CurScreen.RESULT;
                    logMetricIgnored(ID);
                default:
            }
        } else {
            this.openMap();
        }
    }

    private enum CurScreen {
        INTRO,
        RESULT;

        CurScreen() {
        }
    }
}
