package kz.daniar.developer.parsing.testing.parsingStrategy;

import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public final  class DateParsingStrategy implements ExctractionStrategy{

    private static final String [] XPATHS = {
            "//meta[@property='article:published_time']/@content",
            "//time/@datetime",
            "//*[contains(@class, 'date')]/text()",
            "//*[contains(@class, 'timestamp')]/@datetime"
    };

    private static final List<DateTimeFormatter> DATE_FORMATS = List.of(
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MMM dd, yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy")
    );


    @Override
    public String extract(Document document) {
        XPathFactory xpathFactory = XPathFactory.newInstance();

        for (String xpathExpr : XPATHS) {
            try {
                XPath xpath = xpathFactory.newXPath();
                String result = xpath.evaluate(xpathExpr, document);
                if (!result.isEmpty()) return cleanContent(result);
            } catch (XPathExpressionException ignored) {}
        }
        return "";
    }

    public LocalDate parseDate(String dateString) {
        return DATE_FORMATS.stream()
                .map(formatter -> {
                    try {
                        return LocalDate.parse(dateString, formatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
