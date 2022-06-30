package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.ShivPerCardPlayedAction;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class SecondVolley extends AbstractGremlinCard {
    public static final String ID = getID("SecondVolley");

    public SecondVolley() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.cardsToPreview = new Shiv();
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new ShivPerCardPlayedAction(upgraded));
        if(upgraded){
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        else {
            this.rawDescription = DESCRIPTION;
        }
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        if(upgraded){
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        else {
            this.rawDescription = DESCRIPTION;
        }
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
        if(upgraded){
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        else {
            this.rawDescription = DESCRIPTION;
        }
        this.initializeDescription();
    }

    @Override
    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        this.cardsToPreview.upgrade();
    }
}
