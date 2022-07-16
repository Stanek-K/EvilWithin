package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class TwistTheKnife extends AbstractGremlinCard {
    public static final String ID = getID("TwistTheKnife");

    public TwistTheKnife() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.cardsToPreview = new Shiv();
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        makeInHand(new Shiv());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
