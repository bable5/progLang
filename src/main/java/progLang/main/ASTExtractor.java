package progLang.main;

import progLang.ast.CompilationUnit;
import progLang.ast.Stmt;
import progLang.progLangParser;
import progLang.transformers.CompilationUnitsGenerator;
import progLang.util.Context;

import java.util.ArrayList;

public class ASTExtractor {
    public static ASTExtractor instance(Context context) {
        ASTExtractor extractor = context.get(ASTExtractor.class);
        if (extractor == null) {
            extractor = new ASTExtractor(context);
            context.put(ASTExtractor.class, extractor);
        }
        return extractor;
    }

    private final CompilationUnitsGenerator compilationUnitExtractor;

    public ASTExtractor(Context context) {
        this.compilationUnitExtractor = CompilationUnitsGenerator.instance(context);
    }

    public CompilationUnit extract(progLangParser.ProgLangContext context) {
        return compilationUnitExtractor.visitCompilationUnit(context.compilationUnit(0));
    }
}
