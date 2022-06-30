package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.GremlinMod;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class FeelTheAudience extends AbstractGremlinCard {
    public static final String ID = getID("FeelTheAudience");

    public FeelTheAudience() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 1;
        this.isMultiDamage = true;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                if (GremlinMod.doesEnemyIntendToAttack(mo)) {
                    amount += this.magicNumber;
                }
            }
        }
        allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if(amount > 0) {
            applyToSelf(new WizPower(p, amount));
        }
    }

    @Override
    public void upp(){
        upgradeDamage(3);
    }
}
