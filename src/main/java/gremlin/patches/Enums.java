package gremlin.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class Enums {
    public static class AbstractCardEnum {

        @SpireEnum
        public static AbstractCard.CardColor GREMLIN;

    }

    public static class GremlinEnum {

        @SpireEnum
        public static AbstractPlayer.PlayerClass GREMLIN;

    }

    public static class LibraryTypeEnum {

        @SpireEnum
        public static CardLibrary.LibraryType GREMLIN;

    }
}
