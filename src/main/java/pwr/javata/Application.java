package pwr.javata;

import pwr.javata.gui.Window;

public class Application {

    public static void main(String[] args) {
        try {
            new Window();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
