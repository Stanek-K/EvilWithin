package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.actions.RetractAction;

public class Rewind extends AbstractHexaCard {
    public final static String ID = makeID("Rewind");

    public Rewind() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.exhaust = true;
        HexaMod.loadJokeCardImage(this, "Rewind.png");
    }

    public void upp() {
        upgradeMagicNumber(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RetractAction());
        atb(new BetterDiscardPileToHandAction(magicNumber));
    }
}