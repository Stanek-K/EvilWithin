package theHexaghost.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import expansioncontent.cardmods.EtherealMod;
import theHexaghost.HexaMod;

public class Haunt extends AbstractHexaCard {
    public final static String ID = makeID("Haunt");
    public static final String[] EXTENDED_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    public Haunt() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 4;
        isEthereal = true;
        HexaMod.loadJokeCardImage(this, "HauntedHand.png");
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HauntAction(magicNumber));
    }
}




class HauntAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final float startingDuration;
    private CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    HauntAction(int amount) {
        this.amount = amount;
        if (AbstractDungeon.player.hasRelic("GoldenEye")) {
            AbstractDungeon.player.getRelic("GoldenEye").flash();
            this.amount += 2;
        }

        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startingDuration) {
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    p.onScry();
                }

                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (this.amount != -1) {
                    for (int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); ++i) {
                        tmpGroup.addToTop(AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
                    }
                } else {
                    for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                        tmpGroup.addToBottom(c);
                    }
                }

                AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, TEXT[0]);
            } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                    tmpGroup.removeCard(c);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                c.triggerOnScry();
            }

            this.tickDuration();

            if (isDone)
                for (AbstractCard c : tmpGroup.group)
                    if (!c.isEthereal)
                        CardModifierManager.addModifier(c, new EtherealMod());
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ReprogramAction");
        TEXT = uiStrings.TEXT;
    }
}