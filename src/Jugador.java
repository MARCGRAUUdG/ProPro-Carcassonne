import java.util.List;

/**
 * Aquesta classe representa a un jugador de la partida. Aquest està gestionat per un
 * sistema de torns i per a cada torn agafa una fitxa de la Baralla i la fica al tauler
 * de la manera possible que vulgui.
 *
 * Superclasse de la qual deriven per herència Controlable i Maquina.
 *
 * Controlable representa un jugador humà que indica mitjançant la GUI els moviments a
 * realitzar en tot moment.
 *
 * Maquina representa un jugador bot que de manera més intel·ligent o menys juga contra
 * un personatge Controlable.
 */
public abstract class Jugador {

    private int id;
    private Fitxa fitxaActual;
    private int nHumanets;
    private int _punts;

    public int getId() {
        return id;
    }

    Jugador(){
        nHumanets = 6;
        _punts = 0;
    }

    Jugador(int jugador){
        id = jugador;
    }

    void assignarPunts(int punts)
    {
        _punts = punts;
    }

    ///Pre: ---
    ///Post: Indica si un personatge ha guanyat
    public boolean guanyador() {
        return false;
    }

    //public abstract

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                '}';
    }
}
