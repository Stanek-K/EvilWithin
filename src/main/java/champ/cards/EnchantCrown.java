package champ.cards;

import champ.powers.ResolvePower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.fatigue;

public class EnchantCrown extends AbstractChampCard {
    public final static String ID = makeID("EnchantCrown");

    public EnchantCrown() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, CardCrawlGame.languagePack.getUIString("champ:EnchantUI").TEXT[0], c -> c.cost > 0, (cards) -> {
            cards.get(0).modifyCostForCombat(-999);
        }));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = false;
        for (AbstractCard c : p.hand.group) {
            if (c.cost > 0) {
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