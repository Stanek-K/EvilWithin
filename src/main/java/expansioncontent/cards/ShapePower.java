package expansioncontent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import downfall.actions.OctoChoiceAction;
import downfall.cards.OctoChoiceCard;
import downfall.util.OctopusCard;
import expansioncontent.expansionContentMod;

import java.util.ArrayList;

public class ShapePower extends AbstractExpansionCard implements OctopusCard {
    public final static String ID = makeID("ShapePower");

    public ShapePower() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_donudeca.png", "expansioncontentResources/images/1024/bg_boss_donudeca.png");
        tags.add(expansionContentMod.STUDY_SHAPES);
        tags.add(expansionContentMod.STUDY);
        baseMagicNumber = magicNumber = 2;
        baseDownfallMagic = downfallMagic = 5;
        //expansionContentMod.loadJokeCardImage(this, "PolyBeam.png");
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDownfall(3);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new OctoChoiceAction(m, this));
    }

    public ArrayList<OctoChoiceCard> choiceList() {
        ArrayList<OctoChoiceCard> cardList = new ArrayList<>();
        cardList.add(new OctoChoiceCard("expansioncontent:PolyBeam", EXTENDED_DESCRIPTION[0], "expansioncontentResources/images/cards/PolyBeam.png", EXTENDED_DESCRIPTION[1], -1, -1, magicNumber, CardType.POWER));
        if (upgraded) {
            cardList.add(new OctoChoiceCard("expansioncontent:DecaShield", EXTENDED_DESCRIPTION[2], "expansioncontentResources/images/cards/DecaShield.png", EXTENDED_DESCRIPTION[4], -1, -1, CardType.POWER));
        } else {
            cardList.add(new OctoChoiceCard("expansioncontent:DecaShield", EXTENDED_DESCRIPTION[2], "expansioncontentResources/images/cards/DecaShield.png", EXTENDED_DESCRIPTION[3], -1, -1, CardType.POWER));

        }

        return cardList;
    }

    public void doChoiceStuff(AbstractMonster m, OctoChoiceCard card) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (card.cardID) {
            case "expansioncontent:PolyBeam": {
                   atb(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
                 break;
            }
            case "expansioncontent:DecaShield": {
                atb(new ApplyPowerAction(p, p, new PlatedArmorPower(p, downfallMagic), downfallMagic));
            }
        }
    }
}