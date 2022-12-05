package champ.cards;

import basemod.helpers.BaseModCardTags;
import champ.powers.GladiatorFormPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GladiatorForm extends AbstractChampCard {
    public final static String ID = makeID("GladiatorForm");

    public GladiatorForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        tags.add(BaseModCardTags.FORM);
        baseMagicNumber = magicNumber = 1;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GladiatorFormPower(magicNumber));
    }
}