package downfall.events;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.city.Centurion;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import downfall.downfallMod;
import slimebound.SlimeboundMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Beggar_Evil extends AbstractImageEvent {
    public static final String ID = downfallMod.makeID("Beggar");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    public static final String[] DESCRIPTIONSALT;
    public static final String[] OPTIONSALT;
    public static final String[] DESCRIPTIONSOG;
    public static final String[] OPTIONSOG;
    private static final EventStrings eventStrings;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Beggar");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DESCRIPTIONSALT = CardCrawlGame.languagePack.getEventString(ID).DESCRIPTIONS;
        OPTIONSALT = CardCrawlGame.languagePack.getEventString(ID).OPTIONS;
        DESCRIPTIONSOG = CardCrawlGame.languagePack.getEventString(Cleric_Evil.ID).DESCRIPTIONS;
        OPTIONSOG = CardCrawlGame.languagePack.getEventString(Cleric_Evil.ID).OPTIONS;
    }

    private int finalDmg;
    private int gold;
    private CurScreen screen;
    private int cardsToTransform;
    private boolean cardsSelected = false;

    public Beggar_Evil() {
        super(NAME, DESCRIPTIONS[0], "images/events/beggar.jpg");
        this.noCardsInRewards = true;

        if (AbstractDungeon.ascensionLevel >= 15) {
            this.gold = 50;
        } else {
            this.gold = 75;
        }

        this.imageEventText.setDialogOption(OPTIONS[4]);
        this.screen = CurScreen.INTRO;
        this.cardsToTransform = 1;
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (cardsToTransform == 1) {
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
                AbstractDungeon.player.masterDeck.removeCard(c);
                AbstractDungeon.transformCard(c, false, AbstractDungeon.miscRng);
                AbstractCard transCard = AbstractDungeon.getTransformedCard();
                AbstractDungeon.gridSelectScreen.selectedCards.remove(c);
                logMetricTransformCard(ID, "Intimidate", c, transCard);
            } else if (!cardsSelected) {
                List<String> transformedCards = new ArrayList<>();
                List<String> obtainedCards = new ArrayList<>();
                if (AbstractDungeon.gridSelectScreen.selectedCards.size() == 2) {
                    this.cardsSelected = true;
                    float displayCount = 0.0F;
                    for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                        card.untip();
                        card.unhover();
                        transformedCards.add(card.cardID);
                        AbstractDungeon.player.masterDeck.removeCard(card);
                        AbstractDungeon.transformCard(card, false, AbstractDungeon.miscRng);
                        AbstractCard c = AbstractDungeon.getTransformedCard();
                        obtainedCards.add(c.cardID);
                        if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.TRANSFORM && c != null) {
                            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(c.makeCopy(), (float) Settings.WIDTH / 3.0F + displayCount, (float) Settings.HEIGHT / 2.0F, false));
                            displayCount += (float) Settings.WIDTH / 6.0F;
                        }
                    }

                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                    logMetricTransformCards(ID, "Threaten", transformedCards, obtainedCards);
                    AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
                }
            }
        }
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                this.imageEventText.clearAllDialogs();
                //SlimeboundMod.logger.info("Cleric Encountered: " + Cleric_Evil.encountered + ". Cleric Killed: " + Cleric_Evil.heDead + ".");
                if (!Cleric_Evil.encountered) {
                    this.imageEventText.loadImage("images/events/cleric.jpg");
                    this.screen = CurScreen.CLERICFRESHINTRO;
                    this.imageEventText.updateBodyText(DESCRIPTIONSALT[0]);

                    this.imageEventText.setDialogOption(OPTIONSOG[0] + this.gold + OPTIONSOG[4]); // Punch: gain souls
                    this.imageEventText.setDialogOption(OPTIONSOG[1]); // Intimidate: remove 1 card

                } else if (Cleric_Evil.heDead) {
                    this.screen = CurScreen.CLERICDEADINTRO;
                    this.imageEventText.updateBodyText(DESCRIPTIONSALT[1]);

                    this.imageEventText.setDialogOption(OPTIONSOG[0] + this.gold + OPTIONSOG[4] + OPTIONSALT[0], new Pride()); // Punch: gain souls, curse
                    this.imageEventText.setDialogOption(OPTIONS[5]); // Leave
                } else {
                    this.imageEventText.loadImage("images/events/cleric.jpg");
                    this.screen = CurScreen.CLERICALIVEINTRO;
                    this.imageEventText.updateBodyText(DESCRIPTIONSALT[4]); // CLERIC BOUGHT PROTECTION!
                    this.imageEventText.setDialogOption(OPTIONSALT[1]); // Fight

                }
                return;
            case CLERICFRESHINTRO:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.updateBodyText(DESCRIPTIONSOG[1]);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.gold));
                        AbstractDungeon.player.gainGold(this.gold);
                        CardCrawlGame.sound.play("BLUNT_HEAVY");
                        this.screen = CurScreen.END;
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        logMetricGainGold(ID, "Punch", this.gold);
                        break;
                    case 1:
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.updateBodyText(DESCRIPTIONSOG[2]);
                        this.transform();
                        this.screen = CurScreen.END;
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        break;
                }
                return;
            case CLERICDEADINTRO:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.updateBodyText(DESCRIPTIONSALT[2]);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.gold));
                        AbstractDungeon.player.gainGold(this.gold);
                        CardCrawlGame.sound.play("BLUNT_HEAVY");
                        Pride curse = new Pride();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(curse, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));

                        this.screen = CurScreen.END;
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        logMetricGainGoldAndCard(ID, "Punch", curse, this.gold);
                        break;
                    case 1:
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.updateBodyText(DESCRIPTIONSALT[3]);
                        this.screen = CurScreen.END;
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        logMetricIgnored(ID);
                        break;
                }
                return;
            case CLERICALIVEINTRO:
                switch (buttonPressed) {
                    case 0:

                        this.screen = CurScreen.POSTFIGHT;
                        MonsterGroup monsters = new MonsterGroup(new Centurion(-400F, 0F));
                        monsters.add(new Centurion(0F, 0F));
                        AbstractDungeon.getCurrRoom().monsters = monsters;
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().rewardAllowed = false;
                        AbstractDungeon.lastCombatMetricKey = "Hired Bodyguards";
                        this.enterCombatFromImage();
                        break;
                }
                return;
            case POSTFIGHT:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.updateBodyText(DESCRIPTIONSALT[6]);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.gold * 2));
                        AbstractDungeon.player.gainGold(this.gold * 2);
                        CardCrawlGame.sound.play("BLUNT_HEAVY");
                        this.screen = CurScreen.END;
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        logMetricGainGold(ID, "Punch", this.gold * 2);
                        break;
                    case 1:
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().rewardAllowed = false;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.updateBodyText(DESCRIPTIONSALT[7]);
                        cardsToTransform = 2;
                        this.transform();
                        this.screen = CurScreen.END;
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        break;
                }
                return;
            case END:
                this.openMap();
        }

    }

    public void reopen() {
        if (this.screen == CurScreen.POSTFIGHT) {
            AbstractDungeon.getCurrRoom().rewards.clear();
            AbstractDungeon.getCurrRoom().rewardAllowed = false;
            AbstractDungeon.resetPlayer();
            AbstractDungeon.player.drawX = (float) Settings.WIDTH * 0.25F;
            AbstractDungeon.player.preBattlePrep();
            this.imageEventText.clearAllDialogs();
            this.imageEventText.updateBodyText(DESCRIPTIONSALT[5]);
            this.imageEventText.setDialogOption(OPTIONSOG[0] + (this.gold * 2) + OPTIONSOG[4]); // Punch: gain many souls
            this.imageEventText.setDialogOption(OPTIONSALT[2]); // Threaten: remove 2 cards
            this.enterImageFromCombat();
        }
    }

    private void transform() {
        if (!AbstractDungeon.isScreenUp) {
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), cardsToTransform, OPTIONS[5], false, cardsToTransform == 1, false, false);
        } else {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), cardsToTransform, OPTIONS[5], false, cardsToTransform == 1, false, false);
        }

    }

    private enum CurScreen {
        INTRO,
        CLERICFRESHINTRO,
        CLERICALIVEINTRO,
        CLERICDEADINTRO,
        POSTFIGHT,
        END;

        CurScreen() {
        }
    }
}