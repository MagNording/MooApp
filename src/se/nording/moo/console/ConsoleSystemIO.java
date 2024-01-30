/**
 * To be able to run in a console environment
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */
package se.nording.moo.console;

import se.nording.moo.ui.IO;

import java.util.Scanner;

public class ConsoleSystemIO implements IO {
    private Scanner input = new Scanner(System.in);

    @Override
    public boolean yesNo(String prompt) {
        System.out.println(prompt + " (y/n)");
        String answer = input.nextLine();
        return answer.equalsIgnoreCase("y");
    }

    @Override
    public String getString() {
        return input.nextLine();
    }

    @Override
    public void addString(String s) {
        System.out.println(s + "\n");
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}