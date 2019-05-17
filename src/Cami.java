import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cami extends Estructura {

    public Cami(Fitxa inici, Regio r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    @Override
    public boolean tancat(){
        int ultim = getConjunt().size()-1;
        if (getConjunt().size()>1 && getConjunt().get(0).getKey().es_fi_o_inici_de_cami() && getConjunt().get(ultim).getKey().es_fi_o_inici_de_cami()) {

            return true;
        }
        return false;
    }

    @Override
    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f, Regio r){

        if (getConjunt().get(0).getKey().es_fi_o_inici_de_cami() || !f.es_fi_o_inici_de_cami()) {
            getConjunt().add(new Pair<>(f,r));
        } else {
            getConjunt().add(getConjunt().get(0));
            getConjunt().set(0, new Pair<>(f,r));

        }
    }

    public char tipus(){
        return 'C';
    }

    //Pre:---
    //Post:retorna punts total del cami
    public int punts(){
        return getConjunt().size();
    }
}
