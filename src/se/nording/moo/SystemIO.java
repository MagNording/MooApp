package se.nording.moo;

public interface SystemIO {

    // visa en fråga till användaren och få ett ja/nej-svar.
    boolean yesNo(String prompt);

    // läsa en sträng från användaren.
    String getString();

    // skriva en sträng till användaren.
    void addString(String s);

    // rensa användarens gränssnitt.
    void clear();

    // avsluta programmet.
    void exit();
}
