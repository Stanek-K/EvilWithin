package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.MakingMagicPower;
import gremlin.powers.MakingMoreMagicPower;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Whiz extends AbstractGremlinCard {
    public static final String ID = getID("Whiz");

    private final boolean real;

    public Whiz() {
        this(true);
    }

    public Whiz(boolean real) {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
        this.real = real;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
        if (this.real)
            this.cardsToPreview = new Bang(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WizPower(p, this.magicNumber));
        if(upgraded)
            applyToSelf(new MakingMoreMagicPower(p, 1));
        else
            applyToSelf(new MakingMagicPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        if (real) {
            this.cardsToPreview.upgrade();
        }
    }
}

