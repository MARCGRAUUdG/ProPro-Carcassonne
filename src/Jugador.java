import java.util.ArrayList;

///@class Jugador

 ///@brief Aquesta classe representa a un jugador de la partida. Aquest està gestionat per un
 ///sistema de torns i per a cada torn agafa una fitxa de la Baralla i la fica al tauler
 ///de la manera possible que vulgui.
 ///Superclasse de la qual deriven per herència Controlable i Maquina.
 ///Controlable representa un jugador humà que indica mitjançant la GUI els moviments a
 ///realitzar en tot moment.
 ///Maquina representa un jugador bot que de manera més intel·ligent o menys juga contra
 ///un personatge Controlable.

public abstract class Jugador {

    private int id;///<Descripcio...
    private Fitxa fitxaActual;///<Descripcio...
    private int nHumanets;///<Descripcio...

    private int _punts;
    private ArrayList<Possessio> llistaPossessions = new ArrayList<>();

    private int puntuacio=0;///<Descripcio...

    public int getId() {
        return id;
    }

    Jugador(){
    }

    Jugador(int jugador){
        nHumanets = 6;
        id = jugador;
        _punts = 0;
    }

    //Pre: Punts per assignar != 0
    //Post: Punts assignats al jugador correspoenet
    void assignarPunts(int punts)
    {
        _punts = punts;
    }

    //Pre: ---
    //Post: Retorna la llista de possessions del jugador actual.
    public ArrayList<Possessio> getLlistaPossessions()
    {
        return llistaPossessions;
    }

    ///@pre  ---
    ///@post  Indica si un personatge ha guanyat
    public boolean guanyador() {
        return false;
    }

    //Pre: ---
    //Post: Retorna el nombre d'humanets del jugador actual
    public int getHumanets(){
        return nHumanets;
    }

    //Pre: Nombre d'humanets >= 0
    //Post: Nombre d'humanets assignat
    public void setHumanets(int n){
        nHumanets=n;
    }

    public void incrementaHumanets(){
        nHumanets++;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                '}';
    }

    //Pre: ---
    //Post: retorna si el jugador actual es controlable
    public abstract boolean esControlable();

    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual al tauler i gui en la posicio pos
    public abstract void posaFitxa(Posicio pos, Fitxa fitxaActual, Tauler tauler);

    public int getPuntuacio(){
        return puntuacio;
    }

    public void setPuntuacio(int p){
        puntuacio=p;
    }

    public void incrementaPuntuacio(int p){
        setPuntuacio(puntuacio+p);
    }
}
