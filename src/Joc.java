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

    ///Constuctor per defecte de Joc
    public Joc(){

    }

    ///Pre:--
    ///Post:Inicia variables rebudes per el fitxer amb nom text i inicia el Joc si el fitxer es correcte
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

    ///Pre:Fitxer inicialitzat correctament
    ///Post:Rep els jugadors, baralla i fitxa inicial del fitxer
    private static void repDadesFitxer() {
        _jugadors = _fitxer.getJugadors();
        _baralla = _fitxer.getBaralla();
        _fInicial = _fitxer.getInicial();
    }

    ///Pre:_fInicial iniciada i valor correcte, tauler a gui inicialitzat, tauler es buit
    ///Post: posa la fitxa inicial al tauler i la gui
    private static void posaFitxaInicial(){
        Posicio pos =new Posicio(5,5,0);
        _fInicial.setPosicio(pos);
        _tauler.posarFitxaTauler(_fInicial);
        Gui.posaFitxa(_fInicial);
    }

    ///Pre:Joc inicialitzat, repDadesFitxer() cridat, posaFitxaInicial() cridat anteriorment
    ///Post: Inicialitza el Joc per començar a jugar
    private static void IniciJoc(){
        Gui.setupJugadors(_jugadors.size());
        Gui.iniciaTaulerGui();
        posaFitxaInicial();
        for(int i=0;i<_jugadors.size();i++)//Inicialitza el nº de seguidors de cada jugador
            Gui.setSeguidors(_jugadors.get(i).getHumanets(),_jugadors.get(i).getId());
        iniciaNouTorn();
    }

    ///Pre:IniciJoc() cridat anteriorment
    ///Post:Comença un nou torn
    public static void iniciaNouTorn(){
        _jugadorActual++;
        if(_jugadorActual>=_jugadors.size())_jugadorActual=0;
        _tiradaActual =new Tirada(_jugadors.get(_jugadorActual),_baralla,_tauler);
    }

    ///Pre:9<=x>=0 && 9<=y>=0
    ///Post:Li diu a tiradaActual la posicio on l'usuari vol colocar una fitxa
    public static void apretatPerPosarFitxa(int x, int y, int rot) {
        _tiradaActual.apretatOpcionsDeFitxa(new Posicio(x,y,rot));
    }

    ///Pre:9<=x>=0 && 9<=y>=0, dir==('C' o 'N' o 'E' o 'S' o 'O' o 'X')
    ///Post:Li diu a tiradaActual la posicio on l'usuari vol colocar el seguidor
    public static void apretatPerPosarSeguidor(int x, int y, char dir){
        _tiradaActual.apretatOpcionsDeSeguidor(x,y,dir);
    }

    ///Pre:pos inicialitzat i es correcte
    ///Post:Li diu a tiradaActual la posicio on l'usuari vol colocar la fitxa especificant la rotacio dintre de pos
    public static void apretatAngleFitxa(Posicio pos) {
        _tiradaActual.apretatAngleFitxa(pos);
    }

    ///Pre:4<=jugador>=1
    ///Post:Incrementa el nº de seguidors del Jugador jugador +1
    public static void AfegeixSeguidorAJugador(int jugador) {
        Gui.print("Jugador"+jugador+" +1 Seguidor");
        _jugadors.get(jugador-1).incrementaHumanets();
        Gui.setSeguidors(_jugadors.get(jugador-1).getHumanets(),jugador);
    }

    ///Pre:4<=jugador>=1
    ///Post:Puntuacio actual del Jugador jugador + puntuacio
    public static void AfegeixPuntuacioAJugador(int jugador, int puntuacio,char reg) {
        _jugadors.get(jugador-1).incrementaPuntuacio(puntuacio);
        Gui.print("Regio "+reg+ ", Jugador: "+jugador+" +"+puntuacio+" punts");
        Gui.setScore(jugador,_jugadors.get(jugador-1).getPuntuacio());
    }

    //Pre:Partida iniciada
    //Post:Assigna els punts de les possessions que no han sigut tancades i imprimeix per pantalla menu de final de partida amb ranking
    public static void finalitzaJoc() {
        Gui.print("---------Partida Finalitzada---------");
        _tauler.assignaPuntsAPossessionsSenseTancar();
        Gui.print("--------------Puntuació--------------");
        int pmax=0;

        for(int i=0;i<_jugadors.size();i++){//Agafa la puntuacio maxima
            Gui.print("Jugador"+_jugadors.get(i).getId()+" ->"+_jugadors.get(i).getPuntuacio()+" punts");
            if(pmax<_jugadors.get(i).getPuntuacio())
                pmax=_jugadors.get(i).getPuntuacio();
        }
        ArrayList<Integer> guanyadors=new ArrayList<>();
        for(int i=0;i<_jugadors.size();i++){//Agafa els jugadors amb la puntuacio maxima
            if(_jugadors.get(i).getPuntuacio()==pmax)
                guanyadors.add(i+1);
        }
        Gui.mostraGuanyadors(guanyadors,pmax);
    }
}