package theHexaghost.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import slimebound.SlimeboundMod;
import theHexaghost.HexaMod;

public class ShadowStrike extends AbstractHexaCard {
    public final static String ID = makeID("ShadowStrike");

    public ShadowStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseBlock = 5;
        isEthereal = true;
        tags.add(CardTags.STRIKE);
        HexaMod.loadJokeCardImage(this, "ShadowStrike.png");
    }

    public void upp() {
        upgradeDamage(4);
        upgradeBlock(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void afterlife() {
        superFlash(Color.PURPLE);
        blck();
    }
}