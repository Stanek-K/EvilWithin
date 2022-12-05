package champ.cards;

import champ.powers.ResolvePower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.fatigue;

public class EnchantShield extends AbstractChampCard {
    public final static String ID = makeID("EnchantShield");

    public EnchantShield() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 8;
        exhaust = true;
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, CardCrawlGame.languagePack.getUIString("champ:EnchantUI").TEXT[2], c -> c.baseBlock > 0, (cards) -> {
            cards.get(0).baseBlock += magicNumber;
        }));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = false;
        for (AbstractCard c : p.hand.group) {
            if (c.baseBlock > 0) {
                canUse = true;
                break;
            }
        }
        if (!canUse) {
            cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }
}