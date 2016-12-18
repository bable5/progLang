package progLang.main;

import progLang.ast.CompilationUnit;
import progLang.progLangParser;
import progLang.type.Type;
import progLang.util.Context;
import progLang.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
    private final Type typer;

    protected ProgLangCompiler(Context context) {
        log = Log.instance(context);
        parser = Parser.instance(context);
        astExtractor = ASTExtractor.instance(context);
        typer = Type.instance(context);
    }

    public Result compile(Options options) {

        for (String file : options.getFileNames()) {
            File f = new File(file);
            if (f.exists()) {
                parse(f).map(this::extract)
                        .map(this::type);
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

    protected CompilationUnit extract(progLangParser.ProgLangContext context) {
        return astExtractor.extract(context);
    }

    protected CompilationUnit type(CompilationUnit compilationUnit) {
        CompilationUnit check = typer.check(compilationUnit);

        check.stmts.stream()
                .forEach(s -> System.out.println(s));
        return check;
    }

    public enum Result {
        OK(0), ERROR(1);

        public final int code;

        Result(int code) {
            this.code = code;
        }
    }

}
