package champ.cards;

import champ.ChampMod;
import champ.powers.CounterPower;
import champ.powers.EnergizedDurationPower;
import champ.powers.FalseCounterPower;
import champ.stances.BerserkerStance;
import champ.stances.DefensiveStance;
import champ.stances.UltimateStance;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import slimebound.powers.EnergizedSlimeboundPower;
import sneckomod.SneckoMod;

public class FalseCounter extends AbstractChampCard {
    public final static String ID = makeID("FalseCounter");

    public FalseCounter() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 12;
        baseDownfallMagic = downfallMagic = 1;
        baseMagicNumber = magicNumber = 2;
        tags.add(ChampMod.FINISHER);
    }

    public void upp() {
        upgradeDownfall(1);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new DrawCardNextTurnPower(p,downfallMagic));
        applyToSelf(new EnergizedDurationPower(this.magicNumber));
        finisher();
    }
}