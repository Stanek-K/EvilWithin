package gremlin.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;
import gremlin.actions.PourSaltOuterAction;

import static gremlin.GremlinMod.FAT_GREMLIN;

public class PourSalt extends AbstractGremlinCard {
    public static final String ID = getID("PourSalt");

    public PourSalt() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Shiv();
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
        GremlinMod.loadJokeCardImage(this, "PourSalt.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PourSaltOuterAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
