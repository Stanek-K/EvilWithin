package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import sneckomod.actions.MuddleDrawnCardsFollowUpAction;

import java.util.Iterator;

public class QuickBite extends AbstractSneckoCard {
    public final static String ID = makeID("QuickBite");

    public QuickBite() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 3;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY), 0.3F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
                this.tickDuration();
                if (this.isDone) {
                    for (AbstractCard c : DrawCardAction.drawnCards) {
                        if (c.color != p.getCardColor()) {
                            AbstractDungeon.player.hand.moveToDiscardPile(c);
                            c.triggerOnManualDiscard();
                            GameActionManager.incrementDiscard(false);
                        }
                    }
                }
            }
        }));
    }
}