package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import gremlin.powers.WizPower;

import static gremlin.GremlinMod.WIZARD_GREMLIN;

public class Dazzle extends AbstractGremlinCard {
    public static final String ID = getID("Dazzle");

    public Dazzle() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.baseDownfallMagic = this.downfallMagic = 3;
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust = true;
        this.tags.add(WIZARD_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        if (p.hasPower(WizPower.POWER_ID)) {
            amount = p.getPower(WizPower.POWER_ID).amount;
        }
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (amount >= downfallMagic) {
            applyToEnemy(m, new StrengthPower(m, -magicNumber));
            if(!m.hasPower("Artifact"))
                applyToSelf(new StrengthPower(p, magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(WizPower.POWER_ID) && AbstractDungeon.player.getPower(WizPower.POWER_ID).amount >= downfallMagic) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
