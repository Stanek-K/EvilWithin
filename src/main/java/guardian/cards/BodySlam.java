package guardian.cards;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import guardian.GuardianMod;
import guardian.patches.AbstractCardEnum;


public class BodySlam extends AbstractGuardianCard {
    public static final String ID = GuardianMod.makeID("BodySlam");
    public static final String NAME;
    public static final String IMG_PATH = "cards/bodySlam.png";
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardStrings cardStrings;
    public static String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public BodySlam() {
        super(ID, NAME, GuardianMod.getResourcePath(IMG_PATH), 2, DESCRIPTION, TYPE, AbstractCardEnum.GUARDIAN, RARITY, TARGET);
        this.baseBlock = 5;
        this.baseDamage = 0;
        this.socketCount = 0;
        this.rawDescription = DESCRIPTION;
        initializeDescription();
        loadGemMisc();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        this.baseDamage = p.currentBlock + this.block;
        calculateCardDamage(m);
        addToBot(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentBlock + this.block;
        super.applyPowers();

        this.rawDescription = DESCRIPTION;
        this.rawDescription += UPGRADED_DESCRIPTION;
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = DESCRIPTION;
        this.rawDescription += UPGRADED_DESCRIPTION;
        initializeDescription();
    }
}


