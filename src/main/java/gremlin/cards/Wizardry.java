package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.WizPower;
import gremlin.powers.WizardryPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Wizardry extends AbstractGremlinCard {
    public static final String ID = getID("Wizardry");

    public Wizardry() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseDownfallMagic = this.downfallMagic = 1;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
        loadJokeCardImage(this, modID, "Wizardry.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WizPower(p, downfallMagic));
        applyToSelf(new WizardryPower(p, magicNumber));
    }

    public void upp() {
        upgradeDownfall(1);
    }
}

