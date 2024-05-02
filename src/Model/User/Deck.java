package Model.User;

import Model.Card.Card;
import Model.Card.Enum.FactionsType;
import Model.Card.LeadersCard;

import java.util.ArrayList;

public class Deck {
    private FactionsType faction;
    private LeadersCard leader;
    ArrayList<Card> cards = new ArrayList<Card>();
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void shuffle() {
        // shuffle the deck
    }
    public void changeFaction(FactionsType faction) {
        this.faction = faction;
    }
    public FactionsType getFaction() {
        return faction;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
}
