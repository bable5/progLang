package progLang.main;

import progLang.ast.CompilationUnit;
import progLang.progLangParser;
import progLang.util.Context;

public class ASTExtractor {
    public static ASTExtractor instance(Context context) {
        ASTExtractor extractor = context.get(ASTExtractor.class);
        if (extractor == null) {
            extractor = new ASTExtractor();
            context.put(ASTExtractor.class, extractor);
        }
        return extractor;
    }

    public CompilationUnit extract(progLangParser.ProgLangContext context) {
        return new CompilationUnit();
    }
}
