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
        for(int i=0; i<getConjunt().size(); i++){

            if(getConjunt().get(i).getKey().es_fi_o_inici_de_ciutat()){
                obert++;
            }
            else if(getConjunt().get(i).getKey().bandes_de_ciutat()==3){
                obert += 3;
                f.add(getConjunt().get(i).getKey());
            }
            else{
                obert += 2;
                f.add(getConjunt().get(i).getKey());
            }

            if(i>0){
                obert -= 2;
            }
        }
        if(obert==2 && f.size()>3){
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


        for (int i =0; i<getConjunt().size(); i++){
            if((getConjunt().get(i).getKey().regio_c()=='V' || getConjunt().get(i).getKey().regio_c()=='E') && getConjunt().get(i).getKey().regio_c_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_c_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_c_seguidor()-1)+1);
            }
            if((getConjunt().get(i).getKey().regio_n()=='V' || getConjunt().get(i).getKey().regio_n()=='E') && getConjunt().get(i).getKey().regio_n_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_n_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_n_seguidor()-1)+1);
            }
            if((getConjunt().get(i).getKey().regio_e()=='V' || getConjunt().get(i).getKey().regio_e()=='E') && getConjunt().get(i).getKey().regio_e_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_e_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_e_seguidor()-1)+1);
            }
            if((getConjunt().get(i).getKey().regio_s()=='V' || getConjunt().get(i).getKey().regio_s()=='E') && getConjunt().get(i).getKey().regio_s_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_s_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_s_seguidor()-1)+1);
            }
            if((getConjunt().get(i).getKey().regio_o()=='V' || getConjunt().get(i).getKey().regio_o()=='E') && getConjunt().get(i).getKey().regio_o_seguidor()>0){
                pro.set(getConjunt().get(i).getKey().regio_o_seguidor()-1,pro.get(getConjunt().get(i).getKey().regio_o_seguidor()-1)+1);
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

        int puntuacio = 0;

        for(int i=0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).getKey().teEscut()){
                puntuacio += 2;
            }
        }

        puntuacio += getConjunt().size()*2;

        if(!tancat()){
            puntuacio /=2;
        }

        return puntuacio;
    }
}
