package com.example.model.card.enums;

import com.example.model.card.leaderCardsAbilities.*;

public enum LeaderCardsAbilities {
    COMMANDER_OF_THE_RED_RIDERS;

    public static LeaderAbility getLeaderAbilityByLeaderCardName(String leaderName) {
        switch (leaderName) {
            case "leaders_monsters_eredin_bronze" -> {
                return new KingOfTheWildHuntAbility();
            }
            case "leaders_monsters_eredin_gold" -> {
                return new DestroyerOfTheWorldsAbility();
            }
            case "leaders_monsters_eredin_silver" -> {
                return new BringerOfDeathAbility();
            }
            case "leaders_monsters_eredin_copper" -> {
                return new CommnderOfTheRedRidersAbility();
            }
            case "leaders_monsters_eredin_the_treacherous" -> {
                return new TheTreacherousAbility();
            }
            case "leaders_nilfgaard_emhyr_bronze" -> {
                return new EmperorOfNilfGaardAbility();
            }
            case "leaders_nilfgaard_emhyr_gold" -> {
                return new TheRelentlessAbility();
            }
            case "leaders_nilfgaard_emhyr_silver" -> {
                return new TheWhiteFlameAbility();
            }
            case "leaders_nilfgaard_emhyr_copper" -> {
                return new HisImperialMajestyAbility();
            }
            case "leaders_nilfgaard_emhyr_invader_of_the_north" -> {
                return new InvaderOfTheNorthAbility();
            }
            case "leaders_realms_foltest_bronze" -> {
                return new LordCommanderOfTheNorthAbility();
            }
            case "leaders_realms_foltest_gold" -> {
                return new TheSteelForgedAbility();
            }
            case "leaders_realms_foltest_silver" -> {
                return new TheSiegeMasterAbility();
            }
            case "leaders_realms_foltest_copper" -> {
                return new KingOfTermeriaAbility();
            }
            case "leaders_realms_foltest_son_of_medell" -> {
                return new SonOfMedellAbility();
            }
            case "leaders_scoiatael_francesca_bronze" -> {
                return new PurebloodElfAbility();
            }
            case "leaders_scoiatael_francesca_gold" -> {
                return new TheBeautifulAbility();
            }
            case "leaders_scoiatael_francesca_silver" -> {
                return new QueenOfDolBlathanna();
            }
            case "leaders_scoiatael_francesca_copper" -> {
                return new DaisyOfTheValleyAbility();
            }
            case "leaders_scoiatael_francesca_hope_of_the_aen_seidhe" -> {
                return new HopeOfTheAenSeidheAbility();
            }
            case "leaders_skellige_crach_an_craite" -> {
                return new CrachAnCraiteAbility();
            }
            case "leaders_skellige_king_bran" -> {
                return null;
            }
        }
        return null;
    }
}

