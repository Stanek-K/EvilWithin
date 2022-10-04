package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BurningQuestion extends AbstractHexaCard {
    public final static String ID = makeID("BurningQuestion");

    public BurningQuestion() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseDownfallMagic = downfallMagic = 2;
        isEthereal = true;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p,magicNumber));
    }

    @Override
    public void afterlife() {
        flash();
        applyToSelf(new DexterityPower(AbstractDungeon.player, downfallMagic));
    }

    @Override
    protected boolean useAfterlifeVFX() {
        return true;
    }
}