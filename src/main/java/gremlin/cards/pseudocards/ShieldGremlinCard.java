package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.orbs.ShieldGremlin;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class ShieldGremlinCard extends AbstractGremlinCard {
    public static final String ID = getID("ShieldGremlin");

    public ShieldGremlinCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.dontTriggerOnUseCard = true;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        atb(new GremlinSwapAction(new ShieldGremlin(0)));
    }

    public AbstractCard makeCopy()
    {
        return new ShieldGremlinCard();
    }

    public void upp() {}
}


