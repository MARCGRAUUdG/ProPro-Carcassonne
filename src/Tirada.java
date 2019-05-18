import java.util.ArrayList;
import java.util.List;

public class Tirada {

    private Jugador jugadorActual;
    private Baralla baralla;
    private Tauler tauler;
    private Fitxa fitxaActual;
    private ArrayList<Posicio> posicionDisponibles;
    List<Posicio> posicions;

    ///Constructor per defecte de tirada
    Tirada(Jugador jActual, Baralla bActual, Tauler tActual)
    {
        Gui.print("---------Torn del jugador"+jActual.getId()+"---------");
        jugadorActual = jActual;
        baralla = bActual;
        tauler = tActual;

        fitxaActual = baralla.agafarFitxa();
        if(fitxaActual!=null) {
            posicionDisponibles = tauler.getPosDisponibles(fitxaActual);
            while (!baralla.esBuida() && posicionDisponibles.size() == 0) {
                Gui.print("Fitxa: " + fitxaActual.toString() + " descartada no encaixa en el tauler");
                fitxaActual = baralla.agafarFitxa();
                posicionDisponibles = tauler.getPosDisponibles(fitxaActual);
            }
            Gui.posaQuadresVerds(posicionDisponibles);
        }else{
            Gui.print("No hi han més fitxes a la baralla");
        }
        Gui.MostraBaralla(baralla.size(),fitxaActual);
    }

    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual a gui si nomes encaixa d'una manera en la posicio pos altrament posa seleccio de rotacio
    public void apretatOpcionsDeFitxa(Posicio pos)
    {
        ArrayList<Posicio> posDisp = posicionDisponibles;
        for  (int i=posDisp.size()-1;i>=0;i--) {
            if(posDisp.get(i).compareTo(pos)==-1)//Si coincideix x, y (rotacio no cal)
                posDisp.remove(i);
        }
        if(posDisp.size()==1)//Nomes hi ha una opcio per colocar fitxa
            posaFitxa(pos);
        else //En la mateixa posicio es pot posar fitxa diferents rotacions
            Gui.mostraOpcionsDeRotacioEnFitxa(posDisp,fitxaActual);
    }

    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual a gui en la posicio pos
    public void apretatAngleFitxa(Posicio pos){
        posaFitxa(pos);
    }

    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual al tauler i gui en la posicio pos
    private void posaFitxa(Posicio pos){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
        if(jugadorActual.getHumanets()>0) {
            Gui.posaSeleccioDeSeguidors(pos.getPosicioX(), pos.getPosicioY(), fitxaActual.regio_c());
        }else {
            tauler.posarFitxaTauler(fitxaActual);
            Joc.iniciaNouTorn();
        }
    }

    ///Pre:9<=x>=0 && 9<=y>=0, dir==('C' o 'N' o 'E' o 'S' o 'O' o 'X')
    ///Post:Posa fitxaActual al tauler amb el seguidor en la posicio x, y i regio dir
    public void apretatOpcionsDeSeguidor(int x, int y, char dir){
        //Gui.print(String.valueOf(dir));
        if (dir != 'X') {//X vol dir que l'usuari no vol ficar seguidor
            fitxaActual.assignar_seguidor(dir, jugadorActual.getId());
            Gui.posaSeguidor(x,y,dir,jugadorActual.getId());
            jugadorActual.setHumanets(jugadorActual.getHumanets()-1);
            Gui.setSeguidors(jugadorActual.getHumanets(),jugadorActual.getId());
        }
        tauler.posarFitxaTauler(fitxaActual);
        Joc.iniciaNouTorn();
    }
}
