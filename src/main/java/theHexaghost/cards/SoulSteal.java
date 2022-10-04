package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.powers.LivingBombPower;

public class SoulSteal extends AbstractHexaCard {
    public final static String ID = makeID("SoulSteal");

    public SoulSteal() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseTagMagic = tagMagic = 4;
        this.tags.add(HexaMod.DEALS_SOULBURN);
        HexaMod.loadJokeCardImage(this, "SoulSteal.png");
    }

    public void upp() {
        upgradeTagMagic(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        burn(m, tagMagic);
        applyToEnemy(m, new LivingBombPower(m, magicNumber));
    }
}