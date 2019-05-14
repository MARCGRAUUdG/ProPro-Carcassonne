import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tirada {

    private Jugador jugadorActual;
    private Baralla baralla;
    private Tauler tauler;
    private Fitxa fitxaActual;
    private ArrayList<Posicio> posicionDisponibles;

    List<Posicio> posicions;

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

    public void apretatAngleFitxa(Posicio pos){
        posaFitxa(pos);
    }

    private void posaFitxa(Posicio pos){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
        tauler.posarFitxaTauler(fitxaActual);
        if(jugadorActual.getHumanets()>0)
            Gui.posaSeleccioDeSeguidors(pos.getPosicioX(),pos.getPosicioY());
        else
            Joc.iniciaNouTorn();
    }

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



    ///Pre: ---
    ///Post: Gestiona la tirada d'un jugador controlat
   public void gestionarTiradaHuma()
    {
        Fitxa f = jugadorActual.agafarFitxaBaralla(baralla);
        posicions = tauler.getPosDisponibles(f);

        Gui.print("A quina posició esculls?");
        for (Posicio p : posicions)
        {
            Gui.print(p.toString());
        }
        //Usuari entra posició p

        Posicio p = new Posicio();
        jugadorActual.PosaFitxaAlTauler(p, tauler);
        if (calcularPunts(f))
        {
            actualitzarPunts(f);
        }
    }

    ///Pre: ---
    ///Post: gestiona la tirada d'un jugador màquina (bot)
    public void gestionarTiradaBot()
    {
        Fitxa f = jugadorActual.agafarFitxaBaralla(baralla);
        posicions = tauler.getPosDisponibles(f);

        int aleatori = ThreadLocalRandom.current().nextInt(0, posicions.size());
        jugadorActual.PosaFitxaAlTauler(posicions.get(aleatori), tauler);
        if (calcularPunts(f))
        {
            actualitzarPunts(f);
        }
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
