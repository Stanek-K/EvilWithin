package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.MuddleAction;

public class ImprovisedAttack extends AbstractSneckoCard {
    public final static String ID = makeID("ImprovisedAttack");

    public static AbstractCard storage;

    public ImprovisedAttack() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractCard q = SneckoMod.getOffClassCardMatchingPredicate(c -> c.type == CardType.ATTACK);
        makeInHand(q);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                addToTop(new MuddleAction(storage));
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }
}