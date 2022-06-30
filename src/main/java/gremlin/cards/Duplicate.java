package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.DuplicateAction;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class Duplicate extends AbstractGremlinCard {
    public static final String ID = getID("Duplicate");

    public Duplicate() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust = true;
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DuplicateAction(p, magicNumber));
    }

    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        this.exhaust = false;
    }
}


