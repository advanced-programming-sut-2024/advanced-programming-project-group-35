package com.example.model.card;

import com.example.model.card.enums.AbilityName;
import com.example.model.card.enums.CardData;
import com.example.model.game.Player;
import com.example.model.game.place.Place;

public class WeatherCard extends SpecialCard {
    private Player player;
    public WeatherCard(Place place, Ability ability, AbilityName abilityName, CardData cardData) {
        super(place, ability, abilityName, cardData);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
