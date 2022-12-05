package sneckomod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.NoApplyRandomDamageAction;

public class WideSting extends AbstractSneckoCard {
    public final static String ID = makeID("WideSting");

    public WideSting() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL);
        baseMagicNumber = magicNumber = 7; //Min Damage
        baseDamage = 12; //Max Damage
        SneckoMod.loadJokeCardImage(this, "WideSting.png");
    }

    public void upp() {
        upgradeMagicNumber(4);
        upgradeDamage(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            atb(new NoApplyRandomDamageAction(q, magicNumber, damage, 1, AbstractGameAction.AttackEffect.LIGHTNING, this, DamageInfo.DamageType.NORMAL));
        }
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : p.hand.group) {
                    if (q.color != AbstractDungeon.player.getCardColor()) {
                        atb(new UpgradeSpecificCardAction(q));
                    }
                }
            }
        });
    }

    @Override
    public void applyPowers() {
        int CURRENT_MAGIC_NUMBER = baseMagicNumber;
        int CURRENT_DMG = baseDamage;
        baseDamage = CURRENT_MAGIC_NUMBER;
        super.applyPowers(); // takes baseDamage and applies things like Strength or Pen Nib to set damage

        magicNumber = damage; // magic number holds the first condition's modified damage, so !M! will work
        isMagicNumberModified = magicNumber != baseMagicNumber;

        // repeat so damage holds the second condition's damage
        baseDamage = CURRENT_DMG;
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(final AbstractMonster mo) {
        int CURRENT_MAGIC_NUMBER = baseMagicNumber;
        int CURRENT_DMG = baseDamage;
        baseDamage = CURRENT_MAGIC_NUMBER;
        super.calculateCardDamage(mo); // takes baseDamage and applies things like Strength or Pen Nib to set damage

        magicNumber = damage; // magic number holds the first condition's modified damage, so !M! will work
        isMagicNumberModified = magicNumber != baseMagicNumber;

        // repeat so damage holds the second condition's damage
        baseDamage = CURRENT_DMG;
        super.calculateCardDamage(mo);
    }
}