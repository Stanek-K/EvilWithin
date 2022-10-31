package champ.cards;

import champ.ChampMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.loadJokeCardImage;

public class FocusedDefense extends AbstractChampCard {
    public final static String ID = makeID("FocusedDefense");

    public FocusedDefense() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 4;
        baseMagicNumber = magicNumber = 4;
        tags.add(ChampMod.OPENER);
        tags.add(ChampMod.OPENERBERSERKER);
        loadJokeCardImage(this, "BobAndWeave.png");
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        ChampMod.vigor(magicNumber);
        berserkOpen();
    }
}