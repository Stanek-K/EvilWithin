package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.actions.SacrificeAction2;

public class NecessarySacrifice extends AbstractHexaCard {
    public final static String ID = makeID("NecessarySacrifice");

    public NecessarySacrifice() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        HexaMod.loadJokeCardImage(this, "NecessarySacrifice.png");
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SacrificeAction2(upgraded));
    }
}