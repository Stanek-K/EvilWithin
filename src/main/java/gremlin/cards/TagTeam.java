package gremlin.cards;

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
import sneckomod.SneckoMod;

import java.util.ArrayList;

public class TagTeam extends AbstractGremlinCard {
    public static final String ID = getID("TagTeam");

    private boolean hasOptions = true;

    public TagTeam() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        updateModal();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        GremlinMod.loadJokeCardImage(this, "TagTeam.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(hasOptions){
            addToBot(new ChooseOneAction(updateModal()));
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
        selfRetain = true;
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    private ArrayList<AbstractCard> updateModal(){
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


