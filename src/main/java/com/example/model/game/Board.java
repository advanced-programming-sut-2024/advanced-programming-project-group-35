package com.example.model.game;

import com.example.model.card.LeadersCard;
import com.example.model.game.place.CloseCombatCardPlace;
import com.example.model.game.place.RangedCardPlace;
import com.example.model.game.place.SiegeCardPlace;
import com.example.model.game.place.SpellPlace;
import com.example.model.user.User;

public class Board {
    private DiscardPile discardPile;
    private Hand hand;
    private SpellPlace spellPlace;
    private CloseCombatCardPlace closeCombatCardPlace;
    private RangedCardPlace rangedCardPlace;
    private SiegeCardPlace siegeCardPlace;
    private LeadersCard leadersCard;
    private User user;
    private int score;

}
