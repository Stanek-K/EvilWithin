package slimebound.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import slimebound.SlimeboundMod;
import slimebound.orbs.SpawnedSlime;
import slimebound.patches.AbstractCardEnum;

import java.util.Collections;


public class SelfFormingGoo extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:SelfFormingGoo";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "cards/straygoop.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public SelfFormingGoo() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        baseBlock = 8;
        SlimeboundMod.loadJokeCardImage(this, "SelfFormingGoo.png");
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                int backSlime = -1;
                for (int i = 0; i < p.orbs.size(); i++) {
                    if (p.orbs.get(i) instanceof SpawnedSlime) {
                        backSlime = i;
                        break;
                    }
                }

                //0, 1, 2
                //1, 0, 2
                //1, 2, 0

                if (backSlime != -1) {
                    for (int i = 0; i < p.orbs.size() - 1; i++) {
                        if (p.orbs.get(i + 1) instanceof EmptyOrbSlot) break;
                        Collections.swap(p.orbs, i, i + 1);
                    }

                    for(int i = 0; i < p.orbs.size(); ++i) {
                        p.orbs.get(i).setSlot(i, p.maxOrbs);
                    }
                }
                /*
                int oldestSlime = -1;
                for (int i = 0; i < p.orbs.size(); i++) {
                    if (p.orbs.get(i) instanceof SpawnedSlime) {
                        oldestSlime = i;
                        break;
                    }
                }

                if (oldestSlime != -1) {
                    for (int i = oldestSlime; i > 0; i--) {
                        Collections.swap(p.orbs, i, i - 1);
                    }

                    for(int i = 0; i < p.orbs.size(); ++i) {
                        p.orbs.get(i).setSlot(i, p.maxOrbs);
                    }
                }
                */
            }
        });
    }

    public AbstractCard makeCopy() {
        return new SelfFormingGoo();
    }
}

