package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.orbs.MadGremlin;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class MadGremlinCard extends AbstractGremlinCard {
    public static final String ID = getID("MadGremlin");

    public MadGremlinCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.dontTriggerOnUseCard = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractDungeon.actionManager.addToBottom(new GremlinSwapAction(new MadGremlin(0)));
    }

    public AbstractCard makeCopy()
    {
        return new MadGremlinCard();
    }

    public void upp() {}
}