package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.MuddleAction;
import sneckomod.actions.NoApplyRandomDamageAction;

public class RainOfDice extends AbstractSneckoCard {
    public final static String ID = makeID("RainOfDice");

    public RainOfDice() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDownfallMagic = downfallMagic = 6;
        baseDamage = 12;
        this.returnToHand = true;
        tags.add(SneckoMod.RNG);
    }

    public void upp() {
        upgradeDownfall(3);
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new NoApplyRandomDamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), downfallMagic, damage, 1, AbstractGameAction.AttackEffect.BLUNT_LIGHT, this, DamageInfo.DamageType.NORMAL));
        atb(new MuddleAction(this));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
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