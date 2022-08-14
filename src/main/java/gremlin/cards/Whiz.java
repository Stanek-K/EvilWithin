package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.WhizAction;
import gremlin.powers.MakingMagicPower;
import gremlin.powers.MakingMoreMagicPower;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Whiz extends AbstractGremlinCard {
    public static final String ID = getID("Whiz");

    public Whiz() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WizPower(p, this.magicNumber));
        atb(new WhizAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}

