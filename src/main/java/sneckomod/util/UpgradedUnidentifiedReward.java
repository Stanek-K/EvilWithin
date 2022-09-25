package sneckomod.util;

import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import downfall.patches.RewardItemTypeEnumPatch;
import downfall.util.TextureLoader;
import sneckomod.SneckoMod;
import sneckomod.cards.unknowns.AbstractUnknownCard;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class UpgradedUnidentifiedReward extends CustomReward {
    public static final String ID = SneckoMod.makeID("UnidentifiedReward");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public UpgradedUnidentifiedReward() {
        super(TextureLoader.getTexture("downfallResources/images/rewards/placeholder.png"), TEXT[0], RewardItemTypeEnumPatch.UNIDENTIFIEDCARD);
        cards.clear();
        cards.addAll(getCards());
    }

    public static ArrayList<AbstractCard> getCards() {
        ArrayList<AbstractCard> cardList = getUnidentifiedCards(),
                                rewards = new ArrayList<>();
        int amount = Math.min(cardList.size(), 3);
        for (int i = 0; i < amount; i++) {
            AbstractCard c = cardList.get(cardRandomRng.random(cardList.size() - 1));
            cardList.remove(c);
            rewards.add(c);
        }
        if (rewards.isEmpty()) rewards.add(new Madness());
        return rewards;
    }

    public static ArrayList<AbstractCard> getUnidentifiedCards() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards())
            if (c instanceof AbstractUnknownCard)
                if ( !((AbstractUnknownCard) c).myList().isEmpty() ) list.add(c);
        return list;
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