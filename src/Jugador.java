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

    private int id;///<Identificador únic del Jugador
    private Fitxa fitxaActual;///<Fitxa actual dispoible pel jugador
    private int nHumanets;///<Nombre d'humanets del Jugador

    private ArrayList<Possessio> llistaPossessions = new ArrayList<>();///<Llista de Possessio ns del Jugador

    private int puntuacio=0;///<Punts totals del Jugador

    ///@pre --
    ///@post retorna la id
    public int getId() {
        return id;
    }

    ///@pre identificador del jugador
    ///@post constuctor per valor
    Jugador(int jugador){
        nHumanets = 6;
        id = jugador;
        puntuacio = 0;
    }

    ///@pre punts per assignar != 0
    ///@post punts assignats al jugador correspoenet
    void assignarPunts(int punts)
    {
        puntuacio = punts;
    }

    ///@pre --
    ///@post retorna la llista de possessions del jugador actual.
    public ArrayList<Possessio> getLlistaPossessions()
    {
        return llistaPossessions;
    }

    ///@pre --
    ///@post indica si un personatge ha guanyat
    public boolean guanyador() {
        return false;
    }

    ///@pre --
    ////@pre retorna el nombre d'humanets del jugador actual
    public int getHumanets(){
        return nHumanets;
    }

    ///@pre nombre d'humanets >= 0
    ///@post nombre d'humanets assignat
    public void setHumanets(int n){
        nHumanets=n;
    }

    ///@pre --
    ///@post incrementa en un el nombre d'humanets del Jugador
    public void incrementaHumanets(){
        nHumanets++;
    }

    ///@pre --
    ///@post retorna string de l'id del Jugador
    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                '}';
    }

    ///@post ---
    ///@post retorna si el jugador actual es controlable
    public abstract boolean esControlable();

    ///@pre pos inicialitzat i es correcte
    ///@pos posa fitxaActual al tauler i gui en la posicio pos
    public abstract void posaFitxa(Posicio pos, Fitxa fitxaActual, Tauler tauler);

    ///@pre --
    ///@prost retorna la puntuació del Jugador
    public int getPuntuacio(){
        return puntuacio;
    }

    ///@pre puntuació per assignar
    ///@post puntuació del Jugador assignada
    public void setPuntuacio(int p){
        puntuacio=p;
    }

    ///@pre puntuació per incrementar
    ///@post puntuació del Jugador incrementada
    public void incrementaPuntuacio(int p){
        setPuntuacio(puntuacio+p);
    }
}
