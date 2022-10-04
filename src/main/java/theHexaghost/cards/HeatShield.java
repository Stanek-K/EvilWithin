package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.powers.BurnPower;

public class HeatShield extends AbstractHexaCard {
    public final static String ID = makeID("HeatShield");

    public HeatShield() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void upp() {
        upgradeBlock(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractMonster mo: monsterList())
                    if (mo.hasPower(BurnPower.POWER_ID))
                        att(new GainBlockAction(p, block));
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        burnGlowCheck();
    }
}