package com.nanapapais.names_autocomplete.infra.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.nanapapais.names_autocomplete.domain.entities.Name;
import com.nanapapais.names_autocomplete.domain.repositories.NamesRepository;

@Service
public class NamesRepositoryImpl extends NamesRepository {
    private HashMap<String, Set<Name>> map = new HashMap<>();

    public NamesRepositoryImpl() {
        super();
    }

    public void add(String key, Name name) {
        var set = this.map.get(key);
        if (set == null) set = new HashSet<>();
        set.add(name);
        // INFO: Eu preciso mesmo fazer o put? Ou a ref já modifica no hashmap pra mim? Conferir...
        this.map.put(key, set);
    }

    public List<Name> get(String key) {
        var set = this.map.get(key);
        if (set == null) set = new HashSet<>();
        return new ArrayList<>(set);
    }

    public void remove(String key, Name name) {
        this.map.remove(key);
    }

    public void clean() {
        this.map = new HashMap<>();
    }

    public Boolean isEmpty() {
        if (map.size() == 0) return true;
        return false;
    }

}
