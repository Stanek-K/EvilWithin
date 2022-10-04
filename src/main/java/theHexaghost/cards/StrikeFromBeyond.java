package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.actions.DrawSpecificCardsAction;

public class StrikeFromBeyond extends AbstractHexaCard {
    public final static String ID = makeID("StrikeFromBeyond");

    public StrikeFromBeyond() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        this.tags.add(CardTags.STRIKE);
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new DrawSpecificCardsAction(1, (c) -> c.isEthereal));
    }
}