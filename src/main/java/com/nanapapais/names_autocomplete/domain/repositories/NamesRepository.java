package com.nanapapais.names_autocomplete.domain.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nanapapais.names_autocomplete.domain.entities.Name;

public abstract class NamesRepository {
    public abstract void add(String key, Name name);
    public abstract List<Name> get(String key);
    public abstract void remove(String key, Name name);
    public abstract void clean();
    public abstract Boolean isEmpty();

    public List<Name> getMany(List<String> keys) {
        List<Name> result = new ArrayList<>();
        Set<String> resultIds = new HashSet<>();
        for (int index = 0; index < keys.size(); index++) {
            var key = keys.get(index);
            var names = this.get(key);
            names.forEach((name) -> {
                if (!resultIds.contains(name.getId())) result.add(name);
                resultIds.add(name.getId());
            });
        }
        return result;
    }
}
