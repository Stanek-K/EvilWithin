package expansioncontent.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import expansioncontent.cards.AbstractDownfallCard;

public class CharacterMagic extends DynamicVariable {
    public String key() {
        return "DCM";
    }

    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).isCharacterMagicModified;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractDownfallCard)
            ((AbstractDownfallCard) card).isCharacterMagicModified = v;
    }

    public int value(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).characterMagic;
    }

    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).baseCharacterMagic;
    }

    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractDownfallCard) abstractCard).upgradedCharacterMagic;
    }
}
