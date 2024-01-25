package se.nording.moo;

import java.util.Scanner;

public class ConsoleSystemIO implements SystemIO {
    private Scanner scanner;

    public ConsoleSystemIO() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean yesNo(String prompt) {
        return false;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public void addString(String s) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void exit() {

    }
}
