import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Camp extends Possessio {
    public Camp(Fitxa inici) {
        super(inici);
    }

    //Pre:---
    //Post:retorna punts totals de camp
    public int punts(ArrayList<Ciutat> llista){

        ArrayList<Ciutat> aux = new ArrayList<>(llista);
        int puntuacio = 0;

        for(int i=0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).bandes_de_ciutat()>0){
                boolean existeix = false;
                int compt = 0;
                while(!existeix && compt <aux.size()){
                    existeix=conteFitxa(aux.get(compt),getConjunt().get(i));
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

    //Pre:---
    //Post:retorna el/s propietari/s del camp
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));

        for (int i =0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).regio_n()=='F' && getConjunt().get(i).regio_n_seguidor()>0){
                pro.set(getConjunt().get(i).regio_n_seguidor(),pro.get(getConjunt().get(i).regio_n_seguidor())+1);
            }
            if(getConjunt().get(i).regio_e()=='F' && getConjunt().get(i).regio_e_seguidor()>0){
                pro.set(getConjunt().get(i).regio_e_seguidor(),pro.get(getConjunt().get(i).regio_e_seguidor())+1);
            }
            if(getConjunt().get(i).regio_s()=='F' && getConjunt().get(i).regio_s_seguidor()>0){
                pro.set(getConjunt().get(i).regio_s_seguidor(),pro.get(getConjunt().get(i).regio_s_seguidor())+1);
            }
            if(getConjunt().get(i).regio_o()=='F' && getConjunt().get(i).regio_o_seguidor()>0){
                pro.set(getConjunt().get(i).regio_o_seguidor(),pro.get(getConjunt().get(i).regio_o_seguidor())+1);
            }
        }

        int major = 0;
        for (int i=0; i<pro.size(); i++){
            if(pro.get(i) > major){
                major = pro.get(i);
            }
        }

        List<Integer> sol = new ArrayList<>();
        for (int i =0; i<pro.size(); i++){
            if(pro.get(i) == major){
                sol.add(i+1);
            }
        }

        return sol;
    }

    //Pre:---
    //Post:retorna cert si la fitxa f esta a la ciutat c
    private boolean conteFitxa(Ciutat c, Fitxa f){
        int i =0;
        boolean trobat = false;
        while (!trobat && i<c.getConjunt().size()){
            if(c.getConjunt().get(i)==f){
                trobat = true;
            }
            else {
                i++;
            }

        }

        return trobat;
    }

}
