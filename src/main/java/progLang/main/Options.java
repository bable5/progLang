package progLang.main;

import java.util.ArrayList;
import java.util.List;

public class Options {
    private final List<String> filenames = new ArrayList<>();
    
    public List<String> getFileNames() {
        return this.filenames;
    }
    
    public void addFilename(String filename) {
        filenames.add(filename);
    }
    
}
