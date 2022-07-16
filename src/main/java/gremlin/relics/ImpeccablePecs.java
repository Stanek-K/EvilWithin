package gremlin.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ImpeccablePecs extends AbstractGremlinRelic {
    public static final String ID = getID("ImpeccablePecs");
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final AbstractRelic.RelicTier TIER = RelicTier.UNCOMMON;
    private static final String IMG = "relics/impeccable_pecs.png";
    private static final AbstractRelic.LandingSound SOUND = LandingSound.SOLID;

    public ImpeccablePecs() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != AbstractDungeon.player && info.type == DamageInfo.DamageType.NORMAL) {
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 1), 1));
        }
        return super.onAttacked(info, damageAmount);
    }

    public void onTrigger(int amount) {} //TODO clean this up
}


