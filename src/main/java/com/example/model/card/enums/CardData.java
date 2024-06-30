package com.example.model.card.enums;

import com.example.model.game.place.Place;

public enum CardData {
    leaders_monsters_eredin_bronze("leaders_monsters_eredin_bronze", "monsters_eredin_bronze.jpg", 0, "leader", "", "leader"),
    leaders_monsters_eredin_gold("leaders_monsters_eredin_gold", "monsters_eredin_gold.jpg", 0, "leader", "", "leader"),
    leaders_monsters_eredin_silver("leaders_monsters_eredin_silver", "monsters_eredin_silver.jpg", 0, "leader", "", "leader"),
    leaders_monsters_eredin_copper("leaders_monsters_eredin_copper", "monsters_eredin_copper.jpg", 0, "leader", "", "leader"),
    leaders_monsters_eredin_the_treacherous("leaders_monsters_eredin_the_treacherous", "monsters_eredin_the_treacherous.jpg", 0, "leader", "", "leader"),
    leaders_nilfgaard_emhyr_bronze("leaders_nilfgaard_emhyr_bronze", "nilfgaard_emhyr_bronze.jpg", 0, "leader", "", "leader"),
    leaders_nilfgaard_emhyr_gold("leaders_nilfgaard_emhyr_gold", "nilfgaard_emhyr_gold.jpg", 0, "leader", "", "leader"),
    leaders_nilfgaard_emhyr_silver("leaders_nilfgaard_emhyr_silver", "nilfgaard_emhyr_silver.jpg", 0, "leader", "", "leader"),
    leaders_nilfgaard_emhyr_copper("leaders_nilfgaard_emhyr_copper", "nilfgaard_emhyr_copper.jpg", 0, "leader", "", "leader"),
    leaders_nilfgaard_emhyr_invader_of_the_north("leaders_nilfgaard_emhyr_invader_of_the_north", "nilfgaard_emhyr_invader_of_the_north.jpg", 0, "leader", "", "leader"),
    leaders_realms_foltest_bronze("leaders_realms_foltest_bronze", "realms_foltest_bronze.jpg", 0, "leader", "", "leader"),
    leaders_realms_foltest_gold("leaders_realms_foltest_gold", "realms_foltest_gold.jpg", 0, "leader", "", "leader"),
    leaders_realms_foltest_silver("leaders_realms_foltest_silver", "realms_foltest_silver.jpg", 0, "leader", "", "leader"),
    leaders_realms_foltest_copper("leaders_realms_foltest_copper", "realms_foltest_copper.jpg", 0, "leader", "", "leader"),
    leaders_realms_foltest_son_of_medell("leaders_realms_foltest_son_of_medell", "realms_foltest_son_of_medell.jpg", 0, "leader", "", "leader"),
    leaders_scoiatael_francesca_bronze("leaders_scoiatael_francesca_bronze", "scoiatael_francesca_bronze.jpg", 0, "leader", "", "leader"),
    leaders_scoiatael_francesca_gold("leaders_scoiatael_francesca_gold", "scoiatael_francesca_gold.jpg", 0, "leader", "", "leader"),
    leaders_scoiatael_francesca_silver("leaders_scoiatael_francesca_silver", "scoiatael_francesca_silver.jpg", 0, "leader", "", "leader"),
    leaders_scoiatael_francesca_copper("leaders_scoiatael_francesca_copper", "scoiatael_francesca_copper.jpg", 0, "leader", "", "leader"),
    leaders_scoiatael_francesca_hope_of_the_aen_seidhe("leaders_scoiatael_francesca_hope_of_the_aen_seidhe", "scoiatael_francesca_hope_of_the_aen_seidhe.jpg", 0, "leader", "", "leader"),
    leaders_skellige_crach_an_craite("leaders_skellige_crach_an_craite", "skellige_crach_an_craite.jpg", 0, "leader", "", "leader"),
    leaders_skellige_king_bran("leaders_skellige_king_bran", "skellige_king_bran.jpg", 0, "leader", "", "leader"),

    monsters_arachas("monsters_arachas", "monsters_arachas.jpg", 4, "soldier", "muster", "melee"),
    monsters_arachas_1("monsters_arachas_1", "monsters_arachas_1.jpg", 4, "soldier", "muster", "melee"),
    monsters_arachas_2("monsters_arachas_2", "monsters_arachas_2.jpg", 4, "soldier", "muster", "melee"),
    monsters_arachas_behemoth("monsters_arachas_behemoth", "monsters_arachas_behemoth.jpg", 6, "soldier", "muster", "siege"),
    monsters_bruxa("monsters_vampire_bruxa", "monsters_bruxa.jpg", 4, "soldier", "muster", "melee"),
    monsters_celaeno_harpy("monsters_celaeno_harpy", "monsters_celaeno_harpy.jpg", 2, "soldier", "", "agile"),
    monsters_cockatrice("monsters_cockatrice", "monsters_cockatrice.jpg", 2, "soldier", "", "ranged"),
    monsters_draug("monsters_draug", "monsters_draug.jpg", 10, "hero", "", "melee"),
    monsters_earth_elemental("monsters_earth_elemental", "monsters_earth_elemental.jpg", 6, "soldier", "", "siege"),
    monsters_ekkima("monsters_vampire_ekkimara", "monsters_ekkima.jpg", 4, "soldier", "muster", "melee"),
    monsters_endrega("monsters_endrega", "monsters_endrega.jpg", 2, "soldier", "", "ranged"),
    monsters_fiend("monsters_fiend", "monsters_fiend.jpg", 6, "soldier", "", "melee"),
    monsters_fire_elemental("monsters_fire_elemental", "monsters_fire_elemental.jpg", 6, "soldier", "", "siege"),
    monsters_fleder("monsters_vampire_fleder", "monsters_fleder.jpg", 4, "soldier", "muster", "melee"),
    monsters_fogling("monsters_foglet", "monsters_fogling.jpg", 2, "soldier", "", "melee"),
    monsters_forktail("monsters_forktail", "monsters_forktail.jpg", 5, "soldier", "", "melee"),
    monsters_frost_giant("monsters_ice_giant", "monsters_frost_giant.jpg", 5, "soldier", "", "siege"),
    monsters_frightener("monsters_frightener", "monsters_frightener.jpg", 5, "soldier", "", "melee"),
    monsters_gargoyle("monsters_gargoyle", "monsters_gargoyle.jpg", 2, "soldier", "", "ranged"),
    monsters_ghoul("monsters_ghoul", "monsters_ghoul.jpg", 1, "soldier", "muster", "melee"),
    monsters_garkain("monsters_vampire_garkain", "monsters_garkain.jpg", 4, "soldier", "muster", "melee"),
    monsters_ghoul_1("monsters_ghoul_1", "monsters_ghoul_1.jpg", 1, "soldier", "muster", "melee"),
    monsters_ghoul_2("monsters_ghoul_2", "monsters_ghoul_2.jpg", 1, "soldier", "muster", "melee"),
    monsters_gravehag("monsters_gravehag", "monsters_gravehag.jpg", 5, "soldier", "", "ranged"),
    monsters_gryffin("monsters_gryffin", "monsters_gryffin.jpg", 5, "soldier", "", "melee"),
    monsters_harpy("monsters_harpy", "monsters_harpy.jpg", 2, "soldier", "", "agile"),
    monsters_imlerith("monsters_imlerith", "monsters_imlerith.jpg", 10, "hero", "", "melee"),
    monsters_katakan("monsters_vampire_katakan", "monsters_katakan.jpg", 5, "soldier", "muster", "melee"),
    monsters_kayran("monsters_kayran", "monsters_kayran.jpg", 8, "hero", "", "agile"),
    monsters_leshen("monsters_leshen", "monsters_leshen.jpg", 10, "hero", "", "melee"),
    monsters_mighty_maiden("monsters_plague_maiden", "monsters_mighty_maiden.jpg", 5, "soldier", "", "melee"),
    monsters_nekker("monsters_nekker", "monsters_nekker.jpg", 2, "soldier", "muster", "melee"),
    monsters_nekker_1("monsters_nekker_1", "monsters_nekker_1.jpg", 2, "soldier", "muster", "melee"),
    monsters_nekker_2("monsters_nekker_2", "monsters_nekker_2.jpg", 2, "soldier", "muster", "melee"),
    monsters_poroniec("monsters_botchling", "monsters_poroniec.jpg", 4, "soldier", "", "melee"),
    monsters_toad("monsters_toad", "monsters_toad.jpg", 7, "soldier", "scorch", "ranged"),
    monsters_werewolf("monsters_werewolf", "monsters_werewolf.jpg", 5, "soldier", "", "melee"),
    monsters_witch_velen("monsters_crone_brewess", "monsters_witch_velen.jpg", 6, "soldier", "muster", "melee"),
    monsters_witch_velen_1("monsters_crone_weavess", "monsters_witch_velen_1.jpg", 6, "soldier", "muster", "melee"),
    monsters_witch_velen_2("monsters_crone_whispess", "monsters_witch_velen_2.jpg", 6, "soldier", "muster", "melee"),
    monsters_wyvern("monsters_wyvern", "monsters_wyvern.jpg", 2, "soldier", "", "ranged"),

    neutral_chort("neutral_chort", "neutral_chort.jpg", 8, "soldier", "", "melee"),
    neutral_ciri("neutral_ciri", "neutral_ciri.jpg", 15, "hero", "", "melee"),
    neutral_cow("neutral_cow", "neutral_cow.jpg", 0, "soldier", "transformer", "ranged"),
    neutral_dandelion("neutral_dandelion", "neutral_dandelion.jpg", 2, "soldier", "commanders_horn", "melee"),
    neutral_emiel("neutral_emiel", "neutral_emiel.jpg", 5, "soldier", "", "melee"),
    neutral_gaunter_odimm("neutral_gaunter_odimm", "neutral_gaunter_odimm.jpg", 2, "soldier", "muster", "siege"),
    neutral_gaunter_odimm_darkness("neutral_gaunter_odimm_darkness", "neutral_gaunter_odimm_darkness.jpg", 4, "soldier", "muster", "ranged"),
    neutral_geralt("neutral_geralt", "neutral_geralt.jpg", 15, "hero", "", "melee"),
    neutral_mysterious_elf("neutral_mysterious_elf", "neutral_mysterious_elf.jpg", 0, "hero", "spy", "melee"),
    neutral_olgierd("neutral_olgierd", "neutral_olgierd.jpg", 6, "soldier", "moral_boost", "agile"),
    neutral_triss("neutral_triss", "neutral_triss.jpg", 7, "hero", "", "melee"),
    neutral_vesemir("neutral_vesemir", "neutral_vesemir.jpg", 6, "soldier", "", "melee"),
    neutral_villen("neutral_villen", "neutral_villen.jpg", 7, "soldier", "scorch", "melee"),
    neutral_yennefer("neutral_yennefer", "neutral_yennefer.jpg", 7, "hero", "medic", "ranged"),
    neutral_zoltan("neutral_zoltan", "neutral_zoltan.jpg", 5, "soldier", "", "melee"),

    nilfgaard_albrich("nilfgaard_albrich", "nilfgaard_albrich.jpg", 2, "soldier", "", "ranged"),
    nilfgaard_archer_support("nilfgaard_archer_support", "nilfgaard_archer_support.jpg", 1, "soldier", "medic", "ranged"),
    nilfgaard_archer_support_1("nilfgaard_archer_support_1", "nilfgaard_archer_support_1.jpg", 1, "soldier", "medic", "ranged"),
    nilfgaard_assire("nilfgaard_assire", "nilfgaard_assire.jpg", 6, "soldier", "", "ranged"),
    nilfgaard_black_archer("nilfgaard_black_archer", "nilfgaard_black_archer.jpg", 10, "soldier", "", "ranged"),
    nilfgaard_black_archer_1("nilfgaard_black_archer_1", "nilfgaard_black_archer_1.jpg", 10, "soldier", "", "ranged"),
    nilfgaard_cahir("nilfgaard_cahir", "nilfgaard_cahir.jpg", 6, "soldier", "", "melee"),
    nilfgaard_cynthia("nilfgaard_cynthia", "nilfgaard_cynthia.jpg", 4, "soldier", "", "ranged"),
    nilfgaard_fringilla("nilfgaard_fringilla", "nilfgaard_fringilla.jpg", 6, "soldier", "", "ranged"),
    nilfgaard_heavy_zerri("nilfgaard_heavy_zerri", "nilfgaard_heavy_zerri.jpg", 10, "soldier", "", "siege"),
    nilfgaard_imperal_brigade("nilfgaard_imperal_brigade", "nilfgaard_imperal_brigade.jpg", 3, "soldier", "tight_bound", "melee"),
    nilfgaard_letho("nilfgaard_letho", "nilfgaard_letho.jpg", 10, "hero", "", "melee"),
    nilfgaard_menno("nilfgaard_menno", "nilfgaard_menno.jpg", 10, "hero", "medic", "melee"),
    nilfgaard_moorvran("nilfgaard_moorvran", "nilfgaard_moorvran.jpg", 10, "hero", "", "siege"),
    nilfgaard_nauzicaa_2("nilfgaard_nauzicaa_2", "nilfgaard_nauzicaa_2.jpg", 3, "soldier", "tight_bound", "melee"),
    nilfgaard_morteisen("nilfgaard_morteisen", "nilfgaard_morteisen.jpg", 2, "soldier", "", "melee"),
    nilfgaard_puttkammer("nilfgaard_puttkammer", "nilfgaard_puttkammer.jpg", 3, "soldier", "", "ranged"),
    nilfgaard_rainfarn("nilfgaard_rainfarn", "nilfgaard_rainfarn.jpg", 4, "soldier", "", "melee"),
    nilfgaard_renuald("nilfgaard_renuald", "nilfgaard_renuald.jpg", 5, "soldier", "", "ranged"),
    nilfgaard_rotten("nilfgaard_rotten", "nilfgaard_rotten.jpg", 3, "soldier", "spy", "siege"),
    nilfgaard_shilard("nilfgaard_shilard", "nilfgaard_shilard.jpg", 7, "soldier", "", "melee"),
    nilfgaard_siege_engineer("nilfgaard_siege_engineer", "nilfgaard_siege_engineer.jpg", 6, "soldier", "", "siege"),
    nilfgaard_siege_support("nilfgaard_siege_support", "nilfgaard_siege_support.jpg", 0, "soldier", "medic", "siege"),
    nilfgaard_stefan("nilfgaard_stefan", "nilfgaard_stefan.jpg", 9, "soldier", "spy", "melee"),
    nilfgaard_sweers("nilfgaard_sweers", "nilfgaard_sweers.jpg", 2, "soldier", "", "ranged"),
    nilfgaard_tibor("nilfgaard_tibor", "nilfgaard_tibor.jpg", 10, "hero", "", "melee"),
    nilfgaard_vanhemar("nilfgaard_vanhemar", "nilfgaard_vanhemar.jpg", 4, "soldier", "", "ranged"),
    nilfgaard_vattier("nilfgaard_vattier", "nilfgaard_vattier.jpg", 4, "soldier", "spy", "melee"),
    nilfgaard_vreemde("nilfgaard_vreemde", "nilfgaard_vreemde.jpg", 2, "soldier", "", "melee"),
    nilfgaard_young_emissary("nilfgaard_young_emissary", "nilfgaard_young_emissary.jpg", 5, "soldier", "tight_bound", "melee"),
    nilfgaard_young_emissary_1("nilfgaard_young_emissary_1", "nilfgaard_young_emissary_1.jpg", 5, "soldier", "tight_bound", "melee"),
    nilfgaard_zerri("nilfgaard_zerri", "nilfgaard_zerri.jpg", 5, "soldier", "", "siege"),

    realms_ballista("realms_ballista", "realms_ballista.jpg", 6, "soldier", "", "siege"),
    realms_banner_nurse("realms_banner_nurse", "realms_banner_nurse.jpg", 5, "soldier", "medic", "siege"),
    realms_blue_stripes("realms_blue_stripes", "realms_blue_stripes.jpg", 4, "soldier", "tight_bound", "melee"),
    realms_catapult_1("realms_catapult_1", "realms_catapult_1.jpg", 8, "soldier", "tight_bound", "siege"),
    realms_crinfrid("realms_crinfrid", "realms_crinfrid.jpg", 5, "soldier", "tight_bound", "ranged"),
    realms_dethmold("realms_dethmold", "realms_dethmold.jpg", 6, "soldier", "", "ranged"),
    realms_dijkstra("realms_dijkstra", "realms_dijkstra.jpg", 4, "soldier", "spy", "melee"),
    realms_esterad("realms_esterad", "realms_esterad.jpg", 10, "hero", "", "melee"),
    realms_kaedwen_siege("realms_kaedwen_siege", "realms_kaedwen_siege.jpg", 1, "soldier", "medic", "siege"),
    realms_kaedwen_siege_1("realms_kaedwen_siege_1", "realms_kaedwen_siege_1.jpg", 1, "soldier", "medic", "siege"),
    realms_kaedwen_siege_2("realms_kaedwen_siege_2", "realms_kaedwen_siege_2.jpg", 1, "soldier", "medic", "siege"),
    realms_keira("realms_keira", "realms_keira.jpg", 5, "soldier", "", "ranged"),
    realms_natalis("realms_natalis", "realms_natalis.jpg", 10, "hero", "", "melee"),
    realms_philippa("realms_philippa", "realms_philippa.jpg", 10, "hero", "", "ranged"),
    realms_stennis("realms_stennis", "realms_stennis.jpg", 5, "soldier", "spy", "melee"),
    realms_poor_infantry("realms_poor_infantry", "realms_poor_infantry.jpg", 1, "soldier", "tight_bound", "melee"),
    realms_redania("realms_redania", "realms_redania.jpg", 1, "soldier", "", "melee"),
    realms_redania_1("realms_redania_1", "realms_redania_1.jpg", 1, "soldier", "", "melee"),
    realms_sabrina("realms_sabrina", "realms_sabrina.jpg", 4, "soldier", "", "ranged"),
    realms_sheala("realms_sheala", "realms_sheala.jpg", 5, "soldier", "", "ranged"),
    realms_sheldon("realms_sheldon", "realms_sheldon.jpg", 4, "soldier", "", "ranged"),
    realms_siege_tower("realms_siege_tower", "realms_siege_tower.jpg", 6, "soldier", "", "siege"),
    realms_thaler("realms_thaler", "realms_thaler.jpg", 1, "soldier", "spy", "siege"),
    realms_trebuchet("realms_trebuchet", "realms_trebuchet.jpg", 6, "soldier", "", "siege"),
    realms_trebuchet_1("realms_trebuchet_1", "realms_trebuchet_1.jpg", 6, "soldier", "", "siege"),
    realms_vernon("realms_vernon", "realms_vernon.jpg", 10, "hero", "", "melee"),
    realms_ves("realms_ves", "realms_ves.jpg", 5, "soldier", "", "melee"),
    realms_yarpen("realms_yarpen", "realms_yarpen.jpg", 2, "soldier", "", "melee"),

    scoiatael_barclay("scoiatael_barclay", "scoiatael_barclay.jpg", 6, "soldier", "", "agile"),
    scoiatael_ciaran("scoiatael_ciaran", "scoiatael_ciaran.jpg", 3, "soldier", "", "agile"),
    scoiatael_dennis("scoiatael_dennis", "scoiatael_dennis.jpg", 6, "soldier", "", "melee"),
    scoiatael_dol_archer("scoiatael_dol_archer", "scoiatael_dol_archer.jpg", 4, "soldier", "", "ranged"),
    scoiatael_dol_infantry("scoiatael_dol_infantry", "scoiatael_dol_infantry.jpg", 6, "soldier", "", "agile"),
    scoiatael_dol_infantry_1("scoiatael_dol_infantry_1", "scoiatael_dol_infantry_1.jpg", 6, "soldier", "", "agile"),
    scoiatael_dol_infantry_2("scoiatael_dol_infantry_2", "scoiatael_dol_infantry_2.jpg", 6, "soldier", "", "agile"),
    scoiatael_dwarf("scoiatael_dwarf", "scoiatael_dwarf.jpg", 3, "soldier", "muster", "melee"),
    scoiatael_dwarf_1("scoiatael_dwarf_1", "scoiatael_dwarf_1.jpg", 3, "soldier", "muster", "melee"),
    scoiatael_dwarf_2("scoiatael_dwarf_2", "scoiatael_dwarf_2.jpg", 3, "soldier", "muster", "melee"),
    scoiatael_eithne("scoiatael_eithne", "scoiatael_eithne.jpg", 10, "hero", "", "ranged"),
    scoiatael_elf_skirmisher("scoiatael_elf_skirmisher", "scoiatael_elf_skirmisher.jpg", 2, "soldier", "muster", "ranged"),
    scoiatael_elf_skirmisher_1("scoiatael_elf_skirmisher_1", "scoiatael_elf_skirmisher_1.jpg", 2, "soldier", "muster", "ranged"),
    scoiatael_elf_skirmisher_2("scoiatael_elf_skirmisher_2", "scoiatael_elf_skirmisher_2.jpg", 2, "soldier", "muster", "ranged"),
    scoiatael_filavandrel("scoiatael_filavandrel", "scoiatael_filavandrel.jpg", 6, "soldier", "", "agile"),
    scoiatael_havekar_nurse("scoiatael_havekar_nurse", "scoiatael_havekar_nurse.jpg", 0, "soldier", "medic", "ranged"),
    scoiatael_havekar_nurse_1("scoiatael_havekar_nurse_1", "scoiatael_havekar_nurse_1.jpg", 0, "soldier", "medic", "ranged"),
    scoiatael_havekar_nurse_2("scoiatael_havekar_nurse_2", "scoiatael_havekar_nurse_2.jpg", 0, "soldier", "medic", "ranged"),
    scoiatael_havekar_support("scoiatael_havekar_support", "scoiatael_havekar_support.jpg", 5, "soldier", "muster", "melee"),
    scoiatael_havekar_support_1("scoiatael_havekar_support_1", "scoiatael_havekar_support_1.jpg", 5, "soldier", "muster", "melee"),
    scoiatael_havekar_support_2("scoiatael_havekar_support_2", "scoiatael_havekar_support_2.jpg", 5, "soldier", "muster", "melee"),
    scoiatael_ida("scoiatael_ida", "scoiatael_ida.jpg", 6, "soldier", "", "ranged"),
    scoiatael_ioverth("scoiatael_iorveth", "scoiatael_iorveth.jpg", 10, "hero", "", "ranged"),
    scoiatael_isengrim("scoiatael_isengrim", "scoiatael_isengrim.jpg", 10, "hero", "moral_boost", "melee"),
    scoiatael_mahakam("scoiatael_mahakam", "scoiatael_mahakam.jpg", 5, "soldier", "", "melee"),
    scoiatael_mahakam_1("scoiatael_mahakam_1", "scoiatael_mahakam_1.jpg", 5, "soldier", "", "melee"),
    scoiatael_mahakam_2("scoiatael_mahakam_2", "scoiatael_mahakam_2.jpg", 5, "soldier", "", "melee"),
    scoiatael_mahakam_3("scoiatael_mahakam_3", "scoiatael_mahakam_3.jpg", 5, "soldier", "", "melee"),
    scoiatael_mahakam_4("scoiatael_mahakam_4", "scoiatael_mahakam_4.jpg", 5, "soldier", "", "melee"),
    scoiatael_milva("scoiatael_milva", "scoiatael_milva.jpg", 10, "soldier", "moral_boost", "ranged"),
    scoiatael_riordain("scoiatael_riordain", "scoiatael_riordain.jpg", 1, "soldier", "", "ranged"),
    scoiatael_saskia("scoiatael_saskia", "scoiatael_saskia.jpg", 10, "hero", "", "ranged"),
    scoiatael_schirru("scoiatael_schirru", "scoiatael_schirru.jpg", 8, "soldier", "scorch", "siege"),
    scoiatael_toruviel("scoiatael_toruviel", "scoiatael_toruviel.jpg", 2, "soldier", "", "ranged"),
    scoiatael_vrihedd_brigade("scoiatael_vrihedd_brigade", "scoiatael_vrihedd_brigade.jpg", 5, "soldier", "", "agile"),
    scoiatael_vrihedd_brigade_1("scoiatael_vrihedd_brigade_1", "scoiatael_vrihedd_brigade_1.jpg", 5, "soldier", "", "agile"),
    scoiatael_vrihedd_cadet("scoiatael_vrihedd_cadet", "scoiatael_vrihedd_cadet.jpg", 4, "soldier", "", "ranged"),
    scoiatael_yaevinn("scoiatael_yaevinn", "scoiatael_yaevinn.jpg", 6, "soldier", "", "agile"),

    skellige_berserker("skellige_berserker", "skellige_berserker.jpg", 4, "soldier", "berserker", "melee"),
    skellige_birna("skellige_birna", "skellige_birna.jpg", 2, "soldier", "medic", "melee"),
    skellige_blueboy("skellige_blueboy", "skellige_blueboy.jpg", 6, "soldier", "", "melee"),
    skellige_brokva_archer("skellige_brokva_archer", "skellige_brokva_archer.jpg", 6, "soldier", "", "ranged"),
    skellige_cerys("skellige_cerys", "skellige_cerys.jpg", 10, "hero", "muster", "melee"),
    skellige_craite_warrior("skellige_craite_warrior", "skellige_craite_warrior.jpg", 6, "soldier", "tight_bound", "melee"),
    skellige_dimun_pirate("skellige_dimun_pirate", "skellige_dimun_pirate.jpg", 6, "soldier", "scorch", "ranged"),
    skellige_donar("skellige_donar", "skellige_donar.jpg", 4, "soldier", "", "melee"),
    skellige_draig("skellige_draig", "skellige_draig.jpg", 2, "soldier", "commander_horn", "siege"),
    skellige_ermion("skellige_ermion", "skellige_ermion.jpg", 8, "hero", "mardroeme", "ranged"),
    skellige_hemdall("skellige_hemdall", "skellige_hemdall.jpg", 11, "hero", "", "melee"),
    skellige_heymaey("skellige_heymaey", "skellige_heymaey.jpg", 4, "soldier", "", "melee"),
    skellige_hjalmar("skellige_hjalmar", "skellige_hjalmar.jpg", 10, "hero", "", "ranged"),
    skellige_holger("skellige_holger", "skellige_holger.jpg", 4, "soldier", "", "siege"),
    skellige_kambi("skellige_kambi", "skellige_kambi.jpg", 0, "soldier", "", "melee"),
    skellige_light_longship("skellige_light_longship", "skellige_light_longship.jpg", 4, "soldier", "muster", "ranged"),
    skellige_madmad_lugos("skellige_madmad_lugos", "skellige_madmad_lugos.jpg", 6, "soldier", "", "melee"),
    skellige_olaf("skellige_olaf", "skellige_olaf.jpg", 12, "soldier", "moral_boost", "agile"),
    skellige_shield_maiden("skellige_shield_maiden", "skellige_shield_maiden.jpg", 4, "soldier", "tight_bound", "melee"),
    skellige_shield_maiden_1("skellige_shield_maiden_1", "skellige_shield_maiden_1.jpg", 4, "soldier", "tight_bound", "melee"),
    skellige_shield_maiden_2("skellige_shield_maiden_2", "skellige_shield_maiden_2.jpg", 4, "soldier", "tight_bound", "melee"),
    skellige_svanrige("skellige_svanrige", "skellige_svanrige.jpg", 4, "soldier", "", "melee"),
    skellige_tordarroch("skellige_tordarroch", "skellige_tordarroch.jpg", 4, "soldier", "", "melee"),
    skellige_udalryk("skellige_udalryk", "skellige_udalryk.jpg", 4, "soldier", "", "melee"),
    skellige_vildkaarl("skellige_vildkaarl", "skellige_vildkaarl.jpg", 14, "soldier", "medic", "melee"),
    skellige_war_longship("skellige_war_longship", "skellige_war_longship.jpg", 6, "soldier", "tight_bound", "siege"),
    skellige_young_berserker("skellige_young_berserker", "skellige_young_berserker.jpg", 2, "soldier", "tight_bound", "ranged"),
    skellige_young_vildkaarl("skellige_young_vildkaarl", "skellige_young_vildkaarl.jpg", 8, "soldier", "berserker", "ranged"),

    special_decoy("special_decoy", "special_decoy.jpg", 0, "special", "decoy", "spell"),
    special_decoy_1("special_decoy_1", "special_decoy.jpg", 0, "special", "decoy", "spell"),
    special_decoy_2("special_decoy_2", "special_decoy.jpg", 0, "special", "decoy", "spell"),
    special_horn("special_horn", "special_horn.jpg", 0, "special", "", "spell"),
    special_horn_1("special_horn_1", "special_horn.jpg", 0, "special", "", "spell"),
    special_horn_2("special_horn_2", "special_horn.jpg", 0, "special", "", "spell"),
    special_mardroeme("special_mardroeme", "special_mardroeme.jpg", 0, "special", "", "special"),
    special_mardroeme_1("special_mardroeme_1", "special_mardroeme.jpg", 0, "special", "", "special"),
    special_mardroeme_2("special_mardroeme_2", "special_mardroeme.jpg", 0, "special", "", "special"),
    special_scorch("special_scorch", "special_scorch.jpg", 0, "special", "", "spell"),
    special_scorch_1("special_scorch_1", "special_scorch.jpg", 0, "special", "", "spell"),
    special_scorch_2("special_scorch_2", "special_scorch.jpg", 0, "special", "", "spell"),

    weather_fog("weather_fog", "weather_fog.jpg", 0, "special", "weather", "weather"),
    weather_fog_1("weather_fog_1", "weather_fog.jpg", 0, "special", "weather", "weather"),
    weather_frost("weather_frost", "weather_frost.jpg", 0, "special", "weather", "weather"),
    weather_frost_1("weather_frost_1", "weather_frost.jpg", 0, "special", "weather", "weather"),
    weather_rain("weather_rain", "weather_rain.jpg", 0, "special", "weather", "weather"),
    weather_rain_1("weather_rain_1", "weather_rain.jpg", 0, "special", "weather", "weather"),
    weather_clear("weather_clear", "weather_clear.jpg", 0, "special", "weather", "weather"),
    weather_clear_1("weather_clear_1", "weather_clear.jpg", 0, "special", "weather", "weather"),
    weather_storm("weather_storm", "weather_storm.jpg", 0, "special", "weather", "weather"),
    weather_storm_1("weather_storm_1", "weather_storm.jpg", 0, "special", "weather", "weather"),
    ;

    private final String name;
    private final String imageAddress;
    private final int power;
    private final String type;
    private final String abilityName;
    private final Place placeToBe;
    public static final CardData nilfgaard_leader = leaders_nilfgaard_emhyr_bronze;
    public static final CardData monsters_leader = leaders_monsters_eredin_bronze;
    public static final CardData realms_leader = leaders_realms_foltest_bronze;
    public static final CardData scoiatael_leader = leaders_scoiatael_francesca_bronze;
    public static final CardData skellige_leader = leaders_skellige_crach_an_craite;


    CardData(String name, String imageAddress, int power, String type, String abilityName, String placeToBe) {
        this.name = name;
        this.imageAddress = imageAddress;
        this.power = power;
        this.type = type;
        this.abilityName = abilityName;
        this.placeToBe = Place.getPlaceToBeByName(placeToBe);
    }

    public String getName() {
        return this.name;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public int getPower() {
        return power;
    }

    public String getAbility() {
        return type;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public Place getPlaceToBe() {
        return placeToBe;
    }

    public static CardData getCardDataByName(String cardName) {
        for (CardData cardData : CardData.values()) {
            if (cardData.name.equals(cardName))
                return cardData;
        }
        return null;
    }
}