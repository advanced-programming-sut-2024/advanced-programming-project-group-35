package com.example.model.card;

import com.example.model.game.Table;
import com.example.model.game.place.Row;

import java.util.HashMap;

public class AbilityContext {
    private Table table;
    private UnitCard card;
    private Row row;
    private HashMap<String, Object> additionalParams;

    public AbilityContext(Table table, UnitCard card, Row row) {
        this.table = table;
        this.card = card;
        this.row = row;
        this.additionalParams = new HashMap<>();
    }

    public Table getTable() {
        return table;
    }

    public UnitCard getCard() {
        return card;
    }

    public Row getRow() {
        return row;
    }

    public void addParam(String key, Object value) {
        additionalParams.put(key, value);
    }

    public Object getParam(String key) {
        return additionalParams.get(key);
    }
}
