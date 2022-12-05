package champ.cards;

import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VampiricStrike extends AbstractChampCard {
    public final static String ID = makeID("VampiricStrike");

    public VampiricStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new WallopAction(m, makeInfo()));
    }

    public void upp() {
        upgradeDamage(3);
    }
}