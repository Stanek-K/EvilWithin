package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theHexaghost.HexaMod;

public class Floatwork extends AbstractHexaCard {
    public final static String ID = makeID("Floatwork");

    public Floatwork() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 3;
        isEthereal = true;
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new PlatedArmorPower(p, magicNumber));
    }
}