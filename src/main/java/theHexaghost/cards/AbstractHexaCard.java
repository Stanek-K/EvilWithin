package theHexaghost.cards;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import expansioncontent.cards.AbstractDownfallCard;
import hermit.util.TextureLoader;
import theHexaghost.HexaMod;
import theHexaghost.TheHexaghost;
import theHexaghost.powers.BurnPower;
import theHexaghost.vfx.AfterlifePlayEffect;

import static theHexaghost.HexaMod.getModID;
import static theHexaghost.HexaMod.makeCardPath;

public abstract class AbstractHexaCard extends AbstractDownfallCard {
    public String betaArtPath;

    public AbstractHexaCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, getCorrectPlaceholderImage(id), cost, type, rarity, target, TheHexaghost.Enums.GHOST_GREEN);
    }

    public AbstractHexaCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(id, getCorrectPlaceholderImage(id), cost, type, rarity, target, color);
    }

    private static String getCorrectPlaceholderImage(String id) {
        return makeCardPath(id.replaceAll((HexaMod.getModID() + ":"), "")) + ".png";
    }

    public static String makeID(String blah) {
        return getModID() + ":" + blah;
    }

    public void burn(AbstractMonster m, int amount) {
        applyToEnemy(m, new BurnPower(m, amount));
    }

    protected void burnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        if (AbstractDungeon.getCurrRoom().monsters != null)
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDeadOrEscaped() && m.hasPower(BurnPower.POWER_ID)) {
                    this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                    break;
                }
            }
    }

    @Override
    public void triggerOnExhaust() {
        att(new AbstractGameAction() {
            @Override
            public void update() {
                if (useAfterlifeVFX() && duration == startDuration) {
                    atb(new VFXAction(new AfterlifePlayEffect(AbstractHexaCard.this)));
                }
                tickDuration();
                if (isDone) {

                    atb(new WaitAction(0.2F)); // from ShowCardAction

                    applyPowers();
                    afterlife();

                    atb(new WaitAction(0.15F)); // from UseCardAction

                    if (type == AbstractCard.CardType.POWER) { // special case for powers in UseCardAction
                        if (com.megacrit.cardcrawl.core.Settings.FAST_MODE) {
                            atb(new WaitAction(0.1F));
                        } else {
                            atb(new WaitAction(0.7F));
                        }
                    }
                }
            }
        });

    }

    protected boolean useAfterlifeVFX() {
        return AbstractHexaCard.this.tags.contains(HexaMod.AFTERLIFE);
    }

    public void afterlife() {}

    @Override
    protected Texture getPortraitImage() {
        if (Settings.PLAYTESTER_ART_MODE || UnlockTracker.betaCardPref.getBoolean(this.cardID, false)) {
            if (this.textureImg == null) {
                return null;
            } else {
                if (betaArtPath != null) {
                    int endingIndex = betaArtPath.lastIndexOf(".");
                    String newPath = betaArtPath.substring(0, endingIndex) + "_p" + betaArtPath.substring(endingIndex);
                    newPath = "hexamodResources/images/betacards/" + newPath;
                    System.out.println("Finding texture: " + newPath);

                    Texture portraitTexture;
                    portraitTexture = TextureLoader.getTexture(newPath);

                    return portraitTexture;
                }
            }
        }
        return super.getPortraitImage();
    }
}