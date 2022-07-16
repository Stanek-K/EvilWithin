package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Pickpocket extends AbstractGremlinCard {
    public static final String ID = getID("Pickpocket");

    public Pickpocket() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToEnemy(m, autoVuln(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
