package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.util.CardIgnore;

@Deprecated
@CardIgnore
public class SuperheatedStrike extends AbstractHexaCard {

    public final static String ID = makeID("SuperheatedStrike");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public SuperheatedStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseTagMagic = tagMagic = MAGIC;
        baseMagicNumber = magicNumber = MAGIC;
        isEthereal = true;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        burn(m, tagMagic);
    }

    @Override
    public void afterlife() {
        AbstractDungeon.player.damage(new DamageInfo(null, magicNumber, DamageInfo.DamageType.THORNS));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeTagMagic(UPG_MAGIC);
        }
    }
}