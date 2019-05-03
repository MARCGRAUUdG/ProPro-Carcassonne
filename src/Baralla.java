import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Baralla {

    private int nFitxes;
    private List<Fitxa> llistaFitxes;
    private boolean buit;

    Baralla(int nFit)
    {
        nFitxes = nFit;
        llistaFitxes = new ArrayList<Fitxa>(nFitxes);
        buit = true;
    }

    public Baralla() {
        nFitxes=0;
        llistaFitxes = new ArrayList<Fitxa>();
        buit = true;
    }

    ///Pre: ---
    ///Post: Agafa una fitxa de la baralla de fitxes i la retorna
    public Fitxa agafarFitxa()
    {
        int aleatori = ThreadLocalRandom.current().nextInt(0, llistaFitxes.size());
        Fitxa f = (Fitxa) llistaFitxes.get(aleatori);
        llistaFitxes.remove(aleatori);
        return f;
    }

    ///Pre: f != NULL
    ///Post: S'afageix la fitxa f de forma ALEATÒRIA
    public void afegirFitxa(Fitxa f)
    {
        buit = false;
        llistaFitxes.add(f);
    }

    ///Pre: ---
    ///Post: Retorna si la baralla s'ha quedat sense fitxes
    public boolean esBuida()
    {
        return buit;
    }

    @Override
    public String toString() {
        return "Baralla{" +
                "vectorFitxes=" + llistaFitxes +
                '}';
    }
}
