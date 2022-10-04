package theHexaghost.cards;

import automaton.actions.TimedVFXAction;
import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.TorchHeadFireEffect;
import sneckomod.SneckoMod;
import theHexaghost.GhostflameHelper;
import theHexaghost.ghostflames.AbstractGhostflame;
import theHexaghost.powers.BurnPower;

public class ChargedBarrage extends AbstractHexaCard {
    public final static String ID = makeID("ChargedBarrage");

    public ChargedBarrage() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseTagMagic = tagMagic = 5;
    }

    public void upp() {
        upgradeTagMagic(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TimedVFXAction(getVFXForThrow(p, m)));
        burn(m, tagMagic);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractGhostflame gf : GhostflameHelper.hexaGhostFlames) {
                    if (gf.charged) {
                        applyToEnemyTop(m, new BurnPower(m, tagMagic));
                        addToTop(new TimedVFXAction(getVFXForThrow(p, m)));
                    }
                }
            }
        });
    }

    private AbstractGameEffect getVFXForThrow(AbstractPlayer p, AbstractMonster m) {
        return new VfxBuilder(0.33F)
                .arc(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, Settings.HEIGHT * 0.8f)
                .emitEvery((x, y) -> new TorchHeadFireEffect(x + MathUtils.random(10 * Settings.scale), y + MathUtils.random(10 * Settings.scale)), 0.005f)
                .build();
    }
}