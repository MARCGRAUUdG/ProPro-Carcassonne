import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ciutat extends Estructura {

    public Ciutat(Fitxa inici, List<Character> r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat(){
        int obert = 0;
        ArrayList<Fitxa> f = new ArrayList<>();
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();
        for(int i=0; i<llista.size(); i++){
            Fitxa fi = llista.get(i).getKey();
            if(fi.es_fi_o_inici_de_ciutat()){
                if(fi.bandes_de_ciutat()>1){
                    f.add(fi);
                }
                obert++;
            }
            else if(fi.bandes_de_ciutat()==3){
                obert += 3;
                f.add(fi);
            }
            else{
                obert += 2;
                f.add(fi);
            }

            if(i>0){
                obert -= 2;
            }
        }
        if(obert!=0 && f.size()>3){
            int rotacio0=0, rotacio90=0, rotacio180=0, rotacio270=0;
            for (int i=0; i<f.size();i++){
                if(f.get(i).getPosicio().getRotacio()==0){
                    rotacio0++;
                }
                else if (f.get(i).getPosicio().getRotacio()==90){
                    rotacio90++;
                }
                else if (f.get(i).getPosicio().getRotacio()==180){
                    rotacio180++;
                }
                else{
                    if (f.get(i).getPosicio().getRotacio()==270){
                        rotacio270++;
                    }
                }
            }

            if(rotacio0==1 && rotacio90==1 && rotacio180==1 && rotacio270==1){
                obert -=2;
            }
        }
        return obert==0;
    }

    //Pre:---
    //Post:retorna el/s propietari/s de la ciutat altrament llista buida
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();

        for (int i =0; i<llista.size(); i++){
            Fitxa f = llista.get(i).getKey();
            if((f.regio_c()=='V' || f.regio_c()=='E') && f.regio_c_seguidor()>0){
                pro.set(f.regio_c_seguidor()-1,pro.get(f.regio_c_seguidor()-1)+1);
            }
            if((f.regio_n()=='V' || f.regio_n()=='E') && f.regio_n_seguidor()>0){
                pro.set(f.regio_n_seguidor()-1,pro.get(f.regio_n_seguidor()-1)+1);
            }
            if((f.regio_e()=='V' || f.regio_e()=='E') && f.regio_e_seguidor()>0){
                pro.set(f.regio_e_seguidor()-1,pro.get(f.regio_e_seguidor()-1)+1);
            }
            if((f.regio_s()=='V' || f.regio_s()=='E') && f.regio_s_seguidor()>0){
                pro.set(f.regio_s_seguidor()-1,pro.get(f.regio_s_seguidor()-1)+1);
            }
            if((f.regio_o()=='V' || f.regio_o()=='E') && f.regio_o_seguidor()>0){
                pro.set(f.regio_o_seguidor()-1,pro.get(f.regio_o_seguidor()-1)+1);
            }
        }
        return llistaPropietari(pro);
    }

    public char tipus(){
        return 'V';
    }

    //Pre:---
    //Post:retorna punts total de la ciutat
    public int punts(){
        List<Pair<Fitxa,List<Character>>> llista = getConjunt();
        int puntuacio = 0;

        for(int i=0; i<llista.size(); i++){
            if(llista.get(i).getKey().teEscut()){
                puntuacio += 2;
            }
        }

        puntuacio += llista.size()*2;

        if(!tancat()){
            puntuacio /=2;
        }

        return puntuacio;
    }
}
