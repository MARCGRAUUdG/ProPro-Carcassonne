import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

///@class Tirada

///@brief Classe encarregada de gestionar els torns dels diferents jugadors. Aquesta actua diferent segons si el jugador
// * es controlable o es maquina. En el cas de ser controlable fa crides a Gui per deixar escollir els moviments que el jugador huma vulgui.
// * En el cas de ser maquina fa la tirada la màquina sola amb una certa intel·ligencia per no deixar-se guanyar.

public class Tirada {

    private Jugador jugadorActual;///<Jugador actual de la Tirada
    private Tauler tauler;///<Tauler utilitzat per la Tirada
    private Fitxa fitxaActual;///<Fitxa actual colocada
    private ArrayList<Posicio> posicionsDisponibles;///<Llista de posicions on es pot ficar la fitxaActual al Tauler

    ///@pre jugador actual, baralla actual i tauler actual != null per gestionar la tirada
    ///@post constructor per valor de tirada
    Tirada(Jugador jActual, Baralla bActual, Tauler tActual)
    {
        Gui.print("---------Torn del jugador"+jActual.getId()+"---------");
        jugadorActual = jActual;
        tauler = tActual;

        fitxaActual = bActual.agafarFitxa();

        if(fitxaActual!=null) {
            posicionsDisponibles = tauler.getPosDisponibles(fitxaActual);
            while (!bActual.esBuida() && posicionsDisponibles.size() == 0) {
                Gui.print("Fitxa: " + fitxaActual.toString() + " descartada no encaixa en el tauler");
                fitxaActual = bActual.agafarFitxa();
                posicionsDisponibles = tauler.getPosDisponibles(fitxaActual);
            }
            if (jugadorActual.esControlable()){
                Gui.posaQuadresVerds(posicionsDisponibles);
                Gui.MostraBaralla(bActual.size(),fitxaActual);
            }else{
                gestionarMaquina();
                Gui.print("Apreta botó de next torn per continuar");
                Gui.MostraBaralla(bActual.size(), fitxaActual);
                Gui.mostraNextTirada();
            }
        }else{
            Gui.print("No hi han més fitxes a la baralla");
            Gui.MostraBaralla(bActual.size(),fitxaActual);
            Joc.finalitzaJoc();
        }
    }

    ///@pre --
    ///@post gestiona la tirada d'un Jugador Maquina
    private void gestionarMaquina() {
        int puntsMax = 0;
        char regioMaxPuntsHum = 'X';
        Posicio posicioPuntsMax = null;
        Pair<Character, Integer> parellaSim;

        for (Posicio posicio_disponible : posicionsDisponibles)
        {
            parellaSim = tauler.simularPunts(posicio_disponible, fitxaActual, jugadorActual.getHumanets());

            if (parellaSim.getValue() > puntsMax)
            {
                puntsMax = parellaSim.getValue();
                regioMaxPuntsHum = parellaSim.getKey();
                posicioPuntsMax = posicio_disponible;
            }
        }
        if (puntsMax == 0)
        {
            int aleatori = (int) (Math.random() * posicionsDisponibles.size());
            posicioPuntsMax = posicionsDisponibles.get(aleatori);
        }
        jugadorActual.posaFitxa(posicioPuntsMax, fitxaActual, tauler);
        apretatOpcionsDeSeguidor(posicioPuntsMax.getPosicioX(),posicioPuntsMax.getPosicioY(),regioMaxPuntsHum);
    }

    ///@pre pos inicialitzat i es correcte
    ///@post Posa fitxaActual a gui si nomes encaixa d'una manera en la posicio pos altrament posa seleccio de rotacio
    public void apretatOpcionsDeFitxa(Posicio pos)
    {
        ArrayList<Posicio> posDisp= new ArrayList<>();
        for  (int i=0;i<posicionsDisponibles.size();i++) {
            if(posicionsDisponibles.get(i).compareTo(pos)!=-1){//Si coincideix x, y (rotacio no cal)
                posDisp.add(posicionsDisponibles.get(i));
            }
        }
        if(posDisp.size()==1)//Nomes hi ha una opcio per colocar fitxa
            jugadorActual.posaFitxa(pos, fitxaActual, tauler);
        else //En la mateixa posicio es pot posar fitxa diferents rotacions
            Gui.mostraOpcionsDeRotacioEnFitxa(posDisp,fitxaActual);
    }

    ///@pre pos inicialitzat i es correcte
    ///@post Posa fitxaActual a gui en la posicio pos
    public void apretatAngleFitxa(Posicio pos){
        jugadorActual.posaFitxa(pos, fitxaActual, tauler);
    }

    ///@pre 9<=x>=0 && 9<=y>=0, dir==('C' o 'N' o 'E' o 'S' o 'O' o 'X')
    ///@post Posa fitxaActual al tauler amb el seguidor en la posicio x, y i regio dir
    public void apretatOpcionsDeSeguidor(int x, int y, char dir){
        //Gui.print(String.valueOf(dir));
        if (dir != 'X') {//X vol dir que l'usuari no vol ficar seguidor
            fitxaActual.assignar_seguidor(dir, jugadorActual.getId());
            Gui.posaSeguidor(x,y,dir,jugadorActual.getId());
            jugadorActual.setHumanets(jugadorActual.getHumanets()-1);
            Gui.setSeguidors(jugadorActual.getHumanets(),jugadorActual.getId());
        }
        tauler.posarFitxaTauler(fitxaActual);
        if (jugadorActual.esControlable()) {Joc.iniciaNouTorn();}
    }
}
