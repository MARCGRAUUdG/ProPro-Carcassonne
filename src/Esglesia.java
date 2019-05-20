import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Esglesia extends Estructura {

    public Esglesia(Fitxa inici, List<Character> r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat() {
        return getConjunt().size()==9;
    }

    //Pre:---
    //Post:retorna el propietari de l'esglesia si existeix altrament llista buida
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

    public char tipus(){
        return 'M';
    }

    //Pre:---
    //Post:retorna punts total de l'esglesia
    public int punts(){
        if(tancat()){
            return 9;
        }
        return getConjunt().size()-1;
    }
}
