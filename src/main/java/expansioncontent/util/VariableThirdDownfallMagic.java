package expansioncontent.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import expansioncontent.cards.AbstractDownfallCard;

public class VariableThirdDownfallMagic extends DynamicVariable {
    public String key() {
        return "DM3";
    }

    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).isThirdDownfallModified;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractDownfallCard)
            ((AbstractDownfallCard) card).isThirdDownfallModified = v;
    }

    public int value(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).thirdDownfall;
    }

    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).baseThirdDownfall;
    }

    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).isThirdDownfallUpgraded;
    }
}
