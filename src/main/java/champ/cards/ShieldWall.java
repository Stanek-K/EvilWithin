package champ.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class ShieldWall extends AbstractChampCard {
    public final static String ID = makeID("ShieldWall");

    public ShieldWall() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseDownfallMagic = downfallMagic = 2;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, downfallMagic));
        applyToSelf(new MetallicizePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}