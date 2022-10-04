package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;

public class Hexaguard extends AbstractHexaCard {
    public final static String ID = makeID("Hexaguard");

    public Hexaguard() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
        isEthereal = true;
        tags.add(HexaMod.AFTERLIFE);
    }

    public void upp() {
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(p, 1));
    }

    public void afterlife() {
        blck();
        atb(new DrawCardAction(AbstractDungeon.player, 1));
    }
}