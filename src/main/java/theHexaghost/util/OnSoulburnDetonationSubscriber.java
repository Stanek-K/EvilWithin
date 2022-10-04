package theHexaghost.util;

import com.megacrit.cardcrawl.core.AbstractCreature;

public interface OnSoulburnDetonationSubscriber {
    default void onDetonationOwner(int soulburnAmount) {

    }

    default void onDetonationPlayer(AbstractCreature origin) {

    }
}
