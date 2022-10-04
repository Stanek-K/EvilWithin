package theHexaghost.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHexaghost.HexaMod;
import downfall.util.TextureLoader;

public class LoseEnhanceInTurnsPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = HexaMod.makeID("LoseEnhanceInTurnsPower");

    private static final Texture tex84 = TextureLoader.getTexture(HexaMod.getModID() + "Resources/images/powers/LoseIntensity84.png");
    private static final Texture tex32 = TextureLoader.getTexture(HexaMod.getModID() + "Resources/images/powers/LoseIntensity32.png");

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseEnhanceInTurnsPower(final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new ReducePowerAction(owner, owner, EnhancePower.POWER_ID, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[3];
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseEnhanceInTurnsPower(amount);
    }
}