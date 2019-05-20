import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

///@class Baralla

///@brief Descripcio de la clase...

public class Baralla {

    private List<Fitxa> llistaFitxes;///<Descripcio...

    Baralla()
    {
        llistaFitxes = new ArrayList<Fitxa>();
    }

    ///@pre  ---
    ///@post  Agafa una fitxa de la baralla de fitxes i la retorna
    public Fitxa agafarFitxa()
    {
        if(!esBuida()) {
            int aleatori = ThreadLocalRandom.current().nextInt(0, llistaFitxes.size());
            Fitxa f = (Fitxa) llistaFitxes.get(aleatori);
            llistaFitxes.remove(aleatori);
            return f;
        }else {
            return null;
        }
    }

    ///@pre  f != NULL
    ///@post  S'afageix la fitxa f de forma ALEATÒRIA
    public void afegirFitxa(Fitxa f)
    {
        llistaFitxes.add(f);
    }

    ///@pre  ---
    ///@post  Retorna si la baralla s'ha quedat sense fitxes
    public boolean esBuida()
    {
        return llistaFitxes.isEmpty();
    }

    public int size(){
        return llistaFitxes.size();
    }

    @Override
    public String toString() {
        return llistaFitxes.toString();
    }
}
