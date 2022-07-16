package gremlin.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.HashSet;
import java.util.Set;

public class FatGremlinPower extends GremlinPower {
    static final String POWER_ID = getID("FatGremlin");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private Set<AbstractCreature> alreadyHit;

    public FatGremlinPower(int amount) {
        super();
        this.name = strings.NAME;
        this.ID = POWER_ID;
        this.pot = amount;
        this.updateDescription();
        this.alreadyHit = new HashSet<>();
    }

    public void updateDescription() {
        this.description = (strings.DESCRIPTIONS[0] + this.pot + strings.DESCRIPTIONS[1]);
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && !power.ID.equals("Shackled") && source == this.owner && target != this.owner && !target.hasPower("Artifact")) {
            this.flash();
            this.addToBot(new DamageAction(target, new DamageInfo(this.owner, this.pot, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
