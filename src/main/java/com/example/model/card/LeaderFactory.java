package com.example.model.card;

import com.example.model.card.enums.CardName;
import com.example.model.card.enums.LeaderName;
import com.example.model.card.cardsAbilities.CommandersHornCardAbility;
import com.example.model.card.cardsAbilities.SpyAbility;
import com.example.model.game.*;
import com.example.model.game.place.Row;
import com.example.model.game.place.SpellPlace;

import java.util.ArrayList;
import java.util.Random;

public class LeaderFactory {
    public static LeaderCard getLeaderCardByName(String cardName) {
        LeaderCard leaderCard;
        switch (cardName) {
            case "King Bran" -> {
                leaderCard = new LeaderCard("", LeaderName.KING_BRAN, null); // TODO کاری انجام نمیده صرفا برای انجام توانایی آب و هوا باید چک شه اگه لیدر این بود پاور کمتر کم میشه
                return leaderCard;
            }
            case "Crach an Craite" -> {
                leaderCard = new LeaderCard("", LeaderName.CRACH_AN_CRAITE, new CrachAnCraiteAbility());
                return leaderCard;
            }
            case "Hope of the Aen Seidhe" -> {
                leaderCard = new LeaderCard("", LeaderName.HOPE_OF_THE_AEN_SEIDHE, new HopeOfTheAenSeidheAbility());
                return leaderCard;
            }
            case "Pureblood Elf" -> {
                leaderCard = new LeaderCard("", LeaderName.PUREBLOOD_ELF, new PurebloodElfAbility());
                return leaderCard;
            }
            case "Daisy of the Valley" -> {
                leaderCard = new LeaderCard("", LeaderName.DAISY_OF_THE_VALLEY, new DaisyOfTheValleyAbility());
                return leaderCard;
            }
            case "The Beautiful" -> {
                leaderCard = new LeaderCard("", LeaderName.THE_BEAUTIFUL, new TheBeautifulAbility());
                return leaderCard;
            }
            case "Queen of Dol Blathanna" -> {
                leaderCard = new LeaderCard("", LeaderName.QUEEN_OF_DOL_BLATHANNA, new QueenOfDolBlathanna());
                return leaderCard;
            }
            case "The Treacherous" -> {
                leaderCard = new LeaderCard("", LeaderName.THE_TREACHEROUS, new TheTreacherous());
                return leaderCard;
            }
            case "Commander of the Red Riders" -> {
                leaderCard = new LeaderCard("", LeaderName.COMMANDER_OF_THE_RED_RIDERS, new CommnderOfTheRedRiders());
                return leaderCard;
            }
            case "Destroyer of Worlds" -> {
                leaderCard = new LeaderCard("", LeaderName.DESTROYER_OF_WORLDS, new DestroyerOfTheWorlds());
                return leaderCard;
            }
            case "King of the wild Hunt" -> {
                leaderCard = new LeaderCard("", LeaderName.KING_OF_THE_WILD_HUNT, new KingOfTheWildHunt());
                return leaderCard;
            }
            case "Bringer of Death" -> {
                leaderCard = new LeaderCard("", LeaderName.BRINGER_OF_DEATH, new BringerOfDeath());
                return leaderCard;
            }
            case "Invader of the North" -> {
                leaderCard = new LeaderCard("", LeaderName.INVADER_OF_THE_NORTH, new InvaderOfTheNorth());
                return leaderCard;
            }
            case "The Relentless" -> {
                leaderCard = new LeaderCard("", LeaderName.THE_RELENTLESS, new TheRelentless());
                return leaderCard;
            }
            case "Emperor of Nilfgaard" -> {
                leaderCard = new LeaderCard("", LeaderName.EMPEROR_OF_NILFGAARD, new EmperorOfNilfGaard());
                return leaderCard;
            }
            case "His Imperial Majesty" -> {
                leaderCard = new LeaderCard("", LeaderName.HIS_IMPERIAL_MAJESTY, new HisImperialMajesty());
                return leaderCard;
            }
            case "The White Flame" -> {
                leaderCard = new LeaderCard("", LeaderName.THE_WHITE_FLAME, new TheWhiteFlame());
                return leaderCard;
            }
            case "Son of Medell" -> {
                leaderCard = new LeaderCard("", LeaderName.SON_OF_MEDELL, new SonOfMedellAbility());
                return leaderCard;
            }
            case "Lord Commander of the North" -> {
                leaderCard = new LeaderCard("", LeaderName.LORD_COMMANDER_OF_THE_NORTH, new LordCommanderOfTheNorth());
                return leaderCard;
            }
            case "King of Temeria" -> {
                leaderCard = new LeaderCard("", LeaderName.KING_OF_TEMERIA, new KingOfTermeriaAbility());
                return leaderCard;
            }
            case "The Steel-Forged" -> {
                leaderCard = new LeaderCard("", LeaderName.THE_STEEL_FORGED, new TheSteelForgedAbility());
                return leaderCard;
            }
            case "The Siegemaster" -> {
                leaderCard = new LeaderCard("", LeaderName.THE_SIEGEMASTER, new TheSiegeMaster());
                return leaderCard;
            }
            default -> {
                return null;
            }
        }
    }

    static void removeMaxPoweredCardInARow(Player player, Row row) {
        if (!row.isEmpty() && row.getStrength() >= 10) {
            int maximumPowerInRow = 0;
            UnitCard maxPoweredCard = new UnitCard(0, null, null, false, null, false);
            for (UnitCard card : player.getBoard().getRangedCardPlace().getCards()) {
                if (maxPoweredCard.getCurrentPower() >= maximumPowerInRow) {
                    maxPoweredCard = card;
                    maximumPowerInRow = maxPoweredCard.getCurrentPower();
                }
            }
            player.getBoard().getRangedCardPlace().removeCard(maxPoweredCard);
            player.getBoard().getDiscardPile().addCard(maxPoweredCard);
        }
    }
}
class TheSiegeMaster implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Hand hand = table.getCurrentPlayer().getBoard().getHand();
        SpellPlace spellPlace = table.getSpellPlace();
        Card weatherCard = hand.getCardByName(CardName.IMPENETRABLE_FOG);
        if (weatherCard != null) {
            if (!spellPlace.isSpellPlaceFull()) {
                hand.removeCard(weatherCard);
                spellPlace.addCard((WeatherCard) weatherCard);
                //TODO گرافیک انتقال کارت
                if (table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName() == LeaderName.THE_STEEL_FORGED) {
                    hand.addCard(weatherCard);
                    spellPlace.removeCard((WeatherCard) weatherCard);
                } else {
                    weatherCard.getAbility().apply(new AbilityContext(table, null, null));
                }
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
class TheSteelForgedAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO از بین بردن آب و هوا ها
    }
}
class KingOfTermeriaAbility implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Row siege = table.getCurrentPlayer().getBoard().getSiegeCardPlace();
        if (!(siege.getSpecialPlace().getAbility() instanceof CommandersHornCardAbility)) {
            for (UnitCard card : siege.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}
class LordCommanderOfTheNorth implements LeaderAbility {
    @Override
    public void apply(Table table) {
        LeaderFactory.removeMaxPoweredCardInARow(table.getOpponent(), table.getOpponent().getBoard().getSiegeCardPlace());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class SonOfMedellAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        LeaderFactory.removeMaxPoweredCardInARow(table.getOpponent(), table.getOpponent().getBoard().getRangedCardPlace());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class TheWhiteFlame implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Hand hand = table.getCurrentPlayer().getBoard().getHand();
        ArrayList<Card> handCopy = new ArrayList<>(hand.getCards());
        for (Card card : handCopy) {
            if (card.getCardName() == CardName.TORRENTIAL_RAIN) {
                if (!table.getSpellPlace().isSpellPlaceFull()) {
                    hand.removeCard(card);
                    table.getSpellPlace().addCard((WeatherCard) card);
                    card.getAbility().apply(new AbilityContext(table, null, null));
                }
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class HisImperialMajesty implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO باید سه کارت رندوم از هند حریف نمایش داده بشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class EmperorOfNilfGaard implements LeaderAbility {

    @Override
    public void apply(Table table) {
        table.getOpponent().getBoard().getDeck().getLeader().setCanDoAction(false);
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class TheRelentless implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO تمام کارت های غیر هیرو کارتای مرده حریف باید نشون داده بشه و یکی انتخاب شه و وارد هند بشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class InvaderOfTheNorth implements LeaderAbility {

    @Override
    public void apply(Table table) {
        removeRandomCardFromDiscardPileAndAddToHand(table.getCurrentPlayer().getBoard());
        removeRandomCardFromDiscardPileAndAddToHand(table.getOpponent().getBoard());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void removeRandomCardFromDiscardPileAndAddToHand(Board board) {
        Card randomCard = board.getDiscardPile().getCard(new Random().nextInt(board.getDiscardPile().getSize()));
        board.getHand().addCard(randomCard);
        board.getDiscardPile().removeCard(randomCard);
    }
}

class BringerOfDeath implements LeaderAbility {

    @Override
    public void apply(Table table) {
        Row close = table.getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        if (close.getSpecialPlace().getAbility() instanceof CommandersHornCardAbility) {
            for (UnitCard card : close.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class KingOfTheWildHunt implements LeaderAbility {

    @Override
    public void apply(Table table) {
        //TODO  کارت های غیر هیرو از کارتای مرده نشون داده میشه و یکی انتخاب میشه و وارد هند میشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class DestroyerOfTheWorlds implements LeaderAbility {

    @Override
    public void apply(Table table) {
        // TODO کارت های هند نشون داده میشه دوتا انتخاب میشه و از هند حذف میشن و کارت های دک نشون داده میشن یکی انتخاب میشه و وارد هند میشه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class CommnderOfTheRedRiders implements LeaderAbility {
    @Override
    public void apply(Table table) {
        ArrayList<WeatherCard> weatherCards = new ArrayList<>();
        for (Card card : table.getCurrentPlayer().getBoard().getHand().getCards()) {
            if (card instanceof WeatherCard) {
                weatherCards.add((WeatherCard) card);
            }
        }
        for (Card card : table.getCurrentPlayer().getBoard().getDeck().getCards()) {
            if (card instanceof WeatherCard) {
                weatherCards.add((WeatherCard) card);
            }
        }
        //TODO باید اینکارت ها نمایش داده بشه یدونش انتخاب شه و بازی شه
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class TheTreacherous implements LeaderAbility {
    @Override
    public void apply(Table table) {
        duplicateSpyCardsInRows(table.getCurrentPlayer().getBoard().getRows());
        duplicateSpyCardsInRows(table.getOpponent().getBoard().getRows());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void duplicateSpyCardsInRows(ArrayList<Row> rows) {
        for (Row row : rows) {
            for (UnitCard card : row.getCards()) {
                if (card.getAbility() instanceof SpyAbility)
                    card.duplicatePower();
            }
        }
    }
}

class QueenOfDolBlathanna implements LeaderAbility {
    @Override
    public void apply(Table table) {
        if (!table.getOpponent().getBoard().getRangedCardPlace().isEmpty() && table.getOpponent().getBoard().getRangedCardPlace().getStrength() >= 10) {
            LeaderFactory.removeMaxPoweredCardInARow(table.getCurrentPlayer(), table.getOpponent().getBoard().getCloseCombatCardPlace());
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class TheBeautifulAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        Row ranged = table.getCurrentPlayer().getBoard().getRangedCardPlace();
        if (!(ranged.getSpecialPlace().getAbility() instanceof CommandersHornCardAbility)) {
            for (UnitCard card : ranged.getCards()) {
                card.duplicatePower();
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class DaisyOfTheValleyAbility implements LeaderAbility {
    //TODO باید در اول راند چک شه و این توانایی انجام بشه
    @Override
    public void apply(Table table) {
        Deck deck = table.getCurrentPlayer().getBoard().getDeck();
        Card card = deck.getCard(new Random().nextInt(deck.getSize()));
        deck.removeCard(card);
        table.getCurrentPlayer().getBoard().getHand().addCard(card);
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class PurebloodElfAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        Hand hand = table.getCurrentPlayer().getBoard().getHand();
        SpellPlace spellPlace = table.getSpellPlace();
        Card weatherCard = hand.getCardByName(CardName.BITING_FROST);
        if (weatherCard != null) {
            if (!spellPlace.isSpellPlaceFull()) {
                hand.removeCard(weatherCard);
                spellPlace.addCard((WeatherCard) weatherCard);
                //TODO گرافیک انتقال کارت
                if (table.getCurrentPlayer().getBoard().getDeck().getLeader().getLeaderName() == LeaderName.THE_STEEL_FORGED) {
                    hand.addCard(weatherCard);
                    spellPlace.removeCard((WeatherCard) weatherCard);
                } else {
                    weatherCard.getAbility().apply(new AbilityContext(table, null, null));
                    // TODO تو ابیلیتی ودر کارت ها باید چک شه که لیدر کینگ برن نباشه
                }
            }
        }
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }
}

class HopeOfTheAenSeidheAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        Row close = table.getCurrentPlayer().getBoard().getCloseCombatCardPlace();
        Row ranged = table.getCurrentPlayer().getBoard().getRangedCardPlace();
        transferSiegeCards(close, ranged);
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void transferSiegeCards(Row close, Row ranged) {
        ArrayList<UnitCard> closeCopy = new ArrayList<>(close.getCards());
        ArrayList<UnitCard> rangedCopy = new ArrayList<>(ranged.getCards());
        for (UnitCard card : rangedCopy) {
            if (ranged.getSpecialPlace() == null && close.getSpecialPlace() != null) {
                ranged.removeCard(card);
                close.addCard(card);
            }
        }
        for (UnitCard card : closeCopy) {
            if (close.getSpecialPlace() == null && ranged.getSpecialPlace() != null) {
                close.removeCard(card);
                ranged.addCard(card);
            }
        }
    }
}

class CrachAnCraiteAbility implements LeaderAbility {
    @Override
    public void apply(Table table) {
        moveCardsFromDiscardPileToDeck(table.getCurrentPlayer().getBoard());
        moveCardsFromDiscardPileToDeck(table.getOpponent().getBoard());
        table.getCurrentPlayer().getBoard().getDeck().getLeader().setCanDoAction(false);
    }

    private void moveCardsFromDiscardPileToDeck(Board board) {
        Deck deck = board.getDeck();
        DiscardPile discardPile = board.getDiscardPile();
        ArrayList<Card> cardsToTransfer = new ArrayList<>(discardPile.getCards());
        for (Card card : cardsToTransfer) {
            deck.addCard(card);
            discardPile.removeCard(card);
        }
        deck.shuffle();
    }
}
