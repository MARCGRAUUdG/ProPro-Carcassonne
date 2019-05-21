import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

///@class Esglesia

///@brief Subtipus de possessio format per fitxes que tenen regions que són M

public class Esglesia extends Estructura {

    public Esglesia(Fitxa inici, List<Character> r) {
        super(inici,r);
    }

    ///@pre ---
    ///@post retorna cert si la l'esglesia esta completa altrament false
    public boolean tancat() {
        return getConjunt().size()==9;
    }

    ///@pre ---
    ///@post si existeix retorna el propietari de l'esglesia altrament llista buida
    public List<Integer> propietari(){
        int i =0;
        boolean trobat = false;
        ArrayList<Integer> s = new ArrayList<>();
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();

        while (!trobat && i<llista.size()){
            Fitxa f = llista.get(i).getKey();
            if(f.regio_c()=='M'){
                trobat = true;
                if(f.regio_c_seguidor()>0) {
                    s.add(f.regio_c_seguidor());
                }
            }
            else {
                i++;
            }
        }
        return s;
    }

    ///@pre ---
    ///@post  retorna el tipus de possessio que és
    public char tipus(){
        return 'M';
    }

    ///@pre ---
    ///@post retorna punts total de l'esglesia
    public int punts(){
        if(tancat()){
            return 9;
        }
        return getConjunt().size()-1;
    }
}
