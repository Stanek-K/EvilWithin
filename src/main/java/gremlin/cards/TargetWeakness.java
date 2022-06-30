package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.TargetWeaknessPower;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class TargetWeakness extends AbstractGremlinCard {
    public static final String ID = getID("TargetWeakness");

    public TargetWeakness() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TargetWeaknessPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}

