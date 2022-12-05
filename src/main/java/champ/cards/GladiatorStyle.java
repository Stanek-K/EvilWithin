package champ.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GladiatorStyle extends AbstractChampCard {
    public final static String ID = makeID("GladiatorStyle");

    public GladiatorStyle() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseDownfallMagic = downfallMagic = 1;
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new DexterityPower(p, downfallMagic));
    }
}