package automaton.cards;

import automaton.cardmods.ReducedCostMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClockOut extends AbstractBronzeCard {

    public final static String ID = makeID("ClockOut");

    //stupid intellij stuff attack, enemy, basic

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    public ClockOut() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void onCompile(AbstractCard function) {
        super.onCompile(function);
        CardModifierManager.addModifier(function, new ReducedCostMod());
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}