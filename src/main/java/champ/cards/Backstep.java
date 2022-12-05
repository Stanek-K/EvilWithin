package champ.cards;

import champ.ChampMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class Backstep extends AbstractChampCard {
    public final static String ID = makeID("Backstep");

    public Backstep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        tags.add(ChampMod.OPENER);
        this.tags.add(ChampMod.OPENERDEFENSIVE);
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        defenseOpen();
        blck();
    }

    @Override
    protected void applyPowersToBlock() {
        int realBaseBlock = this.baseBlock;
        if (AbstractDungeon.player.hasPower(VigorPower.POWER_ID)) {
            baseBlock += AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
        }
        super.applyPowersToBlock();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = block != baseBlock;
    }
}