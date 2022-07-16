package gremlin.cards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import gremlin.actions.NecromancyAction;
import gremlin.GremlinCharacter;
import gremlin.orbs.GremlinStandby;
import gremlin.powers.BangPower;
import gremlin.powers.WizPower;
import sneckomod.SneckoMod;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Necromancy extends AbstractGremlinCard {
    public static final String ID = getID("Necromancy");

    public Necromancy() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 10;
        this.baseDownfallMagic = this.downfallMagic = 4;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        att(new RemoveSpecificPowerAction(p, p, WizPower.POWER_ID));
        att(new RemoveSpecificPowerAction(p, p, BangPower.POWER_ID));
        atb(new NecromancyAction(magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if (!p.hasPower(WizPower.POWER_ID) || p.getPower(WizPower.POWER_ID).amount < downfallMagic) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        } else if(!deadGrem()) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
            return false;
        } else if(!hasRoom()) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[2];
            return false;
        }
        return true;
    }

    private boolean deadGrem(){
        int count = 0;
        for(AbstractOrb orb : AbstractDungeon.player.orbs) {
            if(orb instanceof GremlinStandby) {
                count++;
            }
        }
        int total = 4;
        if(AbstractDungeon.player instanceof GremlinCharacter) {
            total -= ((GremlinCharacter) AbstractDungeon.player).mobState.numEnslaved();
        }
        return (count!=total);
    }

    private boolean hasRoom() {
        return AbstractDungeon.player.maxOrbs >= 4;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }
}
