package champ.cards;

import champ.ChampMod;
import champ.powers.BerserkerStylePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BerserkerStyle extends AbstractChampCard {
    public final static String ID = makeID("BerserkerStyle");

    public BerserkerStyle() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.tags.add(ChampMod.OPENER);
        this.tags.add(ChampMod.OPENERBERSERKER);
    }

    public void upp() {
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        berserkOpen();
        applyToSelf(new BerserkerStylePower(magicNumber));
    }
}