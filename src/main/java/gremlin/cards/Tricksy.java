package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.orbs.SneakyGremlin;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class Tricksy extends AbstractGremlinCard {
    public static final String ID = getID("Tricksy");

    public Tricksy() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;
        this.tags.add(SNEAKY_GREMLIN);
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
        atb(new GremlinSwapAction(new SneakyGremlin(0)));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}


