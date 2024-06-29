package com.example.model.card;

import com.example.model.card.enums.CardName;
import com.example.model.game.Table;

public class LeaderFactory {
    public static LeaderCard getLeaderCardByName(String cardName) {
        LeaderCard leaderCard;
        switch (cardName) {
            case "King Bran" -> {
                leaderCard = new LeaderCard("", CardName.KING_BRAN, null); // کاری انجام نمیده صرفا برای انجام توانایی آب و هوا باید چک شه اگه لیدر این بود پاور کمتر کم میشه
                return leaderCard;
            }
            default -> {
                return null;
            }
        }
    }
}
