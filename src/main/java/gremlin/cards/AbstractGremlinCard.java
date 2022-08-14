package gremlin.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import expansioncontent.cards.AbstractDownfallCard;
import gremlin.GremlinMod;
import gremlin.patches.Enums;

import static gremlin.GremlinMod.*;

public abstract class AbstractGremlinCard extends AbstractDownfallCard {
    protected static String getID(String ID) {
        return "gremlin:" + ID;
    }

    protected AbstractGremlinCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(GremlinMod.getModID(), id, cost, type, rarity, target, Enums.AbstractCardEnum.GREMLIN);
        setBackgrounds();
    }

    protected AbstractGremlinCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(GremlinMod.getModID(), id, cost, type, rarity, target, color);
        setBackgrounds();
    }

    protected AbstractGremlinCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(GremlinMod.getModID(), id, img, cost, type, rarity, target, color);
        setBackgrounds();
    }

    public void onGremlinSwap() {
    }

    public void onGremlinSwapInDeck() {
    }

    public void setBackgrounds() {
        //Call this immediately after any tag changes.
        if (getGremlinEnumFromCard(this).equals(ART_GREMLIN.NONE)) {
            switch (this.type) {
                case SKILL:
                    this.setBackgroundTexture("gremlinResources/images/512/bg_skill_gremlin.png", "gremlinResources/images/1024/bg_skill_gremlin.png");
                    break;
                case POWER:
                    this.setBackgroundTexture("gremlinResources/images/512/bg_power_gremlin.png", "gremlinResources/images/1024/bg_power_gremlin.png");
                    break;
                case ATTACK:
                    this.setBackgroundTexture("gremlinResources/images/512/bg_attack_gremlin.png", "gremlinResources/images/1024/bg_attack_gremlin.png");
                    break;
            }
        } else {
            switch (this.type) {
                case SKILL:
                    this.setBackgroundTexture("gremlinResources/images/512/bg_skill_gremlin_" + getGremlinEnumFromCard(this).name().toLowerCase() + ".png", "gremlinResources/images/1024/bg_skill_gremlin_" + getGremlinEnumFromCard(this).name().toLowerCase() + ".png");
                    break;
                case POWER:
                    this.setBackgroundTexture("gremlinResources/images/512/bg_power_gremlin_" + getGremlinEnumFromCard(this).name().toLowerCase() + ".png", "gremlinResources/images/1024/bg_power_gremlin_" + getGremlinEnumFromCard(this).name().toLowerCase() + ".png");
                    break;
                case ATTACK:
                    this.setBackgroundTexture("gremlinResources/images/512/bg_attack_gremlin_" + getGremlinEnumFromCard(this).name().toLowerCase() + ".png", "gremlinResources/images/1024/bg_attack_gremlin_" + getGremlinEnumFromCard(this).name().toLowerCase() + ".png");
                    break;
            }
        }
    }

    public enum ART_GREMLIN {
        FAT,
        SNEAKY,
        TSUNDERE,
        WIZ,
        THIEF,
        NOB,
        NONE
    }

    public ART_GREMLIN getGremlinEnumFromCard(AbstractCard card) {
        if (!GremlinMod.gremlinArtMode && !(card instanceof GremlinDance)) {
            return ART_GREMLIN.NONE;
        }

        // Here is where you or I can implement the function that takes a card,
        // and based off of its tags, returns the corresponding ART_GREMLIN enum.
        if (card.tags.contains(FAT_GREMLIN)) {
            return ART_GREMLIN.FAT;
        }
        if (card.tags.contains(MAD_GREMLIN)) {
            return ART_GREMLIN.THIEF;
        }
        if (card.tags.contains(SHIELD_GREMLIN)) {
            return ART_GREMLIN.TSUNDERE;
        }
        if (card.tags.contains(SNEAKY_GREMLIN)) {
            return ART_GREMLIN.SNEAKY;
        }
        if (card.tags.contains(WIZARD_GREMLIN)) {
            return ART_GREMLIN.WIZ;
        }
        return ART_GREMLIN.NONE;
    }
}