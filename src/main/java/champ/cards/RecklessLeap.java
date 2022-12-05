package champ.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RecklessLeap extends AbstractChampCard {
    public final static String ID = makeID("RecklessLeap");

    public RecklessLeap() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 18;
        baseMagicNumber = magicNumber = 2;
        baseDownfallMagic = downfallMagic = 1;
        tags.add(CardTags.STRIKE);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToEnemy(m, new StrengthPower(m, downfallMagic));
    }
}