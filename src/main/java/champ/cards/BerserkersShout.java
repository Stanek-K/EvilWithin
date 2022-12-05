package champ.cards;

import champ.ChampMod;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.*;

public class BerserkersShout extends AbstractChampCard {
    public final static String ID = makeID("BerserkersShout");

    public BerserkersShout() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        tags.add(ChampMod.OPENER);
        this.tags.add(ChampMod.OPENERBERSERKER);
    }

    public void upp() {
        upgradeMagicNumber(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vigor(magicNumber);
        berserkOpen();
    }
}