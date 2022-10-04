package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CouncilsJustice extends AbstractHexaCard {
    public final static String ID = makeID("CouncilsJustice");

    public CouncilsJustice() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
        cardsToPreview = new Apparition();
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void applyPowers() {
        int oldBase = baseDamage;
        for (AbstractCard q : AbstractDungeon.player.exhaustPile.group) {
            if (q.cardID.equals(Apparition.ID)) {
                baseDamage += magicNumber;
            }
        }
        super.applyPowers();
        baseDamage = oldBase;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int oldBase = baseDamage;
        for (AbstractCard q : AbstractDungeon.player.exhaustPile.group) {
            if (q.cardID.equals(Apparition.ID)) {
                baseDamage += magicNumber;
            }
        }
        super.calculateCardDamage(mo);
        baseDamage = oldBase;
        isDamageModified = baseDamage != damage;
    }
}