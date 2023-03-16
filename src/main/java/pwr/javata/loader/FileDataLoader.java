package pwr.javata.loader;

import pwr.javata.model.FileData;

import java.io.IOException;

public interface FileDataLoader {
    public FileData loadFileData(String path) throws IOException;
}
