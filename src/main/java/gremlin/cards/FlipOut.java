package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class FlipOut extends AbstractGremlinCard {
    public static final String ID = getID("FlipOut");

    public FlipOut() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 0;
        this.isMultiDamage = true;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentBlock;
        super.applyPowers();
        //this.rawDescription = DESCRIPTION;
        //this.rawDescription = this.rawDescription + UPGRADE_DESCRIPTION;
        //this.initializeDescription();
    }

    /*
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }
     */

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        //this.rawDescription = cardStrings.DESCRIPTION;
        //this.rawDescription = this.rawDescription + cardStrings.UPGRADE_DESCRIPTION;
        //this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
