package sneckomod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

public class DiceBlock extends AbstractSneckoCard {
    public final static String ID = makeID("DiceBlock");

    public DiceBlock() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8; //Min Block
        baseBlock = 14; //Max Block
        tags.add(SneckoMod.RNG);
    }

    public void upp() {
        upgradeMagicNumber(6);
        upgradeBlock(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, getRandomNum(magicNumber, block, this)));
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
        isBlockModified = baseBlock != block;
    }
}