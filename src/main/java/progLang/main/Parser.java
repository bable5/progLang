package progLang.main;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import progLang.progLangLexer;
import progLang.progLangParser;
import progLang.progLangParser.ProgLangContext;
import progLang.util.Context;

public class Parser {
    public static Parser instance(Context context) {
        Parser parser = context.get(Parser.class);
        if (parser == null) {
            parser = new Parser();
            context.put(Parser.class, parser);
        }
        return parser;
    }

    public ProgLangContext parse(String filename) throws IOException {
        return parse(new ANTLRFileStream(filename));
    }

    public ProgLangContext parse(CharStream input) throws IOException {
        progLangLexer lexer = new progLangLexer(input);
        progLangParser parser = new progLangParser(new CommonTokenStream(lexer));

        return parser.progLang();
    }
}
