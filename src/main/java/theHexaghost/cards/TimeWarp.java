package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.util.OnAdvanceOrRetractSubscriber;

public class TimeWarp extends AbstractHexaCard implements OnAdvanceOrRetractSubscriber {
    public final static String ID = makeID("TimeWarp");

    public TimeWarp() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void upp() {
        upgradeDamage(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
    }

    @Override
    public void onAdvanceOrRetract(boolean endTurn) {
        if (!endTurn)
            this.addToBot(new DiscardToHandAction(this));// 40
    }
}