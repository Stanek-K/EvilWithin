package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class SleeveOfAces extends AbstractGremlinCard {
    public static final String ID = getID("SleeveOfAces");

    public SleeveOfAces() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.cardsToPreview = new Shiv();
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        if(p.hasPower(WizPower.POWER_ID))
            amount = p.getPower(WizPower.POWER_ID).amount;
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if(amount > 0) makeInHand(new Shiv(), amount);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
