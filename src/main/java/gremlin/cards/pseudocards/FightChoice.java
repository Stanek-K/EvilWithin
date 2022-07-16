package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gremlin.actions.SetCardTargetCoordinatesAction;
import gremlin.cards.AbstractGremlinCard;
import gremlin.GremlinCharacter;
import gremlin.patches.AbstractCardEnum;

import static automaton.AutomatonMod.GOOD_STATUS;

public class FightChoice extends AbstractGremlinCard {
    public static final String ID = getID("FightChoice");
    final static String img = "gremlinResources/images/cards/RageBreak.png";

    public FightChoice() {
        super(ID, img, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE, AbstractCardEnum.GREMLIN);
        this.dontTriggerOnUseCard = true;

        this.baseMagicNumber = this.magicNumber = 3;
        // To not break with Bronze Idol
        this.tags.add(GOOD_STATUS);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        atb(new SetCardTargetCoordinatesAction(this, Settings.WIDTH/2.0f - 75f, -1f));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                if(mo instanceof GremlinNob ) {
                    atb(new TalkAction(mo, EXTENDED_DESCRIPTION[0]));
                    atb(new ApplyPowerAction(mo, mo, new StrengthPower(mo, magicNumber), magicNumber));
                }
                if(mo instanceof GremlinLeader) {
                    atb(new TalkAction(mo, EXTENDED_DESCRIPTION[1]));
                    atb(new ApplyPowerAction(mo, mo, new StrengthPower(mo, magicNumber), magicNumber));
                }
            }
        }
        if(AbstractDungeon.player instanceof GremlinCharacter){
            ((GremlinCharacter) AbstractDungeon.player).removeCower(true);
        }
    }

    public void upp() {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return true;
    }
}