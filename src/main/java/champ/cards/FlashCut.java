package champ.cards;

import champ.ChampMod;
import champ.powers.CounterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.loadJokeCardImage;

public class FlashCut extends AbstractChampCard {
    public final static String ID = makeID("FlashCut");

    public FlashCut() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseBlock = block = 4;
        baseMagicNumber = magicNumber = 4;
        tags.add(CardTags.STRIKE);
        tags.add(ChampMod.COMBO);
        tags.add(ChampMod.COMBODEFENSIVE);
        loadJokeCardImage(this, "FlashCut.png");
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        upgradeBlock(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (dcombo()) applyToSelf(new CounterPower(magicNumber));
        if (dcombo()) blck();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = dcombo() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}