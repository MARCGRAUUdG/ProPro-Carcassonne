import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Joc {
    private static LlegirFitxer _fitxer;
    private static ArrayList<Jugador> _jugadors;
    private static Tauler _tauler;
    private static Tirada _tirada;
    private static int _nJugadors;
    private static Baralla _baralla;

    public Joc(){

    }

    public static void repNomFitxer(String text){
        boolean llegitOk=false;//TODO Treure llegitOk posteriorment
        try {
            _fitxer.nomFitxer(text);
            llegitOk=true;//TODO Treure llegitOk posteriorment
        } catch (FileNotFoundException e) {
            Gui.informarFitxerEntradaIncorrecte("No s'ha trobat el fitxer");
        }

        if(/*_fitxer.lecturaCorrecta()*/llegitOk) {//TODO Treure llegitOk posteriorment
            repDadesFitxer();
            IniciJoc();
        }
    }

    private static void repDadesFitxer() {
        _nJugadors = _fitxer.getJugadors();
    }

    private static void IniciJoc(){
        Gui.setupJugadors(_nJugadors);
        Gui.iniciaTaulerGui();
    }
}