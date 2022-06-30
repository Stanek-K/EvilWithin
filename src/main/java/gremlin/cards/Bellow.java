package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gremlin.GremlinMod;

import static gremlin.GremlinMod.NOB_GREMLIN;

public class Bellow extends AbstractGremlinCard {
    public static final String ID = getID("Bellow");

    public Bellow() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = 2;
        this.isEthereal = true;
        this.exhaust = true;
        this.tags.add(NOB_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int amount = 0;
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                if (!(GremlinMod.doesEnemyIntendToAttack(mo))) {
                    amount += this.magicNumber;
                }
            }
        }
        if(amount > 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new StrengthPower(p, amount), amount));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}


