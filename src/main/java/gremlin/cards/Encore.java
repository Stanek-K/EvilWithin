package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.EncorePower;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Encore extends AbstractGremlinCard {
    public static final String ID = getID("Encore");

    public Encore() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
        this.baseDownfallMagic = this.downfallMagic = 3;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WizPower(p, downfallMagic));
        applyToSelf(new EncorePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}

