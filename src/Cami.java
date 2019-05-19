import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cami extends Estructura {

    public Cami(Fitxa inici, List<Character> r) {
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
    //Post:guarda una nova fitxa f amb les regions r
    public void afegir_fitxa(Fitxa f, List<Character> r){

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
    //Post:retorna el/s propietari/s del cami altrament llista buida
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        for (int i =0; i<getConjunt().size(); i++){
            if (getConjunt().get(i).getKey().regio_c()=='X'){
                char r = getConjunt().get(i).getValue().get(0);
                if (r == 'N' && getConjunt().get(i).getKey().regio_n_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_n_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_n_seguidor() - 1) + 1);
                }
                if(r == 'E' && getConjunt().get(i).getKey().regio_e_seguidor() > 0){
                    pro.set(getConjunt().get(i).getKey().regio_e_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_e_seguidor() - 1) + 1);
                }
                if (r == 'S' && getConjunt().get(i).getKey().regio_s_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_s_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_s_seguidor() - 1) + 1);
                }
                if (r == 'O' && getConjunt().get(i).getKey().regio_o_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_o_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_o_seguidor() - 1) + 1);
                }
            }
            else {
                if (getConjunt().get(i).getKey().regio_c() == 'C' && getConjunt().get(i).getKey().regio_c_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_c_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_c_seguidor() - 1) + 1);
                }
                if (getConjunt().get(i).getKey().regio_n() == 'C' && getConjunt().get(i).getKey().regio_n_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_n_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_n_seguidor() - 1) + 1);
                }
                if (getConjunt().get(i).getKey().regio_e() == 'C' && getConjunt().get(i).getKey().regio_e_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_e_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_e_seguidor() - 1) + 1);
                }
                if (getConjunt().get(i).getKey().regio_s() == 'C' && getConjunt().get(i).getKey().regio_s_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_s_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_s_seguidor() - 1) + 1);
                }
                if (getConjunt().get(i).getKey().regio_o() == 'C' && getConjunt().get(i).getKey().regio_o_seguidor() > 0) {
                    pro.set(getConjunt().get(i).getKey().regio_o_seguidor() - 1, pro.get(getConjunt().get(i).getKey().regio_o_seguidor() - 1) + 1);
                }
            }
        }

        return llistaPropietari(pro);
    }

    //Pre:---
    //Post:retorna punts total del cami
    public int punts(){
        return getConjunt().size();
    }

}
