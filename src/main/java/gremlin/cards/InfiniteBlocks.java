package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.InfiniteBlocksPower;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class InfiniteBlocks extends AbstractGremlinCard {
    public static final String ID = getID("InfiniteBlocks");

    public InfiniteBlocks() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new Ward();
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new InfiniteBlocksPower(p, 1));
    }

    public void upp() {
        this.isInnate = true;
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

