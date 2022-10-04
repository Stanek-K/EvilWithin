package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;
import theHexaghost.actions.BurningHitAction;

public class Toasty extends AbstractHexaCard {
    public final static String ID = makeID("Toasty");

    public Toasty() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 14;
        HexaMod.loadJokeCardImage(this, "Toasty.png");
    }

    public void upp() {
        upgradeDamage(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new BurningHitAction(m, p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }
}