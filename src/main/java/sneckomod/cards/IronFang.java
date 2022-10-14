package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import sneckomod.SneckoMod;
import sneckomod.actions.NoApplyRandomDamageAction;

public class IronFang extends AbstractSneckoCard {
    public final static String ID = makeID("IronFang");

    public IronFang() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3; //Min Block
        baseBlock = 8; //Max Block
        baseDownfallMagic = downfallMagic = 3; //Min Damage
        baseDamage = 8; //Max Damage
        tags.add(SneckoMod.RNG);
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeBlock(2);
        upgradeDownfall(2);
        upgradeDamage(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, getRandomNum(magicNumber, block, this)));
        atb(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY), 0.3F));// 117
        atb(new NoApplyRandomDamageAction(m, downfallMagic, damage, 1, AbstractGameAction.AttackEffect.NONE, this, DamageInfo.DamageType.NORMAL));
    }

    @Override
    protected void applyPowersToBlock() {
        int CURRENT_MAGIC_NUMBER = baseMagicNumber;
        int CURRENT_BLOCK = baseBlock;
        baseBlock = CURRENT_MAGIC_NUMBER;
        super.applyPowersToBlock();
        magicNumber = block;
        isMagicNumberModified = block != baseBlock;
        baseBlock = CURRENT_BLOCK;
        super.applyPowersToBlock();
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
}
