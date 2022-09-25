package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import sneckomod.actions.MuddleAction;

public class SnekBite extends AbstractSneckoCard {
    public final static String ID = makeID("SnekBite");

    public SnekBite() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY), 0.3F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        AbstractCard pCard = null;
        for (AbstractCard c: p.hand.group) {
            if (c == this && pCard != null)
                atb(new MuddleAction(pCard));
            if (pCard == this)
                atb(new MuddleAction(c));
            pCard = c;
        }
    }
}