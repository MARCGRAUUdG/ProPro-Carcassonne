import java.util.List;

///@class Estructura

///@brief Descripcio de la clase...

public abstract class Estructura extends Possessio {

    public Estructura(Fitxa inici, List<Character> r) {
        super(inici,r);
    }

    ///@pre ---
    ///@post retorna cert si la possessio esta completa altrament false
    public abstract boolean tancat ();

    ///@pre ---
    ///@post retorna punts de la possessio
    public abstract int punts();

}
