package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.PureSneckoAction;

public class PureSnecko extends AbstractSneckoCard {
    public final static String ID = makeID("PureSnecko");

    public PureSnecko() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(SneckoMod.SNEKPROOF);
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PureSneckoAction(p, upgraded));
    }
}