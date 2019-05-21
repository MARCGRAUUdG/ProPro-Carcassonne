import java.util.List;

///@class Maquina

///@brief Descripcio de la clase...

public class Maquina extends Jugador {

    Maquina(int i)
    {
        super (i);
    }

    @Override
    public boolean esControlable() {
        return false;
    }

    ///Pre:pos inicialitzat i es correcte
    ///Post:Posa fitxaActual al tauler i gui en la posicio pos
    public void posaFitxa(Posicio pos, Fitxa fitxaActual, Tauler tauler){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
    }
}
