package guardian.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import guardian.GuardianMod;
import guardian.patches.AbstractCardEnum;
import guardian.powers.DontLeaveDefensiveModePower;
import guardian.stances.DefensiveMode;
import guardian.vfx.IronWaveEffectBlue;
import hermit.actions.ReduceDebuffsAction;


public class Harden extends AbstractGuardianCard {
    public static final String ID = GuardianMod.makeID("Harden");
    public static final String NAME;
    public static final String IMG_PATH = "cards/harden2.png";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings;
    private static final int COST = 2;
    public static String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }


    public Harden() {
        super(ID, NAME, GuardianMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.GUARDIAN, RARITY, TARGET);
        this.baseBlock = 10;
        this.magicNumber = this.baseMagicNumber = 1;
        this.socketCount = 0;
        updateDescription();
        loadGemMisc();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ReduceDebuffsAction(p, magicNumber));
        this.useGems(p, m);
    }

    public AbstractCard makeCopy() {
            return new Harden();
        }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    public void updateDescription() {

        if (this.socketCount > 0) {
            if (upgraded && UPGRADED_DESCRIPTION != null) {
                this.rawDescription = this.updateGemDescription(UPGRADED_DESCRIPTION, true);
            } else {
                this.rawDescription = this.updateGemDescription(DESCRIPTION, true);
            }
        }
        this.initializeDescription();
    }
}


