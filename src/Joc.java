import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Joc {
    private static LlegirFitxer _fitxer;
    private static ArrayList<Jugador> _jugadors;
    private static Tauler _tauler;
    private static Tirada _tirada;
    private static int _nJugadors;
    private static Baralla _baralla;
    private static Fitxa _fInicial;

    public Joc(){

    }

    public static void repNomFitxer(String text){
        boolean llegitOk=false;//TODO Treure llegitOk posteriorment quan estigui implementat _fitxer.lecturaCorrecta()
        try {
            _fitxer.nomFitxer(text);
            llegitOk=true;//TODO Treure llegitOk posteriorment quan estigui implementat _fitxer.lecturaCorrecta()
        } catch (FileNotFoundException e) {
            Gui.informarFitxerEntradaIncorrecte("No s'ha trobat el fitxer");
        }

        if(/*_fitxer.lecturaCorrecta()*/llegitOk) {//TODO Treure llegitOk posteriorment quan estigui implementat _fitxer.lecturaCorrecta()
            repDadesFitxer();
            IniciJoc();
        }
    }

    private static void repDadesFitxer() {
        _nJugadors = _fitxer.getJugadors();//TODO Hauria de ser Jugador no nJugador
        //_baralla = _fitxer.getBaralla(); //TODO Falta implementar per fitxer
        //_fInicial = _fitxer.getFitxaInicial(); //TODO Falta implementar per fitxer
    }

    private static void posaFitxaInicial(){
        Posicio pos =new Posicio(5,5,0);
        //_fInicial.setPosicio(pos); //TODO Fins que no estigui inicialitzat fitxa inicial no va
        //Gui.posaFitxa(_fInicial); //TODO Fins que no estigui inicialitzat fitxa inicial no va
    }

    private static void IniciJoc(){
        Gui.setupJugadors(_nJugadors);
        Gui.iniciaTaulerGui();
        posaFitxaInicial();
    }
}