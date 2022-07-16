package expansioncontent.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
import downfall.downfallMod;
import expansioncontent.expansionContentMod;
import expansioncontent.patches.CardColorEnumPatch;
import hermit.util.TextureLoader;

import java.util.ArrayList;

import static expansioncontent.expansionContentMod.*;

public abstract class AbstractExpansionCard extends AbstractDownfallCard {
    public static final String CannotUseBossCardMessage = CardCrawlGame.languagePack.getUIString(makeID("CannotUseBossCardMessage")).TEXT[0];

    public AbstractExpansionCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(getModID(), id, cost,  type, rarity, target, CardColorEnumPatch.CardColorPatch.BOSS);
        setFrame();
    }

    public AbstractExpansionCard(final String id, final String img, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(getModID(), id, getCorrectPlaceholderImage(img), cost, type, rarity, target, CardColorEnumPatch.CardColorPatch.BOSS);
        setFrame();
    }

    public AbstractExpansionCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(getModID(), id, cost, type, rarity, target, color);
        setFrame();
    }

    public void setFrame(){
        if (this.type == CardType.ATTACK){
            this.setBackgroundTexture("expansionContentResources/images/512/bg_attack_evil.png", "expansionContentResources/images/1024/bg_attack_evil.png");
        } else if (this.type == CardType.POWER) {
            this.setBackgroundTexture("expansionContentResources/images/512/bg_power_evil.png", "expansionContentResources/images/1024/bg_power_evil.png");
        } else {
            this.setBackgroundTexture("expansionContentResources/images/512/bg_skill_evil.png", "expansionContentResources/images/1024/bg_skill_evil.png");
        }
    }

    private static String getCorrectPlaceholderImage(String id) {
        return makeCardPath(id.replaceAll((expansionContentMod.getModID() + ":"), "")) + ".png";
    }

    public static String makeID(String name) {
        return getModID() + ":" + name;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (hasTag(STUDY)) {
            if (!downfallMod.playedBossCardThisTurn)
                return super.canUse(p, m);
            else {
                cantUseMessage = CannotUseBossCardMessage;
                return false;
            }
        }
        return super.canUse(p, m);
    }

    public void upp() {}
}