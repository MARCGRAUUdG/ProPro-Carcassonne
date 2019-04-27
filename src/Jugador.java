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

    int id;
    Fitxa fitxaActual;
    int nHumanets;

    Jugador(){
        nHumanets = 6;
    }

    Jugador(int jugador){
        id = jugador;
    }

    ///Pre: Baralla b ! buida
    ///Post: Agafa una fitxa aleatòria de la baralla
    Fitxa agafarFitxaBaralla(Baralla b)
    {
        Fitxa f;// = b.agafarFitxa();
        f = fitxaActual;
        return f;
    }

    ///Pre: ---
    ///Post: Indica si un personatge ha guanyat
    public boolean guanyador() {
        return false;
    }

    ///Pre: Llista de posicions correctes per ficar la fitxa no buida
    ///Post: Fitxa escollida ficada al tauler a la posició corresponent
    abstract void PosaFitxaAlTauler(List<Posicio> pos);

    //Pre: ---
    ///Post: Afegeix un seguidor a la fitxa
    abstract void PosarSeguidor(Regio r);

    public String getNom() {
        //TODO Retorna el nom del jugador
        return "";
    }
}
