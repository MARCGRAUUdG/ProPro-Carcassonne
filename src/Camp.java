import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Camp extends Possessio {
    public Camp(Fitxa inici,List<Character> r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna punts totals de camp
    public int punts(ArrayList<Ciutat> llista){

        ArrayList<Ciutat> aux = new ArrayList<>(llista);
        int puntuacio = 0;

        for(int i=0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).getKey().bandes_de_ciutat()>0){
                boolean existeix = false;
                int compt = 0;
                while(!existeix && compt <aux.size()){
                    existeix=conteFitxa(aux.get(compt),getConjunt().get(i).getKey(),getConjunt().get(i).getValue());
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

    public boolean tancat() {
        return false;
    }

    public int punts() {
        return 0;
    }

    //Pre:---
    //Post:retorna el/s propietari/s del camp altrament llista buida
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));

        for (int i =0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).getKey().regio_c()=='F' && getConjunt().get(i).getKey().regio_c_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_c_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_c_seguidor()-1)+1);
            }
            if(getConjunt().get(i).getKey().regio_n()=='F' && getConjunt().get(i).getKey().regio_n_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_n_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_n_seguidor()-1)+1);
            }
            if(getConjunt().get(i).getKey().regio_e()=='F' && getConjunt().get(i).getKey().regio_e_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_e_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_e_seguidor()-1)+1);
            }
            if(getConjunt().get(i).getKey().regio_s()=='F' && getConjunt().get(i).getKey().regio_s_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_s_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_s_seguidor()-1)+1);
            }
            if(getConjunt().get(i).getKey().regio_o()=='F' && getConjunt().get(i).getKey().regio_o_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_o_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_o_seguidor()-1)+1);
            }
        }

        return llistaPropietari(pro);
    }

    public char tipus(){
        return 'F';
    }

    //Pre:---
    //Post:retorna cert si la fitxa f esta a la ciutat c
    private boolean conteFitxa(Ciutat c, Fitxa f, List<Character> r){
        int i =0;
        boolean trobat = false, acabat = false;
        while (!trobat && i<c.getConjunt().size()){
            if(c.getConjunt().get(i).getKey()==f && c.getConjunt().get(i).getValue().equals(r)){
                trobat = true;
            }
            else {
                i++;
            }

        }

        return trobat;
    }

}
