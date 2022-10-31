package champ.cards;

import champ.ChampMod;
import champ.actions.AnimateSuplexAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Haymaker extends AbstractChampCard {
    public final static String ID = makeID("Haymaker");

    public Haymaker() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 2;
        baseDownfallMagic = downfallMagic = 2;
        tags.add(ChampMod.FINISHER);
    }

    public void upp() {
        upgradeDamage(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateSuplexAction(m));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToEnemy(m, autoWeak(m, downfallMagic));
        finisher();
    }
}
