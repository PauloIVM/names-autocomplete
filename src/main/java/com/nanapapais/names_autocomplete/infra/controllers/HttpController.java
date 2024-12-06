package com.nanapapais.names_autocomplete.infra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanapapais.names_autocomplete.domain.entities.Name;
import com.nanapapais.names_autocomplete.usecases.AutoCompleteUsecase;

@RestController
@RequestMapping("/")
public class HttpController {

    @Autowired
    private AutoCompleteUsecase autoCompleteUsecase;

    // TODO: Talvez usar o sistema ACL para permitir a inserção de nomes, ou execução
    //       de um crawler apenas para alguns users.

    @GetMapping("/names")
    public List<Name> getSuggestions(@RequestParam String query) {
        return this.autoCompleteUsecase.exec(query);
    }
}
