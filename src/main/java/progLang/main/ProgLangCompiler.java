package progLang.main;

import org.apache.bcel.classfile.JavaClass;
import progLang.ast.CompilationUnit;
import progLang.jvm.bytecode.Generator;
import progLang.jvm.lower.Lower;
import progLang.jvm.tree.JVMCompilationUnit;
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
    private final Lower lower;
    private final Generator gen;


    private ProgLangCompiler(Context context) {
        log = Log.instance(context);
        parser = Parser.instance(context);
        astExtractor = ASTExtractor.instance(context);
        typer = Type.instance(context);
        lower = Lower.instance(context);
        gen = Generator.instance(context);
    }

    public Result compile(Options options) {

        for (String file : options.getFileNames()) {
            File f = new File(file);
            if (f.exists()) {
                parse(f).map(this::extract)
                        .map(this::type)
                        .map(this::lower)
                        .map(this::emit)
                        .map(this::write);
            } else {
                log.error("file.not.found", f.getName());
            }

        }

        return Result.OK;
    }

    private Optional<progLangParser.ProgLangContext> parse(File f) {
        try {
            return Optional.of(parser.parse(f.getAbsolutePath()));
        } catch (IOException e) {
            log.error("compile.error", e.getMessage());
            return Optional.empty();
        }
    }

    private CompilationUnit extract(progLangParser.ProgLangContext context) {
        return astExtractor.extract(context);
    }

    private CompilationUnit type(CompilationUnit compilationUnit) {
        return typer.check(compilationUnit);
    }

    private JVMCompilationUnit lower(CompilationUnit compilationUnit) {
        return lower.apply(compilationUnit);
    }

    private JavaClass emit(JVMCompilationUnit compilationUnit) {
        return gen.generate(compilationUnit);
    }

    private String write(JavaClass clazz) {
        File f = new File(clazz.getFileName());
        try {
            clazz.dump(f);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write " + f.getName());
        }
        return clazz.getClassName();
    }

    public enum Result {
        OK(0), ERROR(1);

        public final int code;

        Result(int code) {
            this.code = code;
        }
    }

}
