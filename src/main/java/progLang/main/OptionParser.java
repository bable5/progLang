package progLang.main;

import progLang.util.Context;

public class OptionParser {

    public static OptionParser instance(Context context) {
        OptionParser optionsParser = context.get(OptionParser.class);
        if (optionsParser == null) {
            optionsParser = new OptionParser();
            context.put(OptionParser.class, optionsParser);
        }
        return optionsParser;
    }

    protected OptionParser() {
    }

    public Options parseArgs(String[] args) {
        Options options = new Options();

        for (String s : args) {
            options.addFilename(s);
        }

        return options;
    }
}
