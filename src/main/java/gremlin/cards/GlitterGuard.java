package gremlin.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.orbs.ShieldGremlin;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class GlitterGuard extends AbstractGremlinCard {
    public static final String ID = getID("GlitterGuard");

    public GlitterGuard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Ward();
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard w = new Ward();
        if(this.upgraded){
            w.upgrade();
        }
        makeInHand(w, this.magicNumber);
        atb(new GremlinSwapAction(new ShieldGremlin(0)));
    }

    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        this.cardsToPreview.upgrade();
    }
}

