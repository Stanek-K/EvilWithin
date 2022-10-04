package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.BurnvenomPower;

public class BurningTouch extends AbstractHexaCard {
    public final static String ID = makeID("BurningTouch");

    public BurningTouch() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseTagMagic = tagMagic = 8;
        baseMagicNumber = magicNumber = 3;
        this.tags.add(HexaMod.DEALS_SOULBURN);
        HexaMod.loadJokeCardImage(this, "BurningTouch.png");
    }

    public void upp() {
        upgradeTagMagic(1);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        burn(m, tagMagic);
        applyToSelf(new BurnvenomPower(magicNumber));
    }
}