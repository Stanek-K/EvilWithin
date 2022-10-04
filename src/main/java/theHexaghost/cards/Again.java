package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.actions.ChargeCurrentFlameAction;
import theHexaghost.actions.ExtinguishCurrentFlameAction;

public class Again extends AbstractHexaCard {
    public final static String ID = makeID("Again");

    public Again() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExtinguishCurrentFlameAction());
        atb(new ChargeCurrentFlameAction());
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                atb(new ExtinguishCurrentFlameAction());
                isDone = true;
            }
        });
    }
}