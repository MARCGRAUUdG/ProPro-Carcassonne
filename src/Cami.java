import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

///@class Cami

///@brief Descripcio de la clase...

public class Cami extends Estructura {

    public Cami(Fitxa inici, List<Character> r) {
        super(inici,r);
    }

    ///@pre ---
    ///@post retorna cert si la possessio esta completa altrament false
    @Override
    public boolean tancat(){
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();
        int ultim = llista.size()-1;

        if (llista.size()>1 && llista.get(0).getKey().es_fi_o_inici_de_cami() && llista.get(ultim).getKey().es_fi_o_inici_de_cami()) {
            return true;
        }
        return false;
    }

    @Override
    ///@pre ---
    ///@post guarda una nova fitxa f amb les regions r
    public void afegir_fitxa(Fitxa f, List<Character> r){
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();

        if (llista.get(0).getKey().es_fi_o_inici_de_cami() || !f.es_fi_o_inici_de_cami()) {
            llista.add(new Pair<>(f,r));
        } else {
            llista.add(getConjunt().get(0));
            llista.set(0, new Pair<>(f,r));

        }
    }

    public char tipus(){
        return 'C';
    }

    ///@pre  pos < getConjunt.size(), regio = C o N o E o S o O
    ///@post  propietari de la fitxa que esta a la regio r afegit a la llista p
    private void afegir_propietari(List<Integer> p, Fitxa f, char regio){

        if(regio =='C'){
            p.set(f.regio_c_seguidor() - 1, p.get(f.regio_c_seguidor() - 1) + 1);
        }
        else if(regio =='N'){
            p.set(f.regio_n_seguidor() - 1, p.get(f.regio_n_seguidor() - 1) + 1);
        }
        else if(regio == 'E'){
            p.set(f.regio_e_seguidor() - 1, p.get(f.regio_e_seguidor() - 1) + 1);
        }
        else if(regio == 'S'){
            p.set(f.regio_s_seguidor() - 1, p.get(f.regio_s_seguidor() - 1) + 1);
        }
        else{
            p.set(f.regio_o_seguidor() - 1, p.get(f.regio_o_seguidor() - 1) + 1);
        }
    }

    ///@pre ---
    ///@post retorna el/s propietari/s del cami altrament llista buida
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();

        for (int i =0; i<llista.size(); i++){
            Fitxa f = llista.get(i).getKey();

            if (llista.get(i).getKey().regio_c()=='X'){
                char r = llista.get(i).getValue().get(0);

                if (r == 'N' && f.regio_n_seguidor() > 0) {
                    afegir_propietari(pro,f,'N');
                }
                else if(r == 'E' && f.regio_e_seguidor() > 0){
                   afegir_propietari(pro,f,'E');
                }
                else if (r == 'S' && f.regio_s_seguidor() > 0) {
                    afegir_propietari(pro,f,'S');
                }
                else{
                    if (r == 'O' && f.regio_o_seguidor() > 0) {
                        afegir_propietari(pro,f,'O');
                    }
                }
            }
            else {
                if (f.regio_c() == 'C' && f.regio_c_seguidor() > 0) {
                    afegir_propietari(pro,f,'C');
                }
                if (f.regio_n() == 'C' && f.regio_n_seguidor() > 0) {
                    afegir_propietari(pro,f,'N');
                }
                if (f.regio_e() == 'C' && f.regio_e_seguidor() > 0) {
                    afegir_propietari(pro,f,'E');
                }
                if (f.regio_s() == 'C' && f.regio_s_seguidor() > 0) {
                    afegir_propietari(pro,f,'S');
                }
                if (f.regio_o() == 'C' && f.regio_o_seguidor() > 0) {
                    afegir_propietari(pro,f,'O');
                }
            }
        }

        return llistaPropietari(pro);
    }

    ///@pre ---
    ///@post retorna punts total del cami
    public int punts(){
        return getConjunt().size();
    }

}
