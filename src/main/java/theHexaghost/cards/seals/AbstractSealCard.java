package theHexaghost.cards.seals;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.downfallMod;
import theHexaghost.HexaMod;
import theHexaghost.cards.AbstractHexaCard;
import theHexaghost.relics.TheBrokenSeal;
import theHexaghost.vfx.BrokenSealEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSealCard extends AbstractHexaCard {
    private String[] descriptorStrings = CardCrawlGame.languagePack.getUIString(HexaMod.makeID("SealDescriptor")).TEXT;
    private String EtherealStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("EtherealMod")).TEXT[0];

    public AbstractSealCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, cost, type, rarity, target);
        if (downfallMod.disableDescriptors && !this.rawDescription.contains(descriptorStrings[1])) {
            // if translators delete only "Seal" it would add another Ethereal
            this.rawDescription = this.rawDescription.replace(EtherealStrings, EtherealStrings.replace("NL ", "") + descriptorStrings[1]);
            initializeDescription();
        }
        tags.add(CardTags.HEALING);
        isEthereal = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
            rawDescription = UPGRADE_DESCRIPTION;
            if (downfallMod.disableDescriptors && !this.rawDescription.contains(descriptorStrings[1]))
                rawDescription = descriptorStrings[1] + rawDescription;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        realUse(abstractPlayer, abstractMonster);
        if (!AbstractDungeon.player.hasRelic(TheBrokenSeal.ID)) {
            ArrayList<String> sealList = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (c instanceof AbstractSealCard) {
                    if (!(sealList.contains(c.cardID))) {
                        sealList.add(c.cardID);
                    }
                }
            }
            if (playedAll(sealList)) {
                ArrayList<String> notToRemoveList = new ArrayList<>();
                ArrayList<AbstractCard> removeList = new ArrayList<>();
                for (AbstractCard c : abstractPlayer.masterDeck.group) {
                    if (c instanceof AbstractSealCard && !notToRemoveList.contains(c.cardID)) {
                        notToRemoveList.add(c.cardID);
                        removeList.add(c);
                    }
                }
                AbstractDungeon.actionManager.cardsPlayedThisCombat.removeIf(c -> c instanceof AbstractSealCard);
//                for (AbstractPower p : AbstractDungeon.player.powers) {
//                    if (p instanceof RemoveMeBabey || p instanceof RepairPower) {
//                        addToTop(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, p, 1));
//                    }
//                }

                abstractPlayer.masterDeck.group.removeIf(removeList::contains);
                addToTop(new VFXAction(new BrokenSealEffect()));
            }
        }
    }


    public static boolean playedAll(ArrayList<String> sList) {
        return (sList.contains(FirstSeal.ID) && sList.contains(SecondSeal.ID) && sList.contains(ThirdSeal.ID) && sList.contains(FourthSeal.ID) && sList.contains(FifthSeal.ID) && sList.contains(SixthSeal.ID));
    }

    public abstract void realUse(AbstractPlayer p, AbstractMonster m);

    @Override
    public List<String> getCardDescriptors() {
        List<String> tags = new ArrayList<>();
        if (!downfallMod.disableDescriptors) {
            tags.add(descriptorStrings[0]);
        }
        return tags;
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        List<TooltipInfo> tips = new ArrayList<>();
        if (!downfallMod.disableDescriptors && !keywords.contains(HexaMod.makeID(descriptorStrings[0]).toLowerCase())) {
            tips.add(new TooltipInfo(BaseMod.getKeywordTitle("hexamod:seal"), BaseMod.getKeywordDescription("hexamod:seal")));
        }
        return tips;
    }
}
