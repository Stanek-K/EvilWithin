package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.NoApplyRandomDamageAction;

public class SnakeSap extends AbstractSneckoCard {
    public final static String ID = makeID("SnakeSap");

    public SnakeSap() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 3;
        baseDownfallMagic = downfallMagic = 1;
        exhaust = true;
        tags.add(SneckoMod.SNEKPROOF);
        tags.add(SneckoMod.RNG);
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new NoApplyRandomDamageAction(m, downfallMagic, damage, 1, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, this, DamageInfo.DamageType.NORMAL));
        int x = getRandomNum(1, magicNumber, this);
        if (x > 0)
            atb(new GainEnergyAction(x));
    }

    @Override
    public void applyPowers() {
        int CURRENT_SILLY = baseDownfallMagic;
        int CURRENT_DAMAGE = baseDamage;
        baseDamage = CURRENT_SILLY;
        super.applyPowers();
        downfallMagic = damage;
        isDownfallModified = damage != baseDamage;

        baseDamage = CURRENT_DAMAGE;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(final AbstractMonster m) {
        int CURRENT_SILLY = baseDownfallMagic;
        int CURRENT_DAMAGE = baseDamage;
        baseDamage = CURRENT_SILLY;
        super.calculateCardDamage(m);
        downfallMagic = damage;
        isDownfallModified = damage != baseDamage;

        baseDamage = CURRENT_DAMAGE;
        super.calculateCardDamage(m);
    }
}