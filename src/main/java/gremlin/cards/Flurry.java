package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.FlurryPower;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class Flurry extends AbstractGremlinCard {
    public static final String ID = getID("Flurry");

    public Flurry() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FlurryPower(p, this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
