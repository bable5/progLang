package progLang.main;

import java.io.File;
import java.io.IOException;

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

    protected ProgLangCompiler(Context context) {
        log = Log.instance(context);
        parser = Parser.instance(context);
    }

    public Result compile(Options options) {
        
        for (String file : options.getFileNames()) {
            File f = new File(file);
            if(f.exists()) {
                parse(f);
            } else {
                log.error("file.not.found", f.getName());
            }
            
        }
        
        return Result.OK;
    }
    
    protected void parse(File f) {
        try {
            parser.parse(f.getAbsolutePath());
        } catch (IOException e) {
            log.error("compile.error", e.getMessage());
        }
    }

    public static enum Result {
        OK(0), ERROR(1);

        public final int code;

        private Result(int code) {
            this.code = code;
        }
    }

}
