package sneckomod.cards;

import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

public class Restock extends AbstractSneckoCard {
    public final static String ID = makeID("Restock");

    public Restock() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        tags.add(SneckoMod.SNEKPROOF);
        this.exhaust = true;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExpertiseAction(p, magicNumber));
    }
}