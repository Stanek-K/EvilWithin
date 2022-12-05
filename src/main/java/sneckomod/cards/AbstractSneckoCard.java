package sneckomod.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import expansioncontent.cards.AbstractDownfallCard;
import hermit.util.TextureLoader;
import sneckomod.SneckoMod;
import sneckomod.TheSnecko;
import sneckomod.powers.CheatPower;
import sneckomod.relics.D8;
import sneckomod.relics.LoadedDie;

import java.util.ArrayList;
import java.util.List;

import static sneckomod.SneckoMod.getModID;
import static sneckomod.SneckoMod.makeCardPath;


public abstract class AbstractSneckoCard extends AbstractDownfallCard {
    protected String[] unknownUpgrade = CardCrawlGame.languagePack.getUIString(makeID("Unknown")).TEXT;
    protected String[] unknownNames = CardCrawlGame.languagePack.getUIString(makeID("UnknownNames")).TEXT;

    public String betaArtPath;

    public AbstractSneckoCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id,  getCorrectPlaceholderImage(id), cost, type, rarity, target, TheSnecko.Enums.SNECKO_CYAN);
    }

    public AbstractSneckoCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(id,  getCorrectPlaceholderImage(id), cost,  type, rarity, target, color);
    }

    public AbstractSneckoCard(final String id, final String img, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, img, cost, type, rarity, target, TheSnecko.Enums.SNECKO_CYAN);
    }

    public AbstractSneckoCard(final String id, final String img, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(id, img, cost, type, rarity, target, color);
    }

    public static String getCorrectPlaceholderImage(String id) {
        return makeCardPath(id.replaceAll((getModID() + ":"), "")) + ".png";
    }

    public static int getRandomNum(int min, int max) {
        int a, b, sum;
        if (min > max) {
            a = max;
            b = min;
        } else {
            a = min;
            b = max;
        }
        if (a != b) {
            sum = AbstractDungeon.cardRandomRng.random(a, b);
        } else {
            sum = b;
        }
        return sum;
    }


    public static int getRandomNum(int min, int max, AbstractSneckoCard source) {

        int bruh = min;
        if (AbstractDungeon.player.hasPower(CheatPower.POWER_ID)) {
            AbstractPower q = AbstractDungeon.player.getPower(CheatPower.POWER_ID);
            q.flash();
            return max;
        }
        if (AbstractDungeon.player.hasRelic(D8.ID)) {
            //SlimeboundMod.logger.info("min/max check passed D8 relic check");
            if (source != null) {
                //SlimeboundMod.logger.info("min/max check passed card source check");
                D8 d8relic = (D8) AbstractDungeon.player.getRelic(D8.ID);
                if (d8relic.card == source)
                    //SlimeboundMod.logger.info("min/max check passed card source = bottled card check");
                    return max;
            }
        }
        if (AbstractDungeon.player.hasRelic(LoadedDie.ID))
            bruh++;

        int a, b, sum;
        if (bruh > max) {
            a = max;
            b = bruh;
        } else {
            a = bruh;
            b = max;
        }
        if (a != b) {
            sum = AbstractDungeon.cardRandomRng.random(a, b);
        } else {
            sum = b;
        }
        return sum;
    }

    public static String makeID(String name) {
        return getModID() + ":" + name;
    }

    public static String getCharList() {
        StringBuilder s = new StringBuilder();
        for (CardColor c : SneckoMod.validColors) {
            s.append(" NL ").append(SneckoMod.getClassFromColor(c));
        }
        return s.toString();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        for (String name : unknownNames) {
            if (this.rawDescription.contains(name)) {
                if (SneckoMod.validColors.size() > 3) {
                    tips.add(new TooltipInfo(unknownUpgrade[0], unknownUpgrade[5]));
                }
                else if (SneckoMod.validColors.isEmpty()) {
                    tips.add(new TooltipInfo(unknownUpgrade[0], unknownUpgrade[4]));
                } else {
                    tips.add(new TooltipInfo(unknownUpgrade[0], unknownUpgrade[2] + unknownUpgrade[3] + getCharList()));
                }
            }
        }
        return tips;
    }

    @Override
    protected Texture getPortraitImage() {
        if (Settings.PLAYTESTER_ART_MODE || UnlockTracker.betaCardPref.getBoolean(this.cardID, false)) {
            if (this.textureImg == null) {
                return null;
            } else {
                if (betaArtPath != null) {
                    int endingIndex = betaArtPath.lastIndexOf(".");
                    String newPath = betaArtPath.substring(0, endingIndex) + "_p" + betaArtPath.substring(endingIndex);
                    newPath = "sneckomodResources/images/betacards/" + newPath;
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