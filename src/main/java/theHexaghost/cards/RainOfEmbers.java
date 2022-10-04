package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.actions.EasyXCostAction;
import theHexaghost.HexaMod;
import theHexaghost.powers.BurnPower;

public class RainOfEmbers extends AbstractHexaCard {
    public final static String ID = makeID("RainOfEmbers");

    public RainOfEmbers() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseTagMagic = tagMagic = 6;
        this.tags.add(HexaMod.DEALS_SOULBURN);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeTagMagic(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (effect > 0) {
                applyToEnemyTop(m, new BurnPower(m, params[1]));
                atb(new DamageAction(m, new DamageInfo(p, params[0], DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            }
            return true;
        }, damage, tagMagic));
    }
}