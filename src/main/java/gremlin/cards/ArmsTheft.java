package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gremlin.GremlinMod;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class ArmsTheft extends AbstractGremlinCard {
    public static final String ID = getID("ArmsTheft");

    public ArmsTheft() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseDownfallMagic = this.downfallMagic = 1;
        this.exhaust = true;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
        GremlinMod.loadJokeCardImage(this, "ArmsTheft.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            applyToEnemy(m, new StrengthPower(m, -this.magicNumber));
            applyToEnemy(m, autoWeak(m, downfallMagic));
        } else {
            for (AbstractMonster mo : monsterList()) {
                applyToEnemy(mo, new StrengthPower(m, -this.magicNumber));
                applyToEnemy(mo, autoWeak(m, downfallMagic));
            }
        }
    }

    public void upp() {
        this.target = CardTarget.ALL_ENEMY;
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

