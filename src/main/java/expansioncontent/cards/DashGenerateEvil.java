package expansioncontent.cards;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import expansioncontent.actions.RandomCardWithTagAction;
import expansioncontent.expansionContentMod;

import java.util.ArrayList;


public class DashGenerateEvil extends AbstractExpansionCard {
    public final static String ID = makeID("DashGenerateEvil");

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.rarity != CardRarity.SPECIAL && q.hasTag(expansionContentMod.STUDY)) {
                AbstractCard r = q.makeCopy();
                if (upgraded) {
                    r.upgrade();
                }
                myList.add(r);
            }
        }
        return myList;
    }

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public DashGenerateEvil() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_attack.png", "expansioncontentResources/images/1024/bg_boss_attack.png");
        baseDamage = 10;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new HemokinesisEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
       dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        atb(new RandomCardWithTagAction(upgraded, expansionContentMod.STUDY, true));
    }

    public void upp() {
        upgradeDamage(2);
        rawDescription = UPGRADE_DESCRIPTION;
        for (AbstractCard q : this.dupeListForPrev) {
            q.upgrade();
        }
        initializeDescription();
    }

    @Override
    public void update() {
        super.update();
        if (dupeListForPrev.isEmpty()) {
            dupeListForPrev.addAll(getList());
        }
        if (hb.hovered) {
            if (rotationTimer <= 0F) {
                rotationTimer = 2F;
                if (dupeListForPrev.size() == 0) {
                    cardsToPreview = CardLibrary.cards.get("Madness");
                } else {
                    cardsToPreview = dupeListForPrev.get(previewIndex);
                }
                if (previewIndex == dupeListForPrev.size() - 1) {
                    previewIndex = 0;
                } else {
                    previewIndex++;
                }
            } else {
                rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void unhover() {
        super.unhover();
        cardsToPreview = null;
    }
}

