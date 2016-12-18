package progLang;

import progLang.main.OptionParser;
import progLang.main.Options;
import progLang.main.ProgLangCompiler;
import progLang.main.ProgLangCompiler.Result;
import progLang.util.Context;
import progLang.util.Log;

public class Main {
    public static void main(String[] args) {
        System.exit(new Main().runCompiler(args).code);
    }

    private final Context context;
    private final OptionParser optionsParser;
    private final ProgLangCompiler compiler;
    private final Log log;

    public Main() {
        context = createContext();
        log = Log.instance(context);
        compiler = ProgLangCompiler.instance(context);
        optionsParser = OptionParser.instance(context);
    }

    public Main(Context context) {
        this.context = context;
        log = Log.instance(context);
        compiler = ProgLangCompiler.instance(context);
        optionsParser = OptionParser.instance(context);
    }

    public Result runCompiler(String[] args) {
        try {
            Options options = optionsParser.parseArgs(args);

            return compiler.compile(options);
        } catch (Exception ex) {
            log.error("compile.error", ex.getMessage());
            ex.printStackTrace();
            return Result.ERROR;
        }
    }

    private Context createContext() {
        return new Context();
    }

}
