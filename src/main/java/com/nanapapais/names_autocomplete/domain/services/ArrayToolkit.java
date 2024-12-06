package com.nanapapais.names_autocomplete.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArrayToolkit {
    static Integer sum(List<Integer> arr) {
        return arr
            .stream()
            .reduce((acc, curr) -> acc + curr)
            .orElse(0);
    }

    static List<Integer> fill(List<Integer> curr, Integer size, Integer value) {
        var result = new ArrayList<>(curr);
        while (size > 0) {
            result.add(value);
            size--;
        }
        return result;
    }

    static List<Integer> toVector(List<String> baseArray, List<String> entryArr) {
        Map<String, Integer> entryRecord = new HashMap<>();
        entryArr.forEach((key) -> {
            Integer element = entryRecord.get(key);
            if (element != null) {
                entryRecord.put(key, element + 1);
            } else {
                entryRecord.put(key, 1);
            }
        });
        return baseArray
            .stream()
            .map((key) -> entryRecord.get(key) != null ? entryRecord.get(key) : 0)
            .collect(Collectors.toList());
    }

    static Double similarity(List<Integer> arr1, List<Integer> arr2) {
        if (arr1.size() != arr2.size()) {
            // TODO: Criar Exeption certinho...
            // throw new Error("Arrays must be the same length.");
            return 0.0;
        }
        Double dotProduct = 0.0;
        Double normA = 0.0;
        Double normB = 0.0;
        for (int i = 0; i < arr1.size(); i++) {
            dotProduct += ((double) arr1.get(i)) * ((double) arr2.get(i));
            normA += ((double) arr1.get(i)) * ((double) arr1.get(i));
            normB += ((double) arr2.get(i)) * ((double) arr2.get(i));
        }
        if (dotProduct == 0.0) {
            return 0.0;
        }
        Double similarity = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        return similarity;
    }
}
