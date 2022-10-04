package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.HexaMod;

public class Incineration extends AbstractHexaCard {
    public final static String ID = makeID("Incineration");

    public Incineration() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseTagMagic = tagMagic = 4;
        baseMagicNumber = magicNumber = 3;
        this.tags.add(HexaMod.DEALS_SOULBURN);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            burn(m, tagMagic);
        }
    }
}