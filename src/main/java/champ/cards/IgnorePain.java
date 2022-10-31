package champ.cards;

import champ.powers.PushThroughPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IgnorePain extends AbstractChampCard {
    public final static String ID = makeID("IgnorePain");

    public IgnorePain() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PushThroughPower(1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}