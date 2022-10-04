package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UnleashSpirits extends AbstractHexaCard {
    public final static String ID = makeID("UnleashSpirits");

    public UnleashSpirits() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        exhaust = true;
    }

    public void upp() {
        upgradeDamage(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageRandomEnemyAction(makeInfo(), AbstractGameAction.AttackEffect.FIRE));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int i = 0;
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.isEthereal) {
                        i++;
                    }
                }
                for (int q = 0; q < i; q++) {
                    addToTop(new DamageRandomEnemyAction(makeInfo(), AbstractGameAction.AttackEffect.FIRE));
                }
            }
        });
    }
}