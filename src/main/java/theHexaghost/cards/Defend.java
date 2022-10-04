package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;

public class Defend extends AbstractHexaCard {
    public final static String ID = makeID("Defend");

    public Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
        HexaMod.loadJokeCardImage(this, "Defend.png");
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}