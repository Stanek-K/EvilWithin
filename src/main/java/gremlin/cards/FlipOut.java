package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.FuriousPower;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class FlipOut extends AbstractGremlinCard {
    public static final String ID = getID("FlipOut");

    public FlipOut() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 10;
        this.isMultiDamage = true;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToSelf(new FuriousPower(p));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}
