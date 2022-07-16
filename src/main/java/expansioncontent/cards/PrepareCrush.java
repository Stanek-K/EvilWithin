package expansioncontent.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import expansioncontent.expansionContentMod;
import slimebound.cards.SlimeCrush;
import slimebound.powers.NextTurnGainSlimeCrush;

public class PrepareCrush extends AbstractExpansionCard {
    public final static String ID = makeID("PrepareCrush");

    public PrepareCrush() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_slime.png", "expansioncontentResources/images/1024/bg_boss_slime.png");

        tags.add(expansionContentMod.STUDY_SLIMEBOSS);
        tags.add(expansionContentMod.STUDY);

        baseBlock = 10;
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
        cardsToPreview = new SlimeCrush();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList.add(new MegaSpeechBubble(p.hb.cX, p.hb.cY, 1.0F, SlimeBoss.DIALOG[0], true));
        atb(new ShakeScreenAction(0.3F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));

        atb(new GainBlockAction(p, p, this.block));
        atb(new ApplyPowerAction(p, p, new EnergizedPower(p, magicNumber), magicNumber));
        atb(new ApplyPowerAction(p, p, new NextTurnGainSlimeCrush(p, p, 1, this.upgraded), 1));
    }

    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        upgradeBlock(5);

        AbstractCard q = new SlimeCrush();
        q.upgrade();
        cardsToPreview = q;
    }
}
