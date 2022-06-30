package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.CrippledPower;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class Exacerbate extends AbstractGremlinCard {
    public static final String ID = getID("Exacerbate");

    public Exacerbate() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.exhaust = true;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, new CrippledPower(m, p));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}
