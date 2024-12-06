package com.nanapapais.names_autocomplete.domain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.text.Normalizer;
import java.util.Arrays;

import com.nanapapais.names_autocomplete.domain.entities.Name;
import com.nanapapais.names_autocomplete.domain.valueobjects.MinHeap;

public class SearchEngine {
    private Integer maxLength;
    private String query;
    private MinHeap<Name> heap;

    public SearchEngine(String query) {
        this.maxLength = 10;
        this.query = query;
        this.heap = new MinHeap<Name>(this::compare);
    }

    public SearchEngine(String query, Integer maxLength) {
        this.maxLength = maxLength;
        this.query = query;
        this.heap = new MinHeap<Name>(this::compare);
    }

    public List<Name> exec() {
        var list = this.heap.toList();
        Collections.reverse(this.heap.toList());
        return list;
    }

    public void push(Name element) {
        if (element == null) return;
        this.heap.push(element);
        if (this.heap.size() > this.maxLength) this.heap.pop();
    }

    private Boolean compare(Name a, Name b) {
        Double aSim = this.getSimilarity(a);
        Double bSim = this.getSimilarity(b);
        return aSim > bSim ? true : false;
    }

    private Double getSimilarity(Name element) {
        var elementWords = this.sanitize(element.getValue());
        var queryWords = this.sanitize(this.query);
        var queryUniqueWords = new ArrayList<>(new HashSet<>(queryWords));
        var queryVector = ArrayToolkit.toVector(queryUniqueWords, queryWords);
        var currVector = ArrayToolkit.toVector(queryUniqueWords, elementWords);
        var sizeDif = elementWords.size() - ArrayToolkit.sum(currVector);
        var currVectorFilled = ArrayToolkit.fill(currVector, sizeDif, 1);
        var queryVectorFilled = ArrayToolkit.fill(queryVector, sizeDif, 0);
        return ArrayToolkit.similarity(queryVectorFilled, currVectorFilled);
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
