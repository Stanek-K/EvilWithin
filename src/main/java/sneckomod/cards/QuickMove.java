package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.actions.MuddleRandomCardAction;

public class QuickMove extends AbstractSneckoCard {
    public final static String ID = makeID("QuickMove");

    public QuickMove() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new MuddleRandomCardAction(magicNumber, true));
    }
}