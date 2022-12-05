package sneckomod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.NopeAction;

public class Nope extends AbstractSneckoCard {
    public final static String ID = makeID("Nope");

    public Nope() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        tags.add(SneckoMod.SNEKPROOF);
        SneckoMod.loadJokeCardImage(this, "Nope.png");
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new NopeAction(upgraded));
    }
}