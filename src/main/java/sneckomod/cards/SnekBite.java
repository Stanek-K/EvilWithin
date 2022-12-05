package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import sneckomod.actions.MuddleAction;
import sneckomod.actions.MuddleRandomCardAction;

public class SnekBite extends AbstractSneckoCard {
    public final static String ID = makeID("SnekBite");

    public SnekBite() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
        SneckoMod.loadJokeCardImage(this, "SnekBite.png");
    }

    public void upp() {
        upgradeDamage(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY), 0.3F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new MuddleRandomCardAction(1, true));
    }
}