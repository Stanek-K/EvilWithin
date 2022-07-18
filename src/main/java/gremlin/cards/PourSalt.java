package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import gremlin.GremlinMod;

import static gremlin.GremlinMod.FAT_GREMLIN;
import static gremlin.GremlinMod.getModID;

public class PourSalt extends AbstractGremlinCard {
    public static final String ID = getID("PourSalt");

    public PourSalt() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.tags.add(FAT_GREMLIN);
        setBackgrounds();
        loadJokeCardImage(this, getModID(),"PourSalt.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (m.hasPower(WeakPower.POWER_ID))
                    att(new GainEnergyAction(1));
            }
        });
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
