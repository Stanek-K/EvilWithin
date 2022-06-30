package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.orbs.SneakyGremlin;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class SneakyGremlinCard extends AbstractGremlinCard {
    public static final String ID = getID("SneakyGremlin");

    public SneakyGremlinCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.dontTriggerOnUseCard = true;
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        atb(new GremlinSwapAction(new SneakyGremlin(0)));
    }

    public AbstractCard makeCopy()
    {
        return new SneakyGremlinCard();
    }

    public void upp() {}
}


