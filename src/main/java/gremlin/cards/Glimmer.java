package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class Glimmer extends AbstractGremlinCard {
    public static final String ID = getID("Glimmer");

    public Glimmer() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 1;
        this.cardsToPreview = new Ward();
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        makeInHand(new Ward(), this.magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = this.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
