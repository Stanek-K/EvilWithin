package champ.cards;

import basemod.helpers.CardModifierManager;
import champ.ChampMod;
import champ.powers.UltimateFormNextTurnPower;
import champ.powers.UltimateFormPower;
import champ.stances.BerserkerStance;
import champ.stances.DefensiveStance;
import champ.util.TechniqueMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;

public class UltimateStance extends AbstractChampCard {
    public final static String ID = makeID("UltimateStance");

    public UltimateStance() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UltimateFormNextTurnPower(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}