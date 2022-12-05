package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.powers.UpgradedOffclassPostCombatPower;

public class MorePower extends AbstractSneckoCard {
    public final static String ID = makeID("MorePower");

    public MorePower() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        tags.add(CardTags.HEALING);
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UpgradedOffclassPostCombatPower(1));
    }
}