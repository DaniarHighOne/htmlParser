package kz.daniar.developer.parsing.testing.dto;

import java.time.LocalDate;

public record ParsingDTO(
        String title,
        String author,
        LocalDate date,
        String text
) { }
