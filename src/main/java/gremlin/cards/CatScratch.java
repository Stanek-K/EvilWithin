package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class CatScratch extends AbstractGremlinCard {
    public static final String ID = getID("CatScratch");

    public CatScratch() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 2;
        this.baseMagicNumber = this.magicNumber = 3;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction.AttackEffect[] attacks = {
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        };

        for(int i = 0; i < this.magicNumber; i++) {
            atb(new DamageAction(m, makeInfo(), attacks[i%3]));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
