import java.util.List;

///@class Estructura

///@brief Subtipus de possessio

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

    ///@pre ---
    ///@post  retorna el tipus de possessio que és
    public abstract char tipus();

    ///@pre ---
    ///@post  retorna el/s propietari/s de la possessio altrament llista buida
    public abstract List<Integer> propietari();

}
