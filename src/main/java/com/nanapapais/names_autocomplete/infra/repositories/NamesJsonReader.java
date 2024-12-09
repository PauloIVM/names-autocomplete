package com.nanapapais.names_autocomplete.infra.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class NamesJsonReader {
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public NamesJsonReader(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    public List<NameDataDTO> read() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:pt-names.json");
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<NameDataDTO>>() {});
    }
}
