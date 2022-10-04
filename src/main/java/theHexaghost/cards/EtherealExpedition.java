package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class EtherealExpedition extends AbstractHexaCard {
    public final static String ID = makeID("EtherealExpedition");

    public EtherealExpedition() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        isEthereal = true;
        exhaust = true;
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = returnTrulyRandomEtherealCardInCombat().makeCopy();
        q.setCostForTurn(0);
        this.addToBot(new MakeTempCardInHandAction(q, true));// 34
    }

    public static AbstractCard returnTrulyRandomEtherealCardInCombat() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
            if (c.isEthereal && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }

        for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
            if (c.isEthereal && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
            if (c.isEthereal && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }
        if (list.isEmpty()) {
            list.add(new PowerFromBeyond());
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }
}