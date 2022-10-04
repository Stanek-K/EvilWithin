package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import theHexaghost.HexaMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.ghostflames.AbstractGhostflame;

public class Divider extends AbstractHexaCard {
    public final static String ID = makeID("Divider");

    public Divider() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        HexaMod.loadJokeCardImage(this, "Divider.png");
    }

    public void upp() {
        upgradeDamage(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                dmg(m, AbstractGameAction.AttackEffect.FIRE);
                for (AbstractGhostflame gf : GhostflameHelper.hexaGhostFlames) {
                    if (gf.charged) {
                        dmg(m, AbstractGameAction.AttackEffect.FIRE);
                    }
                }
            }
        });
    }
}