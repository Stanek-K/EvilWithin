package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class CounterStrike extends AbstractGremlinCard {
    public static final String ID = getID("CounterStrike");

    public CounterStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Ward();
        this.tags.add(CardTags.STRIKE);
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (GremlinMod.doesEnemyIntendToAttack(m)) {
                    AbstractCard c = new Ward();
                    if(upgraded){
                        c.upgrade();
                    }
                   makeInHand(c, amount);
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        this.cardsToPreview.upgrade();
    }
}
