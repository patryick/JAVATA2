package pwr.javata.model;

import java.util.Arrays;

public class FileData {
    private String [] fileLines;
    private State state;

    public FileData(String[] fileLines, State state) {
        this.fileLines = fileLines;
        this.state = state;
    }

    public String[] getFileLines() {
        return fileLines;
    }

    public void setFileLines(String[] fileLines) {
        this.fileLines = fileLines;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getText() {
        return Arrays.stream(this.fileLines)
                .reduce((str1, str2) -> str1 + "\n" + str2)
                .orElse(null);
    }
}
