package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.BashedPower;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class AggressiveDefense extends AbstractGremlinCard {
    public static final String ID = getID("AggressiveDefense");

    public AggressiveDefense() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 3;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
        loadJokeCardImage(this, this.modID, "AggressiveDefense.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToEnemy(m, new BashedPower(m, this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
