package com.example.model.game.place;

import com.example.model.card.WeatherCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SpellPlace {

    // TODO در هنگام پیاده سازی اینکه بتونیم کارت اسپل رو در اینجا قرار بدیم (در گرافیک) باید چک شه اگه هر کدام از لیدرها The Steel-Forged
    // بودن باید کارت اسپل به discardPlace
    // منتقل شه
    private ObservableList<WeatherCard> cards = FXCollections.observableArrayList();

    public ObservableList<WeatherCard> getCards() {
        return cards;
    }

    public void addCard(WeatherCard weatherCard) {
        cards.add(weatherCard);
    }
    public void removeCard(WeatherCard weatherCard) {
        cards.remove(weatherCard);
    }
    public boolean isSpellPlaceFull() {
        if (cards.size() == 3) {
            return true;
        } else {
            return false;
        }
    }
}
