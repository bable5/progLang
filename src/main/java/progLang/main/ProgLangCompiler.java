package progLang.main;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import progLang.ast.CompilationUnit;
import progLang.progLangParser;
import progLang.util.Context;
import progLang.util.Log;

public class ProgLangCompiler {
    public static ProgLangCompiler instance(Context context) {
        ProgLangCompiler compiler = context.get(ProgLangCompiler.class);
        if (compiler == null) {
            compiler = new ProgLangCompiler(context);
            context.put(ProgLangCompiler.class, compiler);
        }
        return compiler;
    }

    private final Log log;
    private final Parser parser;
    private final ASTExtractor astExtractor;

    protected ProgLangCompiler(Context context) {
        log = Log.instance(context);
        parser = Parser.instance(context);
        astExtractor = ASTExtractor.instance(context);
    }

    public Result compile(Options options) {

        for (String file : options.getFileNames()) {
            File f = new File(file);
            if (f.exists()) {
                parse(f).map(this::extract);
            } else {
                log.error("file.not.found", f.getName());
            }

        }

        return Result.OK;
    }

    protected Optional<progLangParser.ProgLangContext> parse(File f) {
        try {
            return Optional.of(parser.parse(f.getAbsolutePath()));
        } catch (IOException e) {
            log.error("compile.error", e.getMessage());
            return Optional.empty();
        }
    }

    protected Optional<CompilationUnit> extract(progLangParser.ProgLangContext context) {
        return Optional.of(astExtractor.extract(context));
    }

    public enum Result {
        OK(0), ERROR(1);

        public final int code;

        Result(int code) {
            this.code = code;
        }
    }

}
