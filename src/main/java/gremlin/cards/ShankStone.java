package gremlin.cards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class ShankStone extends AbstractGremlinCard {
    public static final String ID = getID("ShankStone");

    public ShankStone() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
        this.cardsToPreview = new Shiv();
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new Shiv(), magicNumber);
    }

    @Override
    public void triggerWhenDrawn() {
        makeInHand(new Shiv());
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

