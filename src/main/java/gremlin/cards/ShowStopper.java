package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import gremlin.powers.WizPower;
import sneckomod.SneckoMod;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class ShowStopper extends AbstractGremlinCard {
    public static final String ID = getID("ShowStopper");

    public ShowStopper() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = this.magicNumber = 5;
        this.baseDownfallMagic = this.downfallMagic = 7;
        this.isMultiDamage = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new GrandFinalEffect(), 1.0F));
        for(int i = 0; i < magicNumber; i++) {
            allDmg(AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if (!p.hasPower(WizPower.POWER_ID) || p.getPower(WizPower.POWER_ID).amount < downfallMagic) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        } else if(p.getPower(WizPower.POWER_ID).amount > downfallMagic) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
            return false;
        }
        return true;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}
