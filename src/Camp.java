import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

///@class Camp

///@brief Subtipus de possessio format per fitxes que tenen regions que són F

public class Camp extends Possessio {
    public Camp(Fitxa inici,List<Character> r) {
        super(inici,r);
    }

    ///@pre ---
    ///@post retorna punts totals del camp
    public int punts(ArrayList<Ciutat> llista){
        List<Pair<Fitxa,List<Character>>> lista = getConjunt();
        ArrayList<Ciutat> aux = new ArrayList<>(llista);
        int puntuacio = 0;

        for(int i=0; i<lista.size(); i++){
            Fitxa f = lista.get(i).getKey();

            if(f.bandes_de_ciutat()>0){
                boolean existeix = false;
                int compt = 0;
                while(!existeix && compt <aux.size()){
                    existeix=conteFitxa(aux.get(compt),f,lista.get(i).getValue());
                    compt++;
                }
                if(existeix && aux.get(compt-1).tancat()) {
                    puntuacio += 3;
                    aux.remove(compt - 1);
                }
            }
        }

        return puntuacio;
    }

    ///@pre ---
    ///@post retorna el/s propietari/s del camp altrament llista buida
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();

        for (int i =0; i<llista.size(); i++){
            Fitxa f = llista.get(i).getKey();

            if(f.regio_c()=='F' && f.regio_c_seguidor()>0){
                pro.set(f.regio_c_seguidor()-1,pro.get(f.regio_c_seguidor()-1)+1);
            }
            if(f.regio_n()=='F' && f.regio_n_seguidor()>0){
                pro.set(f.regio_n_seguidor()-1,pro.get(f.regio_n_seguidor()-1)+1);
            }
            if(f.regio_e()=='F' && f.regio_e_seguidor()>0){
                pro.set(f.regio_e_seguidor()-1,pro.get(f.regio_e_seguidor()-1)+1);
            }
            if(f.regio_s()=='F' && f.regio_s_seguidor()>0){
                pro.set(f.regio_s_seguidor()-1,pro.get(f.regio_s_seguidor()-1)+1);
            }
            if(f.regio_o()=='F' && f.regio_o_seguidor()>0){
                pro.set(f.regio_o_seguidor()-1,pro.get(f.regio_o_seguidor()-1)+1);
            }
        }

        return llistaPropietari(pro);
    }

    ///@pre ---
    ///@post  retorna el tipus de possessio que és
    public char tipus(){
        return 'F';
    }

    ///@pre ---
    ///@post retorna cert si la fitxa f amb les regions r esta a la ciutat c
    private boolean conteFitxa(Ciutat c, Fitxa f, List<Character> r){
        int i =0;
        boolean trobat = false, acabat = false;
        while (!trobat && i<c.getConjunt().size()){
            Pair<Fitxa,List<Character>> p = c.getConjunt().get(i);
            if(p.getKey()==f && p.getValue().equals(r)){
                trobat = true;
            }
            else {
                i++;
            }

        }

        return trobat;
    }

    ///@pre ---
    ///@post retorna false (mai es crida)
    public boolean tancat(){
        return false;
    }

    ///@pre ---
    ///@post retorna punts total de la possessio
    public int punts(){
        return 0;
    }

}
