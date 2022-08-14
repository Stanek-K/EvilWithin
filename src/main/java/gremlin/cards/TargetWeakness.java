package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.TargetWeaknessPower;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class TargetWeakness extends AbstractGremlinCard {
    public static final String ID = getID("TargetWeakness");

    public TargetWeakness() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TargetWeaknessPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}

