import java.util.ArrayList;

public class LlegirFitxer {
    public LlegirFitxer(){

    }

    public static void nomFitxer(String text) {
        //TODO Nomes ficar tota la info del fitxer en variables de la classe
        Gui.print("Fitxer '"+text+"' carregant");
    }

    public ArrayList<Jugador> getJugadors() {
        //TODO Retorna els jugadors inicialitzats despres de llegir el fitxer
        return new ArrayList<Jugador>();
    }
}
