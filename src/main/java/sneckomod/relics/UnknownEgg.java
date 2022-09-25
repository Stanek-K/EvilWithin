package sneckomod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sneckomod.SneckoMod;
import downfall.util.TextureLoader;

public class UnknownEgg extends CustomRelic {
    public static final String ID = SneckoMod.makeID("UnknownEgg");
    private static final Texture IMG = TextureLoader.getTexture(SneckoMod.makeRelicPath("UnknownEgg.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(SneckoMod.makeRelicOutlinePath("UnknownEgg.png"));

    public UnknownEgg() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
