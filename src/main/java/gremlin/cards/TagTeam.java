package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import gremlin.GremlinMod;
import gremlin.cards.pseudocards.*;
import gremlin.orbs.*;
import gremlin.relics.TagTeamwork;

import java.util.ArrayList;

public class TagTeam extends AbstractGremlinCard {
    public static final String ID = getID("TagTeam");

    private boolean hasOptions = true;

    public TagTeam() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 2;
        updateModal();
        loadJokeCardImage(this, modID, "TagTeam.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(this.magicNumber));
        if(hasOptions){
            atb(new ChooseOneAction(updateModal()));
        }
    }

    public AbstractCard makeCopy()
    {
        return new TagTeam();
    }

    @Override
    public void applyPowers() {
        updateModal();
        super.applyPowers();
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    private ArrayList<AbstractCard> updateModal() {
        if(AbstractDungeon.player != null){
            ArrayList<GremlinStandby> living = new ArrayList<>();
            for(AbstractOrb orb : AbstractDungeon.player.orbs){
                if(orb instanceof GremlinStandby){
                    living.add((GremlinStandby) orb);
                }
            }
            if(living.size() == 0){
                hasOptions = false;
                return new ArrayList<>();
            }
            else {
                hasOptions = true;
                ArrayList<AbstractCard> options = new ArrayList<>();
                for(AbstractOrb grem : living){
                    if(grem instanceof MadGremlin){
                        options.add(new MadGremlinCard());
                    }
                    else if(grem instanceof FatGremlin){
                        options.add(new FatGremlinCard());
                    }
                    else if(grem instanceof ShieldGremlin){
                        options.add(new ShieldGremlinCard());
                    }
                    else if(grem instanceof SneakyGremlin){
                        options.add(new SneakyGremlinCard());
                    }
                    else if(grem instanceof GremlinWizard){
                        options.add(new GremlinWizardCard());
                    }
                }
                return options;
            }
        }
        hasOptions = false;
        return new ArrayList<>();
    }

    @Override
    public void triggerWhenDrawn() {
        if(AbstractDungeon.player.hasRelic(TagTeamwork.ID)) {
            AbstractDungeon.player.getRelic(TagTeamwork.ID).onTrigger();
        }
    }
}


