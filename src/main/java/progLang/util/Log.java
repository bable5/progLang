package progLang.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public class Log {
    public static Log instance(Context context) {
        Log optionsParser = context.get(Log.class);
        if (optionsParser == null) {
            optionsParser = new Log();
            context.put(Log.class, optionsParser);
        }
        return optionsParser;
    }

    private PrintStream out;
    private PrintStream err;
    private Properties messages;

    protected Log() {
        out = System.out;
        err = System.err;
        loadMessages();
    }

    private void loadMessages() {
        messages = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/progLang/messages.properties")) {
            messages.load(in);
        } catch (IOException ioe) {
            throw new RuntimeException("Failed to load log message properties", ioe);
        }
    }

    public void info(String msgKey, Object... args) {
        log(out, msgKey, args);
    }

    public void error(String msgKey, Object... args) {
        log(err, msgKey, args);
    }

    private void log(PrintStream writer, String msgKey, Object... args) {
        String format = getMsg(msgKey);
        writer.println(String.format(format, args));
    }

    protected String getMsg(String msgKey) {
        return messages.getProperty(msgKey);
    }

    public void setOutput(PrintStream out) {
        this.out = out;
    }
    
    public void setError(PrintStream err) {
        this.err = err;
    }
}
