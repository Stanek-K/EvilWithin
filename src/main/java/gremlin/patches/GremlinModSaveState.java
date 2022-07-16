package gremlin.patches;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gremlin.GremlinHelper;
import gremlin.GremlinMod;
import gremlin.GremlinCharacter;

public class GremlinModSaveState implements CustomSavable<GremlinHelper> {
    @Override
    public GremlinHelper onSave() {
        if(AbstractDungeon.player instanceof GremlinCharacter) {
            GremlinHelper state = ((GremlinCharacter)(AbstractDungeon.player)).mobState;
            GremlinMod.logger.info("Saving: " + state.toString());
            return state;
        }
        return null;
    }

    @Override
    public void onLoad(GremlinHelper gremlinMobState) {
        if(AbstractDungeon.player instanceof GremlinCharacter) {
            GremlinMod.logger.info("Loading: " + gremlinMobState.toString());
            ((GremlinCharacter)(AbstractDungeon.player)).mobState = gremlinMobState;
        }
    }
}
