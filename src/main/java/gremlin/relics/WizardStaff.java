package gremlin.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import gremlin.powers.WizPower;

public class WizardStaff extends AbstractGremlinRelic {
    public static final String ID = getID("WizardStaff");
    private static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static final AbstractRelic.RelicTier TIER = RelicTier.UNCOMMON;
    private static final String IMG = "relics/wizard_staff.png";
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;

    public WizardStaff() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0];
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (c.type == AbstractCard.CardType.ATTACK && AbstractDungeon.player.hasPower(WizPower.POWER_ID))
            return damage + AbstractDungeon.player.getPower(WizPower.POWER_ID).amount;
        return damage;
    }
}

