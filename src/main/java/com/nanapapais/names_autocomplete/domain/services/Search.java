package com.nanapapais.names_autocomplete.domain.services;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nanapapais.names_autocomplete.domain.entities.Name;
import com.nanapapais.names_autocomplete.domain.repositories.NamesRepository;

// TODO: Talvez esse cara aqui seja um agregate..
public class Search {
    private NamesRepository repository;
    private Integer maxLength;

    public Search(NamesRepository repository, Integer maxLength) {
        this.repository = repository;
        this.maxLength = maxLength;
    }

    public Search(NamesRepository repository) {
        this.maxLength = 10;
        this.repository = repository;
    }

    public List<Name> exec(String query) {
        if (query == null) return new ArrayList<Name>();
        var engine = new SearchEngine(query, this.maxLength);
        var elementsToSearch = this.repository.getMany(this.sanitize(query));
        elementsToSearch.forEach((e) -> engine.push(e));
        return engine.exec();
    }

    // TODO: Mover para uma classe separada:
    private List<String> sanitize(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        String sanitized = Normalizer
            .normalize(input, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");
        return Arrays.asList(sanitized.split("\\s+"));
    }
}
