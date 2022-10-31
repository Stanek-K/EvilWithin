package champ.cards;

import champ.powers.CounterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;

import static champ.ChampMod.loadJokeCardImage;

public class HoldFirm extends AbstractChampCard {
    public final static String ID = makeID("HoldFirm");

    public HoldFirm() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 12;
        magicNumber = baseMagicNumber = 10;
        baseDownfallMagic = downfallMagic = 1;
        loadJokeCardImage(this, "HoldFirm.png");
    }

    public void upp() {
        upgradeBlock(4);
        upgradeMagicNumber(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new CounterPower(magicNumber));
        applyToSelf(new BlurPower(p, downfallMagic));
    }
}