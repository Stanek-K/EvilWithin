package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BulkUp extends AbstractGremlinCard {
    public static final String ID = getID("BulkUp");

    public BulkUp() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, this.magicNumber));

        if (upgraded)
            atb(new ArmamentsAction(false));
        else
            atb(new UpgradeRandomCardAction());
    }

    public void upp(){
        upgradeMagicNumber(1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

