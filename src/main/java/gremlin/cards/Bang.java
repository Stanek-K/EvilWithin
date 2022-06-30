package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Bang extends AbstractGremlinCard {
    public static final String ID = getID("Bang");

    private final boolean real;

    public Bang() {
        this(true);
    }

    public Bang(boolean real) {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.baseMagicNumber = this.magicNumber = 3;
        this.exhaust = true;
        this.real = real;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();

        if (this.real)
            this.cardsToPreview = new Whiz(false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; i++) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
        AbstractCard c = new Whiz();
        if(upgraded)
            c.upgrade();
       atb(new MakeTempCardInDiscardAction(c, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        if (real)
            this.cardsToPreview.upgrade();
    }
}
