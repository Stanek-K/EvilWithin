package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Changeo extends AbstractGremlinCard {
    public static final String ID = getID("Changeo");

    public Changeo() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf( new WizPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBlock(4);
    }
}

