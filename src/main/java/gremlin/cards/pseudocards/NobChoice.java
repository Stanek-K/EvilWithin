package gremlin.cards.pseudocards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.cards.AbstractGremlinCard;
import gremlin.patches.Enums;
import gremlin.patches.Unmovable;

import java.util.ArrayList;

import static automaton.AutomatonMod.GOOD_STATUS;


public class NobChoice extends AbstractGremlinCard implements Unmovable {
    public static final String ID = getID("NobChoice");
    final static String img = "gremlinResources/images/cards/choice.png";

    public NobChoice() {
        super(ID, img, 0, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, Enums.AbstractCardEnum.GREMLIN);
        this.dontTriggerOnUseCard = true;
        this.purgeOnUse = true;
        // To not break with Bronze Idol
        this.tags.add(GOOD_STATUS);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> options = new ArrayList<>();
        if(AbstractDungeon.player == null || AbstractDungeon.player.gold > 0) {
            options.add(new CowerChoice());
        }
        options.add(new FightChoice());
        atb(new ChooseOneAction(options));
    }

    public void upp() {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return true;
    }
}



