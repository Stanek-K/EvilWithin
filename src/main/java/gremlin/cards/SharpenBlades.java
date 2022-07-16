package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class SharpenBlades extends AbstractGremlinCard {
    public static final String ID = getID("SharpenBlades");

    public SharpenBlades() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard c : p.hand.group) {
                    if (c.type == CardType.ATTACK)
                        c.baseDamage += magicNumber;
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}

