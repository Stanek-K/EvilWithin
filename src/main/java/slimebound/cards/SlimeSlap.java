package slimebound.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.PreventSlimeDecayPower;
import slimebound.powers.SlimedPower;
import slimebound.vfx.SlimeSlapEffect;


public class SlimeSlap extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:SlimeSlap";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "cards/slimeslap.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 2;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public SlimeSlap() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        baseDamage = damage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower po = p.getPower(SlimedPower.POWER_ID);
        if (po != null) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    ((SlimedPower) po).dontRemoveOnce = true;
                    this.isDone = true;
                }
            });
        }
        addToBot(new VFXAction(new SlimeSlapEffect(m.hb.cX, m.hb.cY), 0.2F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public AbstractCard makeCopy() {
        return new SlimeSlap();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}

