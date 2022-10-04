package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.actions.PremonitionAction;
import theHexaghost.HexaMod;

public class Premonition extends AbstractHexaCard {
    public final static String ID = makeID("Premonition");

    public Premonition() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        HexaMod.loadJokeCardImage(this, "Premonition.png");
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PremonitionAction(upgraded));
    }
}