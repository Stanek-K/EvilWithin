package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.powers.CongaLinePower;
import sneckomod.SneckoMod;

public class CongaLine extends AbstractGremlinCard {
    public static final String ID = getID("CongaLine");

    public CongaLine() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CongaLinePower(p, 1));
    }

    public void upp() {
        this.isInnate = true;
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

