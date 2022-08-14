package slimebound.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.NextTurnGainSlimeCrush;
import slimebound.powers.NextTurnGainStrengthPower;


public class PrepareCrush extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:PrepareCrush";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "cards/preparingcrush.png";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public PrepareCrush() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), 2, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new SlimeCrush();
        this.magicNumber = this.baseMagicNumber = 3;
        this.slimed = this.baseSlimed = 3;
        SlimeboundMod.loadJokeCardImage(this, "PrepareCrush.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList.add(new MegaSpeechBubble(p.hb.cX, p.hb.cY, 1.0F, "~Slime...~ NL #r~CRUSH!!!~", true));
        AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.3F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));

        if (upgraded)
            addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 4), 4));
        else
            addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 3), 3));

        addToBot(new ApplyPowerAction(p, p, new NextTurnGainStrengthPower(p, p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new NextTurnGainSlimeCrush(p, p, 1, this.upgraded), 1));
    }

    public AbstractCard makeCopy() {
        return new PrepareCrush();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            AbstractCard q = new SlimeCrush();
            q.upgrade();
            cardsToPreview = q;

            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }
}


