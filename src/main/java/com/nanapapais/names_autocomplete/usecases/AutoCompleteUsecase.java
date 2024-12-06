package com.nanapapais.names_autocomplete.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nanapapais.names_autocomplete.domain.entities.Name;
import com.nanapapais.names_autocomplete.domain.services.Search;
import com.nanapapais.names_autocomplete.infra.repositories.NamesRepositoryImpl;

@Service
public class AutoCompleteUsecase {
    // TODO: Inverter corretamente o repository através do config bean;
    @Autowired
    NamesRepositoryImpl repository;

    public List<Name> exec(String query) {
        var search = new Search(repository);
        return search.exec(query);
    }
}
