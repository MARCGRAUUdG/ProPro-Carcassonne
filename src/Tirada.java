import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tirada {

    private Jugador jugadorActual;
    private Baralla baralla;
    private Tauler tauler;
    private Fitxa fitxaActual;
    private ArrayList<Posicio> posicionsDisponibles;

    List<Posicio> posicions;

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

            while(!baralla.esBuida() && posicionsDisponibles.size()==0){
                Gui.print("Fitxa: "+fitxaActual.toString()+" descartada no encaixa en el tauler");
                fitxaActual = baralla.agafarFitxa();
                posicionsDisponibles = tauler.getPosDisponibles(fitxaActual);
            }
            Gui.posaQuadresVerds(posicionsDisponibles);
            Gui.MostraBaralla(baralla.size(),fitxaActual);
        } else
        {
            int puntsMax = 0, punts;
            Posicio posicioPuntsMax = new Posicio();
            for (Posicio posicio_disponible : posicionsDisponibles)
            {
                punts = posicio_disponible.simularPunts(fitxaActual);
                if (punts >= puntsMax)
                {
                    puntsMax = punts;
                    posicioPuntsMax = posicio_disponible;
                }
            }
            posaFitxa(posicioPuntsMax);
        }
    }

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

    public void apretatAngleFitxa(Posicio pos){
        posaFitxa(pos);
    }

    private void posaFitxa(Posicio pos){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
        tauler.posarFitxaTauler(fitxaActual);
        if (jugadorActual.esControlable())
        {
            Gui.posaSeleccioDeSeguidors(pos.getPosicioX(),pos.getPosicioY());
        } else
        {
            //TODO: Asignar seguidor
        }

    }

    public void apretatOpcionsDeSeguidor(int x, int y, char dir){
        //Gui.print(String.valueOf(dir));
        try{
            fitxaActual.assignar_seguidor(dir, jugadorActual.getId());
        }catch (Excepcio e){
            Gui.print("Posicio del seguidor incorrecte");
        }
        Gui.posaSeguidor(x,y,dir,jugadorActual.getId());
        //TODO: tauler.posar_seguidor(dir, fitxaActual);
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
