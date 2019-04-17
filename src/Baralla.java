import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Baralla {

    private int nFitxes;
    private Vector<Fitxa> vectorFitxes;
    private boolean buit;
    private int sentinella;

    Baralla(int nFit)
    {
        nFitxes = nFit;
        vectorFitxes = new Vector(nFitxes);
        for (int i=0; i<nFitxes; i++)
        {
            vectorFitxes.add(i, null);
        }
        buit = true;
        sentinella = 0;
    }

    ///Pre: ---
    ///Post: Agafa una fitxa de la baralla de fitxes i la retorna
    public Fitxa agafarFitxa()
    {
        Fitxa f = (Fitxa) vectorFitxes.get(sentinella);
        vectorFitxes.set(sentinella, null);
        actualitzarSentinella();
        return f;
    }

    ///Pre: f != NULL
    ///Post: S'afageix la fitxa f de forma ALEATÒRIA
    public void afegirFitxa(Fitxa f)
    {
        int aleatori = ThreadLocalRandom.current().nextInt(0, nFitxes-1);
        while (vectorFitxes.get(aleatori) != null)
        {
            aleatori++;
            if (aleatori == nFitxes)
            {
                aleatori = 0;
            }
        }

        buit = false;
        vectorFitxes.set(aleatori, f);
    }

    ///Pre: ---
    ///Post: Retorna si la baralla s'ha quedat sense fitxes
    public boolean esBuida()
    {
        return buit;
    }

    ///Pre: ---
    //Post: Coloca el sentinella a la posició més petita possible
    private void actualitzarSentinella()
    {
        /*while (vectorFitxes.get(sentinella) == 0)
        {
            sentinella++;
            if (sentinella == nFitxes-1)
            {
                sentinella = 0;
            }
        }*/
        sentinella++;
    }

    ///Pre: ---
    //Post: Mostra les fitxes de la baralla separades per un espai
    public void mostrar()
    {
        for (Fitxa i : vectorFitxes) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

}
