package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.orbs.GremlinWizard;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class GremlinWizardCard extends AbstractGremlinCard {
    public static final String ID = getID("GremlinWizard");

    public GremlinWizardCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.dontTriggerOnUseCard = true;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        atb(new GremlinSwapAction(new GremlinWizard(0)));
    }

    public AbstractCard makeCopy()
    {
        return new GremlinWizardCard();
    }

    public void upp() {}
}


