package sneckomod.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import sneckomod.SneckoMod;

import java.util.ArrayList;

public class GlitteringGambit extends AbstractSneckoCard {
    public final static String ID = makeID("GlitteringGambit");

    public GlitteringGambit() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 10;
        exhaust = true;
        tags.add(CardTags.HEALING);
        tags.add(SneckoMod.RNG);
        SneckoMod.loadJokeCardImage(this, "GlitteringGambit.png");
    }

    public void upp() {
        upgradeBaseCost(0);
        tags.add(SneckoMod.SNEKPROOF);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new GlitteringGambitPotion());
        choices.add(new GlitteringGambitGold());

        this.addToBot(new ChooseOneAction(choices));
    }
}

class GlitteringGambitGold extends AbstractCard {
    public static final String ID = "FameAndFortune";
    private static final CardStrings cardStrings;

    public GlitteringGambitGold() {
        super("FameAndFortune", cardStrings.NAME, "colorless/skill/fame_and_fortune", -2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber * 2, true));
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        this.addToBot(new GainGoldAction(this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

    public AbstractCard makeCopy() {
        return new GlitteringGambitGold();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FameAndFortune");
    }
}

class GlitteringGambitPotion extends AbstractCard {
    public static final String ID = "Venomology";
    private static final CardStrings cardStrings;

    public GlitteringGambitPotion() {
        super("Venomology", cardStrings.NAME, "green/skill/alchemize", -2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

    public AbstractCard makeCopy() {
        return new GlitteringGambitPotion();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Venomology");
    }
}