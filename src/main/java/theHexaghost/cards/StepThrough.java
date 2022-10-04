package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.ghostflames.AbstractGhostflame;

public class StepThrough extends AbstractHexaCard {
    public final static String ID = makeID("StepThrough");

    public StepThrough() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractGhostflame g : GhostflameHelper.hexaGhostFlames) {
                    if (g.charged) {
                        att(new DrawCardAction(1));
                    }
                }
            }
        });
    }
}
