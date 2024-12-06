package com.nanapapais.names_autocomplete.domain.entities;

public class Name {
    private String id;
    private String value;

    public String getId() { return id; }
    public String getValue() { return value; }

    public Name(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
