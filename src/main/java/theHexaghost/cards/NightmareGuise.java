package theHexaghost.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHexaghost.HexaMod;

public class NightmareGuise extends AbstractHexaCard {
    public final static String ID = makeID("NightmareGuise");

    public NightmareGuise() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void afterlife() {
        superFlash(Color.PURPLE);
        applyToSelf(new NightmareGuisePower(AbstractDungeon.player, magicNumber));
    }
}



class NightmareGuisePower extends AbstractPower {
    public static final String POWER_ID = HexaMod.makeID("NightmareGuisePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    NightmareGuisePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "NightmareGuisePower";
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("nightmare");
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        flash();
        for (AbstractMonster mo: AbstractDungeon.getMonsters().monsters)
            if (mo != this.owner && !mo.isDeadOrEscaped())
                addToBot(new ApplyPowerAction(owner, owner, new VulnerablePower(owner, this.amount, false), this.amount));
    }
}