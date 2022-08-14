package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Presto extends AbstractGremlinCard {
    public static final String ID = getID("Presto");

    public Presto() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 1;
        isMultiDamage = true;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        applyToSelf(new WizPower(p, this.magicNumber));
    }

    @Override
    public void upp(){
        upgradeDamage(3);
    }
}
