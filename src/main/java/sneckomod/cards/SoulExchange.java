package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.SoulExchangeAction;

public class SoulExchange extends AbstractSneckoCard {
    public final static String ID = makeID("SoulExchange");

    public SoulExchange() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        tags.add(SneckoMod.SNEKPROOF);
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SoulExchangeAction());
    }
}