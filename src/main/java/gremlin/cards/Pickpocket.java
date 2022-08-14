package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Pickpocket extends AbstractGremlinCard {
    public static final String ID = getID("Pickpocket");

    public Pickpocket() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 8;
        this.exhaust = true;
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new GainGoldAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(4);
    }
}
