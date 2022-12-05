package champ.cards;

import champ.ChampMod;
import expansioncontent.actions.EasyXCostAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SteelEdge extends AbstractChampCard {
    public final static String ID = makeID("SteelEdge");

    public SteelEdge() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 7;
        tags.add(ChampMod.FINISHER);
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            }
            return true;
        }));
        finisher();
    }
}