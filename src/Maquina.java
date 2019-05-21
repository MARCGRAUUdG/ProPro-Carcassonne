import java.util.List;

///@class Maquina

///@brief Subclasse de la superclasse Jugador. Implementa els mètodes propis d'un jugador no Controlable

public class Maquina extends Jugador {

    ///@pre indentificador de Jugador Maqunia
    ///@post constructor per valor
    Maquina(int i)
    {
        super (i);
    }

    ///@pre --
    ///@post retorna false; no és Controlable
    public boolean esControlable() {
        return false;
    }

    ///@pre pos inicialitzat i es correcte
    ///@post posa fitxaActual al tauler i gui en la posicio pos
    public void posaFitxa(Posicio pos, Fitxa fitxaActual, Tauler tauler){
        fitxaActual.setPosicio(pos);
        Gui.posaFitxa(fitxaActual);
    }
}
