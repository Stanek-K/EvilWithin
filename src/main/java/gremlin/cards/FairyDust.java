package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;

import static gremlin.GremlinMod.SHIELD_GREMLIN;
import static gremlin.GremlinMod.getModID;

public class FairyDust extends AbstractGremlinCard {
    public static final String ID = getID("FairyDust");

    public FairyDust() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseDownfallMagic = downfallMagic = 1;
        this.exhaust = true;
        this.cardsToPreview = new Ward();
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
        loadJokeCardImage(this, getModID(), "FairyDust.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new Ward(), this.magicNumber);
        atb(new DrawCardAction(p, downfallMagic));
    }

    public void upp() {
        upgradeDownfall(1);
    }
}


