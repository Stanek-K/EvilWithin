package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theHexaghost.HexaMod;

public class GhostLash extends AbstractHexaCard {
    public final static String ID = makeID("GhostLash");

    public GhostLash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        isEthereal = true;
        baseMagicNumber = magicNumber = 6;
        HexaMod.loadJokeCardImage(this, "GhostLash.png");
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void afterlife() {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, magicNumber), magicNumber));
    }
}