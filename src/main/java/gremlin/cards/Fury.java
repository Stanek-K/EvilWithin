package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class Fury extends AbstractGremlinCard {
    public static final String ID = getID("Fury");

    private static final int COST = 3;
    private int prevDiscount = 0;

    public Fury() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        //dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> cards = new ArrayList<>();
                for (AbstractCard c : p.hand.group) {
                    if (!c.freeToPlayOnce && c.costForTurn > 0) cards.add(c);
                }
                if (!cards.isEmpty())
                    cards.get( AbstractDungeon.cardRandomRng.random(cards.size()) ).setCostForTurn(0);
                isDone = true;
            }
        });
    }

    /*
    @Override
    public void applyPowers() {
        this.costForTurn += this.prevDiscount;

        super.applyPowers();
        if (!AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)) {
            if (this.costForTurn == COST) {
                this.isCostModifiedForTurn = false;
            }
            return;
        }
        if (AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount < 0) {
            if (this.costForTurn == COST) {
                this.isCostModifiedForTurn = false;
            }
            return;
        }

        this.prevDiscount = AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount / 2;

        if (this.costForTurn - this.prevDiscount < 0) this.prevDiscount = this.costForTurn;

        this.costForTurn = this.costForTurn - this.prevDiscount;
        if (this.costForTurn != COST) {
            this.isCostModifiedForTurn = true;
        }
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        this.prevDiscount = 0;
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        this.prevDiscount = 0;
    }
     */

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}
