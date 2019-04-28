import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Joc {
    private static LlegirFitxer _fitxer;
    private static ArrayList<Jugador> _jugadors;
    private static Tauler _tauler;
    private static Tirada _tirada;

    public Joc(){

    }

    public static void repNomFitxer(String text) throws FileNotFoundException {
        _fitxer.nomFitxer(text);
        //_jugadors=_fitxer.getJugadors();//TODO Llegir per la clase Llegirfitxer els jugadors
        Gui.setupJugadors(4,_jugadors);//TODO jugadors.size() en comptes de 4
        Gui.iniciaTaulerGui();

        Gui.setScore(1,24);
        Gui.setScore(2,15);
        Gui.setScore(4,3);
    }
}
