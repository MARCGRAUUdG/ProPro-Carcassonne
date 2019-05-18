import java.util.ArrayList;
import java.util.List;

/**
 * Classe encarregada de gestionar els torns dels diferents jugadors. Aquesta actua diferent segons si el jugador
 * es controlable o es maquina.
 *
 * En el cas de ser controlable fa crides a Gui per deixar escollir els moviments que el jugador huma vulgui.
 *
 * En el cas de ser maquina fa la tirada la màquina sola amb una certa intel·ligencia per no deixar-se guanyar.
 */

public class Tirada {

    private Jugador jugadorActual;
    private Baralla baralla;
    private Tauler tauler;
    private Fitxa fitxaActual;
    private ArrayList<Posicio> posicionsDisponibles;
    List<Posicio> posicions;

    ///Constructor per defecte de tirada

    Tirada(Jugador jActual, Baralla bActual, Tauler tActual)
    {
        Gui.print("---------Torn del jugador"+jActual.getId()+"---------");
        jugadorActual = jActual;
        baralla = bActual;
        tauler = tActual;

        fitxaActual = baralla.agafarFitxa();

        posicionsDisponibles = tauler.getPosDisponibles(fitxaActual);

        if (jugadorActual.esControlable())
        {
            if(fitxaActual!=null) {
                while (!baralla.esBuida() && posicionsDisponibles.size() == 0) {
                    Gui.print("Fitxa: " + fitxaActual.toString() + " descartada no encaixa en el tauler");
                    fitxaActual = baralla.agafarFitxa();
                    posicionsDisponibles = tauler.getPosDisponibles(fitxaActual);
                }
                Gui.posaQuadresVerds(posicionsDisponibles);
            }else{
                Gui.print("No hi han més fitxes a la baralla");
            }
            Gui.MostraBaralla(baralla.size(),fitxaActual);
        } else
        {
            int puntsMax = 0, punts;
            Posicio posicioPuntsMax = null;

            //assert false; //<--?
            for (Posicio posicio_disponible : posicionsDisponibles)
            {
                punts = tauler.simularPunts(posicio_disponible, fitxaActual);
                Gui.print(String.valueOf(punts)+posicio_disponible.toString());
                if (punts > puntsMax)
                {
                    puntsMax = punts;
                    posicioPuntsMax = posicio_disponible;
                    Gui.print("Posicio seleccionada="+posicioPuntsMax.toString());
                }
            }
            if (puntsMax == 0)
            {
                int aleatori = (int) (Math.random() * posicionsDisponibles.size()-1);
                posicioPuntsMax = posicionsDisponibles.get(aleatori);
            }
            Gui.print("PuntsMax:"+Integer.toString(puntsMax));
            Gui.print(posicioPuntsMax.toString());
            posaFitxa(posicioPuntsMax);
            Gui.MostraBaralla(baralla.size(),fitxaActual);
        }
    }


    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual a gui si nomes encaixa d'una manera en la posicio pos altrament posa seleccio de rotacio
    public void apretatOpcionsDeFitxa(Posicio pos)
    {
        ArrayList<Posicio> posDisp = posicionsDisponibles;
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
            ArrayList<Character> posicions=tauler.onEsPotFicarSeguidor(fitxaActual);
            Gui.posaSeleccioDeSeguidors(pos.getPosicioX(), pos.getPosicioY(), posicions);
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
