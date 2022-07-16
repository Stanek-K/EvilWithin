package sneckomod.util;

import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import downfall.patches.RewardItemTypeEnumPatch;
import downfall.util.TextureLoader;
import sneckomod.OffclassHelper;
import sneckomod.SneckoMod;

import java.util.ArrayList;

public class UpgradedOffclassReward extends CustomReward {
    public static final String ID = SneckoMod.makeID("UpgradedOffclassReward");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public UpgradedOffclassReward() {
        super(TextureLoader.getTexture("downfallResources/images/rewards/placeholder.png"), TEXT[0], RewardItemTypeEnumPatch.UPGRADEDUNKNOWNCARD);
        cards.clear();
        cards.addAll(getCards());
    }

    public static ArrayList<AbstractCard> getCards() {
        return OffclassHelper.getXRandomOffclassCards(3);
    }

    @Override
    public boolean claimReward() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[1]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }
}