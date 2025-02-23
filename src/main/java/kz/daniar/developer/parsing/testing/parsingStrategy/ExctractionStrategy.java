package kz.daniar.developer.parsing.testing.parsingStrategy;

import org.w3c.dom.Document;

public sealed interface ExctractionStrategy  permits
        TitleParsingStrategy,TextParsingStrategy, DateParsingStrategy, AuthorParsingStrategy{

    String extract(Document document);

    default String cleanContent(String content) {
        return content != null ? content.trim() : "";
    }
}
