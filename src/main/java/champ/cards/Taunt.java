package champ.cards;

import champ.powers.CounterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static champ.ChampMod.loadJokeCardImage;

public class Taunt extends AbstractChampCard {
    public final static String ID = makeID("Taunt");

    public Taunt() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseDownfallMagic = this.downfallMagic = 4;
        loadJokeCardImage(this, "Taunt.png");
    }

    public void upp() {
        target = CardTarget.ALL;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        upgradeDownfall(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            applyToEnemy(m, autoVuln(m, 1));
        }
        else {
           AbstractDungeon.getMonsters().monsters.stream().filter(m2 -> !m2.isDead && !m2.isDying).forEach(m2 -> {
               applyToEnemy(m2, autoVuln(m2, 1));
           });
        }
        applyToSelf(new CounterPower(downfallMagic));
    }
}