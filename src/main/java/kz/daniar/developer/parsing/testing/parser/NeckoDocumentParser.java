package kz.daniar.developer.parsing.testing.parser;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;

public class NeckoDocumentParser implements DocumentParser {


    @Override
    public Document parse(String html) throws Exception {

        DOMParser parser = new DOMParser();
        parser.parse(new InputSource(new StringReader(html)));

        return parser.getDocument();
    }
}
