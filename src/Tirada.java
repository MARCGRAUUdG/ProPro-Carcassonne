import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    //Pre: Jugador actual, baralla utilitzada i el tauler per poder començar una tirada
    //Post: Tirada començada
    Tirada(Jugador jActual, Baralla bActual, Tauler tActual)
    {
        Gui.print("---------Torn del jugador"+jActual.getId()+"---------"+jActual.esControlable());
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
            int puntsMax = 0;
            ArrayList<Integer> punts = new ArrayList<>();
            Posicio posicioPuntsMax = new Posicio();

            assert false; //<--?
            for (Posicio posicio_disponible : posicionsDisponibles)
            {
                punts = posicio_disponible.simularPunts(fitxaActual); //Llista amb els punts corresponents a les 4 rotacions
                int angle = 0; //angle de la fitxa a la posicio
                for (int puntsRotacio : punts)
                {
                    if (puntsRotacio >= puntsMax)
                    {
                        puntsMax = puntsRotacio;
                        posicioPuntsMax = posicio_disponible;
                        posicioPuntsMax.setRotacio(angle);
                    }
                    angle += 90;
                }
                Gui.print(posicio_disponible.toString());
            }
            posaFitxa(posicioPuntsMax);
            Gui.MostraBaralla(baralla.size(),fitxaActual);
        }
    }

    //Pre: Posicio pos != NULL
    //Post: Coloca la fitxa a la posició passada per paràmetre a la Gui i crida el mètode per assignar la rotació
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

    //Pre: Posicio pos != NULL
    //Post: Crida el mètode per colocar la fitxa definitivament un cop escollit l'angle desitjat
    public void apretatAngleFitxa(Posicio pos){
        posaFitxa(pos);
    }

    //Pre: Posicio pos != NULL
    //Post: Coloca la fitxa definitivament un cop escollit l'angle desitjat
    private void posaFitxa(Posicio pos){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
        tauler.posarFitxaTauler(fitxaActual);
        if (jugadorActual.esControlable())
        {
            if(jugadorActual.getHumanets()>0)
                Gui.posaSeleccioDeSeguidors(pos.getPosicioX(),pos.getPosicioY());
            else
                Joc.iniciaNouTorn();
        } else
        {
            //TODO: Asignar seguidor
            if(jugadorActual.getHumanets()>0)
                Gui.posaSeleccioDeSeguidors(pos.getPosicioX(),pos.getPosicioY());
            else
                Joc.iniciaNouTorn();
        }
    }

    //Pre: Posició correcta
    //Post: Assigna el seguidor a la Regió corresponent i inicia un nou torn
    public void apretatOpcionsDeSeguidor(int x, int y, char dir){
        //Gui.print(String.valueOf(dir));
        if (dir != 'X') {//X vol dir que l'usuari no vol ficar seguidor
            fitxaActual.assignar_seguidor(dir, jugadorActual.getId());
            Gui.posaSeguidor(x,y,dir,jugadorActual.getId());
            jugadorActual.setHumanets(jugadorActual.getHumanets()-1);
            Gui.setSeguidors(jugadorActual.getHumanets(),jugadorActual.getId());
            //TODO Afegir seguidor de la fitxa del tauler
            //TODO Assignar possesio?
        }
        Joc.iniciaNouTorn();
    }

    ///Pre: Fitxa f actual
    ///Post: Cert si és necessari calcular els punts després de colocar la fitxa (s'ha completat una possessio)
    public boolean calcularPunts(Fitxa f)
    {
        return tauler.tencaRegions(f);
    }

    ///Pre: Fitxa f actual
    ///Post: Actualitza els punts de la/les regions completada/es de la fitxa actual
    public void actualitzarPunts(Fitxa f)
    {
        tauler.actualitzarPunts(f);
    }
}
