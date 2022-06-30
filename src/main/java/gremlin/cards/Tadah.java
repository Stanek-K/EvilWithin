package gremlin.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.GremlinSwapAction;
import gremlin.actions.MakeEchoAction;
import gremlin.orbs.GremlinWizard;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Tadah extends AbstractGremlinCard {
    public static final String ID = getID("Tadah");

    public Tadah() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard skill = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.SKILL).makeCopy();
        while (skill.cost == -2) {
            skill = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.SKILL).makeCopy();
        }
        atb(new MakeEchoAction(skill, 1, magicNumber));
        atb(new GremlinSwapAction(new GremlinWizard(0)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}


