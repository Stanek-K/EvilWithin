package gremlin.cards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.ShadowShivPower;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class ShadowShiv extends AbstractGremlinCard {
    public static final String ID = getID("ShadowShiv");

    public ShadowShiv() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new Shiv();
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        applyToSelf(new ShadowShivPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}

