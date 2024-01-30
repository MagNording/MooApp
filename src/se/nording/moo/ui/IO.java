/**
 * User interface
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */

package se.nording.moo.ui;

public interface IO {

    boolean yesNo(String prompt);

    String getString();

    void addString(String s);

    void clear();

    void exit();
}
