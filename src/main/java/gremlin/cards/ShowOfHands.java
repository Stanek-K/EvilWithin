package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.BlockPerCardInHandAction;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class ShowOfHands extends AbstractGremlinCard {
    public static final String ID = getID("ShowOfHands");

    public ShowOfHands() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded)
            atb(new DrawCardAction(p, 1));
        atb(new BlockPerCardInHandAction(magicNumber));
    }

    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

