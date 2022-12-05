package champ.cards;

import champ.ChampMod;
import champ.powers.BerserkerStylePower;
import champ.powers.FocusedBerPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

public class FocusedBerserking extends AbstractChampCard {
    public final static String ID = makeID("FocusedBerserking");

    public FocusedBerserking() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        tags.add(ChampMod.FINISHER);
        exhaust = true;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            finisher(true);
        }
    }
}