package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class PinNeedle extends AbstractGremlinCard {
    public static final String ID = getID("PinNeedle");

    public PinNeedle() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust = true;
        this.cardsToPreview = new Pinprick();
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractCard card = new Pinprick();
        if(upgraded)
            card.upgrade();
        shuffleIn(card, this.magicNumber);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        this.cardsToPreview.upgrade();
    }
}
