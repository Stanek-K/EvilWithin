package theHexaghost.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theHexaghost.GhostflameHelper;
import theHexaghost.HexaMod;

public class ExtinguishCurrentFlameAction extends AbstractGameAction {
    private final AbstractGameAction followUpAction;

    public ExtinguishCurrentFlameAction() {
        followUpAction = null;
    }

    public ExtinguishCurrentFlameAction(AbstractGameAction followUpAction) {
        this.followUpAction = followUpAction;
    }

    public void update() {
    //  if (!HexaMod.renderFlames)
    //      HexaMod.renderFlames = true;
        if (GhostflameHelper.activeGhostFlame.charged) addToTop(followUpAction);
        GhostflameHelper.activeGhostFlame.extinguish();
        isDone = true;
    }
}
