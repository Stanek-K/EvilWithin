package theHexaghost.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theHexaghost.HexaMod;

public class GhostShield extends AbstractHexaCard {
    public final static String ID = makeID("GhostShield");

    public GhostShield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
        isEthereal = true;
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void afterlife() {
        flash(Color.PURPLE);
        applyToSelf(new NextTurnBlockPower(AbstractDungeon.player, block));
    }
}