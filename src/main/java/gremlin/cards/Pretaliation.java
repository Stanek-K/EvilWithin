package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import gremlin.GremlinMod;
import guardian.powers.LoseThornsPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Pretaliation extends AbstractGremlinCard {
    public static final String ID = getID("Pretaliation");

    public Pretaliation() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 7;
        this.baseMagicNumber = magicNumber = 3;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToSelf(new ThornsPower(p, this.magicNumber));
        applyToSelf(new LoseThornsPower(p, this.magicNumber));
    }
}
