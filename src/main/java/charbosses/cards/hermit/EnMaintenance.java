package charbosses.cards.hermit;

import charbosses.bosses.AbstractCharBoss;
import charbosses.bosses.Hermit.CharBossHermit;
import charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import hermit.actions.ReduceCostActionFixed;
import hermit.cards.Maintenance;
import hermit.characters.hermit;
import hermit.powers.MaintenanceStrikePower;

public class EnMaintenance extends AbstractHermitBossCard {
    public static final String ID = "downfall_Charboss:Maintenance";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("hermit:BossMaintenance");

    public EnMaintenance() {
        super(ID, cardStrings.NAME, "hermitResources/images/cards/maintenance.png", 2, cardStrings.DESCRIPTION, CardType.SKILL, hermit.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF, AbstractMonster.Intent.BUFF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, m, new MaintenanceStrikePower(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, m, new DexterityPower(m, 2)));
        addToBot(new ReduceCostActionFixed(this.uuid, 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);

        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new EnMaintenance();
    }
}
