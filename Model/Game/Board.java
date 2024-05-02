package Model.Game;

import Model.Card.LeadersCard;
import Model.Game.Place.CloseCombatCardPlace;
import Model.Game.Place.RangedCardPlace;
import Model.Game.Place.SiegeCardPlace;
import Model.Game.Place.SpellPlace;
import Model.User.User;

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
