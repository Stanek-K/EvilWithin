package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class GremlinMeal extends AbstractGremlinCard {
    public static final String ID = getID("GremlinMeal");

    public GremlinMeal() {
        super(ID,0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, this.magicNumber));
        applyToSelf(new LoseStrengthPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}


