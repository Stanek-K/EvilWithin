package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class EdibleArmor extends AbstractGremlinCard {
    public static final String ID = getID("EdibleArmor");

    public EdibleArmor() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
        this.baseDownfallMagic = this.downfallMagic = 6;
        this.exhaust = true;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ThornsPower(p, this.magicNumber));
        atb(new AddTemporaryHPAction(p, p, this.downfallMagic));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeDownfall(2);
    }
}

