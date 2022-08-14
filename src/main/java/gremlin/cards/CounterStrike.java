package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class CounterStrike extends AbstractGremlinCard {
    public static final String ID = getID("CounterStrike");

    public CounterStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 1;
        this.cardsToPreview = new Shiv();
        this.tags.add(CardTags.STRIKE);
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (GremlinMod.doesEnemyIntendToAttack(m)) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    makeInHand(new Shiv(), magicNumber);
                    this.isDone = true;
                }
            });
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
