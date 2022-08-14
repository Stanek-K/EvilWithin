package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static gremlin.GremlinMod.*;

public class ArmsTheft extends AbstractGremlinCard {
    public static final String ID = getID("ArmsTheft");

    public ArmsTheft() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseDownfallMagic = this.downfallMagic = 1;
        this.exhaust = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
        loadJokeCardImage(this, getModID(), "ArmsTheft.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new StrengthPower(m, -magicNumber));
        applyToSelf(new StrengthPower(p, downfallMagic));
    }

    public void upp() {
        upgradeDownfall(1);
    }
}

