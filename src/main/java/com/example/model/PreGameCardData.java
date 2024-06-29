package com.example.model;

public enum PreGameCardData {
    monsters_arachas("monsters_arachas", "monsters_arachas.jpg", 6, "soldier"),
    monsters_arachas_1("monsters_arachas_1", "monsters_arachas_1.jpg", 6, "soldier"),
    monsters_arachas_2("monsters_arachas_2", "monsters_arachas_2.jpg", 6, "soldier"),
    monsters_arachas_behemoth("monsters_arachas_behemoth", "monsters_arachas_behemoth.jpg", 6, "soldier"),
    monsters_bruxa("monsters_bruxa", "monsters_bruxa.jpg", 6, "soldier"),
    monsters_celaeno_harpy("monsters_celaeno_harpy", "monsters_celaeno_harpy.jpg", 6, "soldier"),
    monsters_cockatrice("monsters_cockatrice", "monsters_cockatrice.jpg", 6, "soldier"),
    monsters_draug("monsters_draug", "monsters_draug.jpg", 6, "soldier"),
    monsters_earth_elemental("monsters_earth_elemental", "monsters_earth_elemental.jpg", 6, "soldier"),
    monsters_ekkima("monsters_ekkima", "monsters_ekkima.jpg", 6, "soldier"),
    monsters_endrega("monsters_endrega", "monsters_endrega.jpg", 6, "soldier"),
    monsters_fiend("monsters_fiend", "monsters_fiend.jpg", 6, "soldier"),
    monsters_fire_elemental("monsters_fire_elemental", "monsters_fire_elemental.jpg", 6, "soldier"),
    monsters_fleder("monsters_fleder", "monsters_fleder.jpg", 6, "soldier"),
    monsters_fogling("monsters_fogling", "monsters_fogling.jpg", 6, "soldier"),
    monsters_forktail("monsters_forktail", "monsters_forktail.jpg", 6, "soldier"),
    monsters_frost_giant("monsters_frost_giant", "monsters_frost_giant.jpg", 6, "soldier"),
    monsters_frightener("monsters_frightener", "monsters_frightener.jpg", 6, "soldier"),
    monsters_gargoyle("monsters_gargoyle", "monsters_gargoyle.jpg", 6, "soldier"),
    monsters_ghoul("monsters_ghoul", "monsters_ghoul.jpg", 6, "soldier"),
    monsters_ghoul_1("monsters_ghoul_1", "monsters_ghoul_1.jpg", 6, "soldier"),
    monsters_ghoul_2("monsters_ghoul_2", "monsters_ghoul_2.jpg", 6, "soldier"),
    monsters_gravehag("monsters_gravehag", "monsters_gravehag.jpg", 6, "soldier"),
    monsters_gryffin("monsters_gryffin", "monsters_gryffin.jpg", 6, "soldier"),
    monsters_harpy("monsters_harpy", "monsters_harpy.jpg", 6, "soldier"),
    monsters_imlerith("monsters_imlerith", "monsters_imlerith.jpg", 6, "soldier"),
    monsters_katakan("monsters_katakan", "monsters_katakan.jpg", 6, "soldier"),
    monsters_kayran("monsters_kayran", "monsters_kayran.jpg", 6, "soldier"),
    monsters_leshen("monsters_leshen", "monsters_leshen.jpg", 6, "soldier"),
    monsters_mighty_maiden("monsters_mighty_maiden", "monsters_mighty_maiden.jpg", 6, "soldier"),
    monsters_nekker("monsters_nekker", "monsters_nekker.jpg", 6, "soldier"),
    monsters_nekker_1("monsters_nekker_1", "monsters_nekker_1.jpg", 6, "soldier"),
    monsters_nekker_2("monsters_nekker_2", "monsters_nekker_2.jpg", 6, "soldier"),
    monsters_poroniec("monsters_poroniec", "monsters_poroniec.jpg", 6, "soldier"),
    monsters_toad("monsters_toad", "monsters_toad.jpg", 6, "soldier"),
    monsters_werewolf("monsters_werewolf", "monsters_werewolf.jpg", 6, "soldier"),
    monsters_witch_velen("monsters_witch_velen" , "monsters_witch_velen.jpg", 6, "soldier"),
    monsters_witch_velen_1("monsters_witch_velen_1", "monsters_witch_velen_1.jpg", 6, "soldier"),
    monsters_witch_velen_2("monsters_witch_velen_2", "monsters_witch_velen_2.jpg", 6, "soldier"),
    monsters_wyvern("monsters_wyvern", "monsters_wyvern.jpg", 6, "soldier"),





    weather_fog("weather_fog", "weather_fog.jpg", 6, "weather"),
    weather_frost("weather_frost", "weather_frost.jpg", 6, "weather"),
    weather_rain("weather_rain", "weather_rain.jpg", 6, "weather"),
    weather_clear("weather_clear", "weather_clear.jpg", 6, "weather"),
    weather_storm("weather_storm", "weather_storm.jpg", 6, "weather"),;
    private final String name;
    private final String imageAddress;
    private final int power;
    private final String ability;


    PreGameCardData(String name, String imageAddress, int power, String ability) {
        this.name = name;
        this.imageAddress = imageAddress;
        this.power = power;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public int getPower() {
        return power;
    }

    public String getAbility() {
        return ability;
    }

}