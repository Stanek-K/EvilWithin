package hermit;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardSave;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import downfall.downfallMod;
import downfall.util.CardIgnore;
import hermit.actions.MessageCaller;
import hermit.cards.*;
import hermit.characters.hermit;
import hermit.patches.EnumPatch;
import hermit.potions.BlackBile;
import hermit.potions.Eclipse;
import hermit.potions.Tonic;
import hermit.relics.*;
import hermit.rewards.BountyGold;
import hermit.util.CardFilter;
import hermit.util.TextureLoader;
import hermit.variables.DefaultCustomVariable;
import hermit.variables.DefaultSecondMagicNumber;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

@SpireInitializer
public class HermitMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        //EditStringsSubscriber,
        //EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        AddAudioSubscriber,
        OnStartBattleSubscriber,
        SetUnlocksSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    //
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)
    //public static boolean[] activeTutorials = new boolean[]{true};
    public static Properties HermitModDefaultSettings = new Properties();
    public static boolean tackybypass = false;

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "The Hermit";
    private static final String AUTHOR = "Team D-13"; // And pretty soon - You!
    private static final String DESCRIPTION = "A base for Slay the Spire to start your own mod from, feat. the Default.";

    // =============== INPUT TEXTURE LOCATION =================

    // Colors (RGB)
    // Character Color
    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);
    public static final Color HERMIT_YELLOW = CardHelper.getColor(254.0F, 223.0F, 0.0F);

    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!

    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DEFAULT_GRAY = "hermitResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "hermitResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "hermitResources/images/512/bg_power_default_gray.png";

    private static final String ENERGY_ORB_DEFAULT_GRAY = "hermitResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "hermitResources/images/512/card_small_orb.png";

    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "hermitResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "hermitResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "hermitResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "hermitResources/images/1024/card_default_gray_orb.png";

    // Character assets
    private static final String THE_DEFAULT_BUTTON = "hermitResources/images/charSelect/HermitButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "hermitResources/images/charSelect/hermitSelect.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "hermitResources/images/char/hermit/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "hermitResources/images/char/hermit/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "hermitResources/images/char/hermit/corpse.png";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "hermitResources/images/Badge.png";

    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "hermitResources/images/char/hermit/Hermit.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "hermitResources/images/char/hermit/Hermit.json";

    // =============== MAKE IMAGE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    // Dead on card list juts swoocing right in.

    public static CardGroup deadList;


    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================

    public HermitMod() {
        System.out.println("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);

        modID = ("hermit");

        System.out.println("Creating the color " + hermit.Enums.COLOR_YELLOW.toString());

        BaseMod.addColor(hermit.Enums.COLOR_YELLOW, HERMIT_YELLOW, HERMIT_YELLOW, HERMIT_YELLOW,
                HERMIT_YELLOW, HERMIT_YELLOW, HERMIT_YELLOW, HERMIT_YELLOW,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);
    }

    public static String getModID() {
        return modID;
    }

    private static boolean alreadyInitialized = false;

    @SuppressWarnings("unused")
    public static void initialize() {

        if (alreadyInitialized) {
            return;
        }
        alreadyInitialized = true;

        HermitMod hermitMod = new HermitMod();
    }

    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================


    // =============== LOAD THE CHARACTER =================

    @Override
    public void receiveEditCharacters() {
        System.out.println("Beginning to edit characters. " + "Add " + hermit.Enums.HERMIT.toString());

        BaseMod.addCharacter(new hermit("the Hermit", hermit.Enums.HERMIT),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, hermit.Enums.HERMIT);

        receiveEditPotions();
        System.out.println("Added " + hermit.Enums.HERMIT.toString());
    }

    // =============== /LOAD THE CHARACTER/ =================


    // =============== POST-INITIALIZE =================

    @Override
    public void receivePostInitialize() {
        System.out.println("Loading badge image and mod options");

        // Load the Mod Badge
        // Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        // Create the Mod Menu
        //  ModPanel settingsPanel = new ModPanel();

        // BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        BaseMod.registerCustomReward(
                EnumPatch.HERMIT_BOUNTY,
                (rewardSave) -> { // this handles what to do when this quest type is loaded.
                    return new BountyGold(rewardSave.amount);
                },
                (customReward) -> { // this handles what to do when this quest type is saved.
                    return new RewardSave(customReward.type.toString(), null, ((BountyGold) customReward).goldAmt, 0);
                });


        // =============== EVENTS =================

        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"
        //BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);

        // Dead On card list addition.

        // WIDE pots

        if (Loader.isModLoaded("widepotions")) {
            WidePotionsMod.whitelistSimplePotion(Tonic.POTION_ID);
            WidePotionsMod.whitelistSimplePotion(BlackBile.POTION_ID);
            WidePotionsMod.whitelistSimplePotion(Eclipse.POTION_ID);
        }

        deadList = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard deadlist_c : CardLibrary.getAllCards()) {
            if (deadlist_c.hasTag(AbstractHermitCard.Enums.DEADON) && !deadlist_c.hasTag(AbstractCard.CardTags.HEALING) && deadlist_c.type != AbstractCard.CardType.CURSE && deadlist_c.rarity != AbstractCard.CardRarity.BASIC) {
                deadList.addToBottom(deadlist_c);
            }
        }

        // =============== /EVENTS/ =================
        System.out.println("Done loading badge Image and mod options");
    }

    // =============== / POST-INITIALIZE/ =================


    // ================ ADD POTIONS ===================

    public void receiveEditPotions() {
        System.out.println("Beginning to edit potions");

        BaseMod.addPotion(Tonic.class, null, null, null, Tonic.POTION_ID, hermit.Enums.HERMIT);
        BaseMod.addPotion(BlackBile.class, null, null, null, BlackBile.POTION_ID, hermit.Enums.HERMIT);
        BaseMod.addPotion(Eclipse.class, Color.SCARLET.cpy(), Color.BLACK.cpy(), null, Eclipse.POTION_ID, hermit.Enums.HERMIT);

        System.out.println("Done editing potions");
    }

    // ================ /ADD POTIONS/ ===================


    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
        System.out.println("Adding relics");

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.

        BaseMod.addRelicToCustomPool(new Memento(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new RyeStalk(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new Spyglass(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new StraightRazor(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new CharredGlove(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new RedScarf(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new BlackPowder(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new ClaspedLocket(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new BartenderGlass(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new DentedPlate(), hermit.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool(new PetGhost(), hermit.Enums.COLOR_YELLOW);


        BaseMod.addRelic(new BrassTacks(), RelicType.SHARED);
        BaseMod.addRelic(new BloodyTooth(), RelicType.SHARED);
        BaseMod.addRelic(new Horseshoe(), RelicType.SHARED);


        // Mark relics as seen (the others are all starters so they're marked as seen in the character file
        UnlockTracker.markRelicAsSeen(Memento.ID);
        UnlockTracker.markRelicAsSeen(RyeStalk.ID);
        UnlockTracker.markRelicAsSeen(BartenderGlass.ID);
        UnlockTracker.markRelicAsSeen(Horseshoe.ID);
        UnlockTracker.markRelicAsSeen(Spyglass.ID);
        UnlockTracker.markRelicAsSeen(CharredGlove.ID);
        UnlockTracker.markRelicAsSeen(RedScarf.ID);
        UnlockTracker.markRelicAsSeen(DentedPlate.ID);
        UnlockTracker.markRelicAsSeen(PetGhost.ID);
        UnlockTracker.markRelicAsSeen(ClaspedLocket.ID);
        System.out.println("Done adding relics!");
    }

    // ================ /ADD RELICS/ ===================


    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        System.out.println("Adding variables");
        // Add the Custom Dynamic Variables
        System.out.println("Add variabls");
        // Add the Custom Dynamic variabls
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());

        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Done adding cards!");
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = HermitMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);

        }
    }

    public void receiveOnBattleStart(AbstractRoom room) {
        tackybypass = true;
        if (AbstractDungeon.player instanceof hermit) {
            if (downfallMod.unseenTutorials[0]) {
                AbstractDungeon.actionManager.addToBottom(new MessageCaller(0));
            }
        }
    }


    public static void saveData() throws IOException {
    }


    public static void loadJokeCardImage(AbstractCard card, String img) {
        if (card instanceof AbstractHermitCard) {
            ((AbstractHermitCard) card).betaArtPath = img;
        }
        Texture cardTexture;
        cardTexture = TextureLoader.getTexture(getModID() + "Resources/images/betacards/" + img);
        cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int tw = cardTexture.getWidth();
        int th = cardTexture.getHeight();
        TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, tw, th);
        ReflectionHacks.setPrivate(card, AbstractCard.class, "jokePortrait", cardImg);
    }


    // ================ /LOAD THE KEYWORDS/ ===================    

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(makeID("GUN1"), "hermitResources/audio/hermit_gun.ogg");
        BaseMod.addAudio(makeID("GUN2"), "hermitResources/audio/hermit_gun2.ogg");
        BaseMod.addAudio(makeID("GUN3"), "hermitResources/audio/hermit_gun3.ogg");
        BaseMod.addAudio(makeID("SPIN"), "hermitResources/audio/hermit_spin.ogg");
        BaseMod.addAudio(makeID("RELOAD"), "hermitResources/audio/hermit_reload.ogg");
    }

    @Override
    public void receiveSetUnlocks() {
        downfallMod.registerUnlockSuiteAlternating(
            LoneWolf.ID,
            FullyLoaded.ID,
            Showdown.ID,

            BartenderGlass.ID,
            Spyglass.ID,
            RedScarf.ID,

            CursedWeapon.ID,
            BlackWind.ID,
            Purgatory.ID,

            Horseshoe.ID,
            CharredGlove.ID,
            PetGhost.ID,

            Reprieve.ID,
            FromBeyond.ID,
            DeadMansHand.ID,

            hermit.Enums.HERMIT
        );
    }
}
