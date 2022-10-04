package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import theHexaghost.HexaMod;

public class PowerFromBeyond extends AbstractHexaCard {
    public final static String ID = makeID("PowerFromBeyond");

    public PowerFromBeyond() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
        tags.add(HexaMod.AFTERLIFE);
    }

    public void upp() {
        upgradeMagicNumber(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void afterlife() {
        applyToSelf(new EnergizedBluePower(AbstractDungeon.player, 1));
        applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player, magicNumber));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedBluePower(AbstractDungeon.player, 1));
        applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player, magicNumber));
    }
}