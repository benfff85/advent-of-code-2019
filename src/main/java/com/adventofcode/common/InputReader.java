package com.adventofcode.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;

@Slf4j
@Component
public class InputReader {

    public List<String> read(String file) {
        log.info("");
        log.info("Reading file {}", file);
        try (Stream<String> stream = Files.lines(Paths.get(requireNonNull(getClass().getClassLoader().getResource(file)).toURI()))) {
            return stream.toList();
        } catch (Exception e) {
            log.warn("Error while reading file", e);
        }
        return emptyList();
    }

}