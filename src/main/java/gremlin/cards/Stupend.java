package gremlin.cards;

import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Stupend extends AbstractGremlinCard {
    public static final String ID = getID("Stupend");

    public Stupend() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new WallopAction(m, makeInfo()));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}