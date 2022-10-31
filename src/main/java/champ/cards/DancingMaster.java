package champ.cards;

import champ.powers.DancingMasterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.loadJokeCardImage;


public class DancingMaster extends AbstractChampCard {
    public final static String ID = makeID("DancingMaster");

    public DancingMaster() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        loadJokeCardImage(this, "DancingMaster.png");
    }

    public void upp() {
        upgradeBaseCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DancingMasterPower(magicNumber));
    }
}