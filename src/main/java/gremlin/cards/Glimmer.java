package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class Glimmer extends AbstractGremlinCard {
    public static final String ID = getID("Glimmer");

    public Glimmer() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.cardsToPreview = new Ward();
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractCard w = new Ward();
        if(this.upgraded){
            w.upgrade();
        }
        makeInHand(w, 2);
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        this.cardsToPreview.upgrade();
    }
}
