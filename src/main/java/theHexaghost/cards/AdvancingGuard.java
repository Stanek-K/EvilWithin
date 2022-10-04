package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.actions.AdvanceAction;

public class AdvancingGuard extends AbstractHexaCard {
    public final static String ID = makeID("AdvancingGuard");

    public AdvancingGuard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AdvanceAction(false));
    }
}