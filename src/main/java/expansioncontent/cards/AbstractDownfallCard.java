package expansioncontent.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import hermit.util.TextureLoader;

import java.util.ArrayList;

abstract public class AbstractDownfallCard extends CustomCard {
    //Variables
        //Second Magic - Downfall Magic (DM)
            public int downfallMagic;
            public int baseDownfallMagic;
            public boolean upgradedDownfall;
            public boolean isDownfallModified;
        //Third Magic - Second Downfall Magic (DM2)
            public int secondDownfall;
            public int baseSecondDownfall;
            public boolean upgradedSecondDownfall;
            public boolean isSecondDownfallModified;
        //Character Magic (DCM)
            public int characterMagic;
            public int baseCharacterMagic;
            public boolean upgradedCharacterMagic;
            public boolean isCharacterMagicModified;
    //Card stuff
        public String betaArtPath;
        protected final String modID;
        protected final CardStrings cardStrings;
        protected final String NAME;
        protected String DESCRIPTION;
        protected String UPGRADE_DESCRIPTION;
        protected String[] EXTENDED_DESCRIPTION;


    public AbstractDownfallCard(final String modID, final String id, String img, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(id, "ERROR", Gdx.files.internal(img).exists() ? img : getCorrectPlaceholderImage(type), cost, "ERROR", type, color, rarity, target);
        this.modID = modID;
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    public AbstractDownfallCard(final String modID, final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        this(modID, id, generateImagePath(modID, id), cost, type, rarity, target, color);
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
                    newPath = modID + "Resources/images/betacards/" + newPath;
                    System.out.println("Finding texture: " + newPath);

                    Texture portraitTexture;
                    portraitTexture = TextureLoader.getTexture(newPath);

                    return portraitTexture;
                }
            }
        }
        return super.getPortraitImage();
    }

    public static void loadJokeCardImage(AbstractDownfallCard card, String modID, String img) {
        card.betaArtPath = img;
        Texture cardTexture;
        cardTexture = hermit.util.TextureLoader.getTexture(modID + "Resources/images/betacards/" + img);
        cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int tw = cardTexture.getWidth();
        int th = cardTexture.getHeight();
        TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, tw, th);
        ReflectionHacks.setPrivate(card, AbstractCard.class, "jokePortrait", cardImg);
    }

    private static String generateImagePath(String modID, String id) {
        return modID + "Resources/images/cards/" + id.replaceAll((modID + ":"), "") + ".png";
    }

    private static String getCorrectPlaceholderImage(CardType type) {
        switch (type) {
            case ATTACK:
                return "expansioncontentResources/images/cards/Placeholder/Attack.png";
            case POWER:
                return "expansioncontentResources/images/cards/Placeholder/Power.png";
            default:
                return "expansioncontentResources/images/cards/Placeholder/Skill.png";
        }
    }

    //Variables and upgrades

    public void resetAttributes() {
        super.resetAttributes();
        downfallMagic = baseDownfallMagic;
        isDownfallModified = false;
        secondDownfall = baseSecondDownfall;
        isSecondDownfallModified = false;
        characterMagic = baseCharacterMagic;
        isCharacterMagicModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedDownfall) {
            downfallMagic = baseDownfallMagic;
            isDownfallModified = true;
        }
        if (upgradedSecondDownfall) {
            secondDownfall = baseSecondDownfall;
            isSecondDownfallModified = true;
        }
        if (upgradedCharacterMagic) {
            characterMagic = baseCharacterMagic;
            isCharacterMagicModified = true;
        }
    }

    public void upgradeDownfall(int amount) {
        baseDownfallMagic += amount;
        downfallMagic = baseDownfallMagic;
        upgradedDownfall = true;
    }

    public void upgradeSecondDownfall(int amount) {
        baseSecondDownfall += amount;
        secondDownfall = baseSecondDownfall;
        upgradedSecondDownfall = true;
    }

    public void upgradeCharacterMagic(int amount) {
        baseCharacterMagic += amount;
        characterMagic = baseCharacterMagic;
        upgradedCharacterMagic = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
        }
    }

    abstract public void upp();

    //Simple Methods

    protected void atb(AbstractGameAction action) {
        addToBot(action);
    }

    protected void att(AbstractGameAction action) {
        addToTop(action);
    }

    protected DamageInfo makeInfo() {
        return makeInfo(damageTypeForTurn);
    }

    private DamageInfo makeInfo(DamageInfo.DamageType type) {
        return new DamageInfo(AbstractDungeon.player, damage, type);
    }

    public void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, makeInfo(), fx));
    }

    public void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    public void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public void makeInHand(AbstractCard c, int i) {
        atb(new MakeTempCardInHandAction(c, i));
    }

    public void makeInHand(AbstractCard c) {
        makeInHand(c, 1);
    }

    public void makeInHandTop(AbstractCard c, int i) {
        att(new MakeTempCardInHandAction(c, i));
    }

    public void makeInHandTop(AbstractCard c) {
        makeInHandTop(c, 1);
    }

    public void shuffleIn(AbstractCard c, int i) {
        atb(new MakeTempCardInDrawPileAction(c, i, true, true)); // This is suspect
    }

    public void shuffleIn(AbstractCard c) {
        shuffleIn(c, 1);
    }

    public ArrayList<AbstractMonster> monsterList() {
        ArrayList<AbstractMonster> q = new ArrayList<>();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDying && !m.isDead) q.add(m);
        }
        return q;
    }

    public void applyToEnemy(AbstractMonster m, AbstractPower po) {
        atb(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }

    public void applyToEnemyTop(AbstractMonster m, AbstractPower po) {
        att(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }

    public void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public void applyToSelfTop(AbstractPower po) {
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public WeakPower autoWeak(AbstractMonster m, int i) {
        return new WeakPower(m, i, false);
    }

    public VulnerablePower autoVuln(AbstractMonster m, int i) {
        return new VulnerablePower(m, i, false);
    }
}
