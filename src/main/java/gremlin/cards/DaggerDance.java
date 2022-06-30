package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class DaggerDance extends AbstractGremlinCard {
    public static final String ID = getID("DaggerDance");

    public DaggerDance() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = this.magicNumber = 2;
        this.isMultiDamage = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; i++) {
            atb(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0f));
            allDmg(AbstractGameAction.AttackEffect.NONE);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
