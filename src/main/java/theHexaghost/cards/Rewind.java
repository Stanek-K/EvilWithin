package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.HexaMod;
import theHexaghost.actions.RetractAction;

import static automaton.AutomatonMod.makeBetaCardPath;

public class Rewind extends AbstractHexaCard {

    public final static String ID = makeID("Rewind");

    //stupid intellij stuff SKILL, SELF, COMMON

    public Rewind() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.exhaust = true;
        tags.add(HexaMod.GHOSTWHEELCARD);
        HexaMod.loadJokeCardImage(this, "Rewind.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RetractAction());
        atb(new BetterDiscardPileToHandAction(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}