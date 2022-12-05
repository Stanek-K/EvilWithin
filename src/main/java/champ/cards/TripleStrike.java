package champ.cards;

import basemod.helpers.CardModifierManager;
import champ.util.TechniqueMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TripleStrike extends AbstractChampCard {
    public final static String ID = makeID("TripleStrike");

    public TripleStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
        AbstractCard r = new Strike();
        r.updateCost(-999);
        CardModifierManager.addModifier(r, new TechniqueMod());
        cardsToPreview = r;
        tags.add(CardTags.STRIKE);
    }

    public void upp() {
        upgradeBaseCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        AbstractCard r = new Strike();
        r.updateCost(-999);
        CardModifierManager.addModifier(r, new TechniqueMod());
        makeInHand(r, magicNumber);
    }
}