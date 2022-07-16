package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Presto extends AbstractGremlinCard {
    public static final String ID = getID("Presto");

    public Presto() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = this.magicNumber = 1;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToSelf(new WizPower(p, this.magicNumber));
    }

    @Override
    public void upp(){
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}
