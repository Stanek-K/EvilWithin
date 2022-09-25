package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.powers.UnendingSupplyPower;

public class UnendingSupply extends AbstractSneckoCard {
    public final static String ID = makeID("UnendingSupply");

    public UnendingSupply() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        SneckoMod.loadJokeCardImage(this, "UnendingSupply.png");
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UnendingSupplyPower(1));
    }
}