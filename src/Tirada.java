import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tirada {

    private Jugador jugadorActual;
    private Baralla baralla;

    List<Posicio> pos;

    Tirada(Jugador jActual, Baralla bActual)
    {
        jugadorActual = jActual;
        baralla = bActual;
    }

    public void gestionarTiradaHuma()
    {
        jugadorActual.agafarFitxaBaralla(baralla);
        /**
         * Agafar la informació corresponent de la gui (el que vol fer l'humà
         */
        Posicio p = new Posicio();
        jugadorActual.PosaFitxaAlTauler(p);
        if (calcularPunts())
        {
            actualitzarPunts();
        }
    }

    public void gestionarTiradaBot()
    {
        jugadorActual.agafarFitxaBaralla(baralla);
        /**
         * La GUI ens retorna una llista de posicions disponibles
         */
        int aleatori = ThreadLocalRandom.current().nextInt(0, pos.size());
        jugadorActual.PosaFitxaAlTauler(pos.get(aleatori));
        if (calcularPunts())
        {
            actualitzarPunts();
        }
    }

    public boolean calcularPunts()
    {
        return true;
    }

    public void actualitzarPunts()
    {

    }
}
