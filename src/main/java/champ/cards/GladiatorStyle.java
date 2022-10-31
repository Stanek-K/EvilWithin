package champ.cards;

import champ.ChampMod;
import champ.powers.GladiatorStylePower;
import champ.powers.ResolvePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static champ.ChampMod.fatigue;

public class GladiatorStyle extends AbstractChampCard {
    public final static String ID = makeID("GladiatorStyle");

    public GladiatorStyle() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
      baseMagicNumber = magicNumber = 2;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        for (AbstractMonster q : monsterList()) {
            applyToEnemy(q, new StrengthPower(q, 1));
        }
    }
}