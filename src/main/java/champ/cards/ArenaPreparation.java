package champ.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import expansioncontent.cardmods.RetainCardMod;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class ArenaPreparation extends AbstractChampCard {
    public final static String ID = makeID("ArenaPreparation");

    public ArenaPreparation() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = getRandomSkills(magicNumber);
        for (AbstractCard c : cards) {
            c.isSeen = true;
            UnlockTracker.markCardAsSeen(c.cardID);
            if (!c.selfRetain) {
                CardModifierManager.addModifier(c, new RetainCardMod());
            }
            makeInHand(c);
        }
    }

    private static ArrayList<AbstractCard> getRandomSkills(int amount) {
        ArrayList<AbstractCard> list = new ArrayList<>(),
                returnList = new ArrayList<>();
        AbstractCard c;
        Iterator<AbstractCard> var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c.type == CardType.SKILL && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c.type == CardType.SKILL && !c.hasTag(CardTags.HEALING) && !(c instanceof ArenaPreparation)) {
                list.add(c);
            }
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = var2.next();
            if (c.type == CardType.SKILL && !c.hasTag(CardTags.HEALING)) {
                list.add(c);
            }
        }

        for (int i = 0; i < amount; i++) {
            returnList.add(list.remove(cardRandomRng.random(list.size() - 1)));
        }

        return returnList;
    }
}
