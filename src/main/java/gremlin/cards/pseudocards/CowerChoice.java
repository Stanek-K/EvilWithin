package gremlin.cards.pseudocards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import gremlin.actions.LoseAllGoldAction;
import gremlin.actions.SetCardTargetCoordinatesAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.characters.GremlinCharacter;

import static automaton.AutomatonMod.GOOD_STATUS;

public class CowerChoice extends AbstractGremlinCard {
    public static final String ID = getID("CowerChoice");

    public CowerChoice() {
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE);
        this.dontTriggerOnUseCard = true;
        AlwaysRetainField.alwaysRetain.set(this, true);
        // To not break with Bronze Idol
        this.tags.add(GOOD_STATUS);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractDungeon.actionManager.addToBottom(new SetCardTargetCoordinatesAction(this, Settings.WIDTH/2.0f - 75f, -1f));
        AbstractDungeon.getCurrRoom().mugged = true;

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                if (mo instanceof GremlinNob) {
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(mo, cardStrings.EXTENDED_DESCRIPTION[0]));
                    AbstractDungeon.actionManager.addToBottom(new LoseAllGoldAction(mo));
                    AbstractDungeon.actionManager.addToBottom(new WaitAction(1.5f));
                }
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(mo));
            }
        }
        if(AbstractDungeon.player instanceof GremlinCharacter){
            ((GremlinCharacter) AbstractDungeon.player).removeCower(false);
        }
        this.current_x = -1000;
    }

    public void upp() {
        upgradeName();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return true;
    }
}



