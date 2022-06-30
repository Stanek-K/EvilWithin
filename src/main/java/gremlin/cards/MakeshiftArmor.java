package gremlin.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import gremlin.powers.MakeshiftArmorPower;

import static gremlin.GremlinMod.MAD_GREMLIN;

public class MakeshiftArmor extends AbstractGremlinCard {
    public static final String ID = getID("MakeshiftArmor");

    public MakeshiftArmor() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 0;
        this.tags.add(MAD_GREMLIN);
        setBackgrounds();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
            applyToSelf(new ArtifactPower(p, magicNumber));
        applyToSelf(new MakeshiftArmorPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

