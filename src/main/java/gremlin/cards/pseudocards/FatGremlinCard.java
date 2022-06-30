package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.orbs.FatGremlin;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class FatGremlinCard extends AbstractGremlinCard {
    public static final String ID = getID("FatGremlin");

    public FatGremlinCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.dontTriggerOnUseCard = true;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        atb(new GremlinSwapAction(new FatGremlin(0)));
    }

    public AbstractCard makeCopy()
    {
        return new FatGremlinCard();
    }

    public void upp() {}
}


