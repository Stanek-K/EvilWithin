package gremlin.relics;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import gremlin.actions.GremlinSwapAction;

public class GremlinKnob extends AbstractGremlinRelic {
    public static final String ID = getID("GremlinKnob");
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final AbstractRelic.RelicTier TIER = RelicTier.STARTER;
    private static final String IMG = "relics/gremlinKnob.png";
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;

    public static final int AMOUNT = 6;

    public GremlinKnob() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0] + AMOUNT + strings.DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, AMOUNT));
    }
}

