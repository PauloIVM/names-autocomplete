package com.nanapapais.names_autocomplete.infra.repositories;

public class NameDataDTO {
    private String name;
    private String href;
    private String meaning;
    private String meaning_original;

    public String getName() { return this.name; }
    public String getHref() { return this.href; }
    public String getMeaning() { return this.meaning; }
    public String getMeaningOriginal() { return this.meaning_original; }
}
