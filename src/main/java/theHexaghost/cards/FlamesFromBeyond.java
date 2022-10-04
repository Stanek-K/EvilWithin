package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;

public class FlamesFromBeyond extends AbstractHexaCard {
    public final static String ID = makeID("FlamesFromBeyond");

    public FlamesFromBeyond() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseTagMagic = tagMagic = 18;
        isEthereal = true;
        tags.add(HexaMod.AFTERLIFE);
    }

    public void upp() {
        upgradeTagMagic(6);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            burn(q, tagMagic);
        }
    }

    @Override
    public void afterlife() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            burn(m, tagMagic);
        }
    }
}