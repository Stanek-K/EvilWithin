package gremlin.patches.relicpatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.SingingBowl;
import gremlin.GremlinCharacter;

@SpirePatch(
        clz= SingingBowl.class,
        method="getUpdatedDescription"
)
public class SingingBowlDescriptionPatch {
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings("gremlin:PatchFixes");

    public static SpireReturn<String> Prefix(SingingBowl __instance){
        if(AbstractDungeon.player instanceof GremlinCharacter){
            return SpireReturn.Return(strings.DESCRIPTIONS[1]);
        }
        return SpireReturn.Continue();
    }
}