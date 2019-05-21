import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

///@class Baralla

///@brief Classe baralla encarregada de la gestió de les Fitxa /es

public class Baralla {

    private List<Fitxa> llistaFitxes;///<Llista de Fitxa /es

    ///@pre --
    ///@post constructor per defecte
    Baralla()
    {
        llistaFitxes = new ArrayList<Fitxa>();
    }

    ///@pre --
    ///@post agafa una fitxa de la baralla de fitxes i la retorna
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

    ///@pre f != NULL
    ///@post s'afageix la fitxa f de forma ALEATÒRIA
    public void afegirFitxa(Fitxa f)
    {
        llistaFitxes.add(f);
    }

    ///@pre --
    ///@post retorna si la baralla s'ha quedat sense fitxes
    public boolean esBuida()
    {
        return llistaFitxes.isEmpty();
    }

    ///@pre --
    ///@post retorna la quantitat de Fitxa /es que hi ha a la Baralla
    public int size(){
        return llistaFitxes.size();
    }

    ///@pre --
    ///@post retorna la llista de fitxes de la Baralla
    @Override
    public String toString() {
        return llistaFitxes.toString();
    }
}
