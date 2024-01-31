/**
 * User interface
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */

package se.nording.moo.ui;

public interface IO {

    boolean yesNo(String prompt); // true/false fråga

    String getString(); // Läser in en sträng från användaren

    void addString(String s); // Skriver ut en sträng till användaren

    void clear();

    void exit();
}
