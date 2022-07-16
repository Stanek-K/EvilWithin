package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class ToeStub extends AbstractGremlinCard {
    public static final String ID = getID("ToeStub");

    public ToeStub() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseDownfallMagic = this.downfallMagic = 1;
        this.baseMagicNumber = this.magicNumber = 1;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToEnemy(m, autoWeak(m, downfallMagic));
        applyToEnemy(m, autoVuln(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}
