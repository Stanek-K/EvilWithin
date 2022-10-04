package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.HexaMod;
import theHexaghost.powers.EnhancePower;
import theHexaghost.powers.LoseEnhanceInTurnsPower;

public class TurnItUp extends AbstractHexaCard {
    public final static String ID = makeID("TurnItUp");

    public TurnItUp() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        HexaMod.loadJokeCardImage(this, "TurnItUp.png");
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnhancePower(magicNumber));
        applyToSelf(new LoseEnhanceInTurnsPower(magicNumber));
    }
}