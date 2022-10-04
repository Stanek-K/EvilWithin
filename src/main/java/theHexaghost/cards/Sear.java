package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import theHexaghost.HexaMod;
import theHexaghost.powers.BurnPower;

public class Sear extends AbstractHexaCard {
    public final static String ID = makeID("Sear");

    public Sear() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        baseTagMagic = tagMagic = 10;
        this.tags.add(HexaMod.DEALS_SOULBURN);
        isEthereal = true;
        this.tags.add(HexaMod.AFTERLIFE);
        HexaMod.loadJokeCardImage(this, "Sear.png");
    }

    public void upp() {
        upgradeTagMagic(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FireballEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
        burn(m, tagMagic);
    }

    @Override
    public void afterlife() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (m == null) return;
                applyToEnemyTop(m, new BurnPower(m, tagMagic));
                att(new VFXAction(new FireballEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
            }
        });
    }
}