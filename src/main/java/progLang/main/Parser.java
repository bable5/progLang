package progLang.main;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
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

    public void parse(String filename) throws IOException {
        progLangLexer lexer = new progLangLexer(new ANTLRFileStream(filename));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        progLangParser parser = new progLangParser(tokens);

        ProgLangContext progLangCtx = parser.progLang();

        System.out.println(progLangCtx.getText());
    }
}
