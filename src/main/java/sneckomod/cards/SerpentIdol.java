package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.SerpentIdolAction;

public class SerpentIdol extends AbstractSneckoCard {
    public final static String ID = makeID("SerpentIdol");

    public SerpentIdol() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        tags.add(SneckoMod.SNEKPROOF);
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SerpentIdolAction());
    }
}