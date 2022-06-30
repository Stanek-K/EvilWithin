package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Erupt extends AbstractGremlinCard {
    public static final String ID = getID("Erupt");

    public Erupt() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;
        this.baseDownfallMagic = this.downfallMagic = 2;
        this.exhaust = true;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(this.downfallMagic));
        applyToSelf(new StrengthPower(p, this.magicNumber));
        applyToSelf(new LoseStrengthPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}