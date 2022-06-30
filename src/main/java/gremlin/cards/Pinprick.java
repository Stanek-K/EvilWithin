package gremlin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import gremlin.actions.MakeEchoAction;

import static gremlin.GremlinMod.SNEAKY_GREMLIN;

public class Pinprick extends AbstractGremlinCard {
    public static final String ID = getID("Pinprick");

    public Pinprick() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.exhaust = true;
        this.tags.add(SNEAKY_GREMLIN);
        setBackgrounds();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new DrawCardAction(p, 1));
    }

    @Override
    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        if(upgraded)
            AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(this));
    }
}
