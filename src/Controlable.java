import java.util.ArrayList;
import java.util.List;

///@class Controlable

///@brief Subclasse de la superclasse Jugador. Implementa els mètodes propis d'un jugador Controlable

public class Controlable extends Jugador {

    ///@pre indentificador de Jugador Controlable
    ///@post constructor per valor
    Controlable(int i)
    {
        super (i);
    }

    ///@pre --
    ///@post retorna false; no és Controlable
    public boolean esControlable() {
        return true;
    }

    ///@pre pos inicialitzat i es correcte
    ///@post posa fitxaActual al tauler i gui en la posicio pos
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
