package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.DamagePerCardPlayedAction;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class Flurry extends AbstractGremlinCard {
    public static final String ID = getID("Flurry");

    public Flurry() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.exhaust = true;
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamagePerCardPlayedAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        this.rawDescription = DESCRIPTION;
        this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += EXTENDED_DESCRIPTION[1];
        }
        else {
            this.rawDescription += EXTENDED_DESCRIPTION[2];
        }
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
