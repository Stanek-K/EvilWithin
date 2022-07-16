package expansioncontent.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import expansioncontent.expansionContentMod;
import expansioncontent.powers.ChronoBoostPower;


public class Chronoboost extends AbstractExpansionCard {
    public final static String ID = makeID("Chronoboost");

    public Chronoboost() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(expansionContentMod.STUDY_TIMEEATER);
        tags.add(expansionContentMod.STUDY);
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_timeeater.png", "expansioncontentResources/images/1024/bg_boss_timeeater.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) atb(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));

        atb(new ApplyPowerAction(p, p, new ChronoBoostPower(p, p, 1), 1));
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}


