import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tirada {

    private Jugador jugadorActual;
    private Baralla baralla;
    private Tauler tauler;

    List<Posicio> posicions;

    Tirada(Jugador jActual, Baralla bActual, Tauler tActual)
    {
        jugadorActual = jActual;
        baralla = bActual;
        tauler = tActual;
    }

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

    public boolean calcularPunts(Fitxa f)
    {
        return tauler.tencaRegions(f);
    }

    public void actualitzarPunts(Fitxa f)
    {
        tauler.actualitzarPunts(f);
    }
}
