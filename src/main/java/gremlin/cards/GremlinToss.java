package gremlin.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SHIELD_GREMLIN;

public class GremlinToss extends AbstractGremlinCard {
    public static final String ID = getID("GremlinToss");

    public GremlinToss() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.baseMagicNumber = this.magicNumber = 0;
        this.tags.add(SHIELD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded) {
            atb(new AddTemporaryHPAction(p, p, magicNumber));
        }
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void applyPowers() {
        if(upgraded) {
            this.baseDamage = AbstractDungeon.player.currentBlock +
                    TempHPField.tempHp.get(AbstractDungeon.player) +
                    magicNumber;
        } else {
            this.baseDamage = AbstractDungeon.player.currentBlock + TempHPField.tempHp.get(AbstractDungeon.player);
        }
        super.applyPowers();
        if(upgraded) {
            this.rawDescription = UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = DESCRIPTION;
        }
        this.rawDescription += EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        if(upgraded) {
            this.rawDescription = UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = DESCRIPTION;
        }
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(final AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(upgraded) {
            this.rawDescription = UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = DESCRIPTION;
        }
        this.rawDescription += EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
        this.rawDescription = UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }
}
