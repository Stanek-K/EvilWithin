package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.NOB_GREMLIN;

public class SkullBash extends AbstractGremlinCard {
    public static final String ID = getID("SkullBash");

    public SkullBash() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 2;
        this.isEthereal = true;
        this.exhaust = true;
        this.tags.add(NOB_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, autoVuln(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
