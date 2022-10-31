package champ.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static champ.ChampMod.vigor;

public class DeathBlow extends AbstractChampCard {
    public final static String ID = makeID("DeathBlow");

    public DeathBlow() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 8;
        isMultiDamage = true;
        exhaust = true;
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        vigor(magicNumber);
    }
}