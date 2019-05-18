import javafx.util.Pair;

import java.util.List;

public abstract class Estructura extends Possessio {

    public Estructura(Fitxa inici, List<Character> r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    abstract public boolean tancat ();

    //Pre:---
    //Post:retorna punts
    abstract public int punts();

}
