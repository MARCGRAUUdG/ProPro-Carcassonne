import java.util.List;

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
    private int puntuacio=0;///<Descripcio...

    public int getId() {
        return id;
    }

    Jugador(){
    }

    Jugador(int jugador){
        nHumanets = 6;
        id = jugador;
    }

    ///@pre  Baralla b ! buida
    ///@post  Agafa una fitxa aleatòria de la baralla
    Fitxa agafarFitxaBaralla(Baralla b)
    {
        Fitxa f;// = b.agafarFitxa();
        f = fitxaActual;
        return f;
    }

    ///@pre  ---
    ///@post  Indica si un personatge ha guanyat
    public boolean guanyador() {
        return false;
    }

    public int getHumanets(){
        return nHumanets;
    }

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
