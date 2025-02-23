package kz.daniar.developer.parsing.testing.parsingStrategy;

import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public final class TitleParsingStrategy implements ExctractionStrategy {

    private final String[] XPATHS = {
            "//meta[@property='og:title']/@content",
            "//title/text()",
            "//h1[1]/text()",
            "//*[contains(@class, 'title')]/text()"
    };

    @Override
    public String extract(Document document) {
        XPathFactory xPathFactory = XPathFactory.newInstance();

        for (String xpathExpr : XPATHS) {
            try {
                XPath xPath = xPathFactory.newXPath();
                String result = xPath.evaluate(xpathExpr, document);
                if (!result.isEmpty()) return cleanContent(result);
            } catch (XPathExpressionException ignored) {}
        }
        return "";
    }
}
