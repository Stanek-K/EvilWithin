package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Tricksy extends AbstractGremlinCard {
    public static final String ID = getID("Tricksy");

    public Tricksy() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(this.magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
                this.tickDuration();
                if (this.isDone) {
                    for (AbstractCard c : DrawCardAction.drawnCards) {
                        if (c.type != CardType.ATTACK) {
                            AbstractDungeon.player.hand.moveToDiscardPile(c);
                            c.triggerOnManualDiscard();
                            GameActionManager.incrementDiscard(false);
                        }
                    }
                }
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}


