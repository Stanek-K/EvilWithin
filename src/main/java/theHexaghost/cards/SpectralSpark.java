package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.actions.ExtinguishCurrentFlameAction;
import theHexaghost.powers.EnhancePower;

public class SpectralSpark extends AbstractHexaCard {
    public final static String ID = makeID("SpectralSpark");

    public SpectralSpark() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExtinguishCurrentFlameAction(new ApplyPowerAction(p, p, new EnhancePower(magicNumber), magicNumber)));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = GhostflameHelper.activeGhostFlame.charged ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}