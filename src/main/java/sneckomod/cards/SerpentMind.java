package sneckomod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.powers.SerpentMindPower;

public class SerpentMind extends AbstractSneckoCard {
    public final static String ID = makeID("SerpentMind");

    public SerpentMind() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(BaseModCardTags.FORM);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void upp() {
        this.tags.remove(SneckoMod.BANNEDFORSNECKO);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SerpentMindPower(1));
    }
}