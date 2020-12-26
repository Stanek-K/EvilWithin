package automaton.cards;

import automaton.FunctionHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RecursiveStrike extends AbstractBronzeCard {

    public final static String ID = makeID("RecursiveStrike");

    //stupid intellij stuff attack, enemy, common

    private static final int DAMAGE = 4;

    public RecursiveStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = 4;
        thisEncodes();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void atTurnStart() {
        if (FunctionHelper.held != null) {
            if (FunctionHelper.held.contains(this)) {
                baseDamage += magicNumber;
                damage += magicNumber;
                superFlash();
                FunctionHelper.genPreview();
            }
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}