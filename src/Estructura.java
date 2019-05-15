

public abstract class Estructura extends Possessio {

    public Estructura(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    abstract public boolean tancat ();

    //Pre:---
    //Post:retorna punts
    abstract public int punts();

}
