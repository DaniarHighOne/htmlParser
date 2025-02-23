package kz.daniar.developer.parsing.testing.parser;

import org.w3c.dom.Document;

public interface DocumentParser {

    Document parse(String html) throws Exception;

}
