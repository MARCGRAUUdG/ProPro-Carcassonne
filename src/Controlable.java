import java.util.ArrayList;
import java.util.List;

public class Controlable extends Jugador {

    Controlable(int i)
    {
        super (i);
    }

    @Override
    public boolean esControlable() {
        return true;
    }

    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual al tauler i gui en la posicio pos
    public void posaFitxa(Posicio pos, Fitxa fitxaActual, Tauler tauler){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
        if(super.getHumanets()>0) {
            ArrayList<Character> posicions=tauler.onEsPotFicarSeguidor(fitxaActual);
            if(posicions.size()>0)Gui.posaSeleccioDeSeguidors(pos.getPosicioX(), pos.getPosicioY(), posicions);
            else{
                tauler.posarFitxaTauler(fitxaActual);
                Joc.iniciaNouTorn();
            }
        }else {
            tauler.posarFitxaTauler(fitxaActual);
            Joc.iniciaNouTorn();
            return;
        }
    }


}
