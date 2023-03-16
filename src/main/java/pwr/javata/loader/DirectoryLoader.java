package pwr.javata.loader;

import java.io.File;

public class DirectoryLoader {

    private File directoryPath;

    public DirectoryLoader(File directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String[] readDirectory(){
        return directoryPath.list();
    }
}
