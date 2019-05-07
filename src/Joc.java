import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Joc {
    private static LlegirFitxer _fitxer;
    private static ArrayList<Jugador> _jugadors;
    private static Tauler _tauler=new Tauler();
    private static Tirada _tiradaActual;
    private static Baralla _baralla;
    private static Fitxa _fInicial;
    private static int _jugadorActual=-1;

    public Joc(){

    }

    public static void repNomFitxer(String text){
        try {
            _fitxer.nomFitxer(text);
        } catch (FileNotFoundException e) {
            Gui.informarFitxerEntradaIncorrecte("No s'ha trobat el fitxer");
        }

        if(_fitxer.lecturaCorrecta()) {
            repDadesFitxer();
            IniciJoc();
        }
    }

    private static void repDadesFitxer() {
        _jugadors = _fitxer.getJugadors();
        _baralla = _fitxer.getBaralla();//TODO S'hauria de fer amb getBaralla()
        _fInicial = _fitxer.getInicial();
    }

    private static void posaFitxaInicial(){
        Posicio pos =new Posicio(5,5,0);
        _fInicial.setPosicio(pos);
        _tauler.posarFitxaTauler(_fInicial);
        Gui.posaFitxa(_fInicial);
    }

    private static void IniciJoc(){
        Gui.setupJugadors(_jugadors.size());
        Gui.iniciaTaulerGui();
        posaFitxaInicial();
        iniciaNouTorn();
    }

    public static void iniciaNouTorn(){
        _jugadorActual++;
        if(_jugadorActual>=_jugadors.size())_jugadorActual=0;
        _tiradaActual =new Tirada(_jugadors.get(_jugadorActual),_baralla,_tauler);
    }

    public static void apretatPerPosarFitxa(int x, int y, int rot) {
        _tiradaActual.apretatOpcionsDeFitxa(new Posicio(x,y,rot));
    }

    public static void apretatPerPosarSeguidor(int x, int y, char dir) throws Excepcio {
        _tiradaActual.apretatOpcionsDeSeguidor(x,y,dir);
        iniciaNouTorn();
    }
}