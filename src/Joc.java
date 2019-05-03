import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Joc {
    private static LlegirFitxer _fitxer;
    private static ArrayList<Jugador> _jugadors;
    private static Tauler _tauler;
    private static Tirada _tirada;
    private static Baralla _baralla;
    private static Fitxa _fInicial;

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
        _baralla = _fitxer.getBaralla();
        _fInicial = _fitxer.getInicial();
    }

    private static void posaFitxaInicial(){
        Posicio pos =new Posicio(5,5,0);
        _fInicial.setPosicio(pos);
        Gui.posaFitxa(_fInicial);
    }

    private static void IniciJoc(){
        Gui.setupJugadors(_jugadors.size());
        Gui.iniciaTaulerGui();
        posaFitxaInicial();
        
        ArrayList<Posicio> alp = null;//TODO S'ha de treure la llista Posicions ha de venir extern
        mostraOpcions(alp);
    }

    public static void mostraOpcions(ArrayList<Posicio> alp){
        alp=new ArrayList<Posicio>();//TODO treure
        alp.add(new Posicio(5,4,0));//TODO treure es nomes per mostrar ja haurien de estar inicialitzades
        alp.add(new Posicio(5,6,90));//TODO treure
        alp.add(new Posicio(6,5,0));//TODO treure
        alp.add(new Posicio(4,5,90));//TODO treure

        Gui.posaQuadresVerds(alp);
    }

    public static void apretatPerPosarFitxa(int x, int y, int rot) {
        /*TODO TREURE*/Fitxa f= null;
        /*TODO TREURE*/try {
        /*TODO TREURE*/    f = new Fitxa("CFCVC");
        /*TODO TREURE*/} catch (Excepcio excepcio) {
        /*TODO TREURE*/    excepcio.printStackTrace();
        /*TODO TREURE*/}
        /*TODO TREURE*/f.setPosicio(new Posicio(x,y,rot));
        Gui.posaFitxa(f);//TODO s'ha de posar la primera fitxa de la baralla
        Gui.posaSeleccioDeSeguidors(x,y);
    }

    public static void apretatPerPosarSeguidor(int x, int y, char dir) {
        Gui.posaSeguidor(x,y,dir,1);
    }
}