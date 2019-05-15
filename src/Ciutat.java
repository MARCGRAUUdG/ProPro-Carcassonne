import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ciutat extends Estructura {

    public Ciutat(Fitxa inici) {
        super(inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat(){

        int obert = 0;
        for(int i=0; i<getConjunt().size(); i++){

            if(getConjunt().get(i).es_fi_o_inici_de_ciutat()){
                obert++;
            }
            else if(getConjunt().get(i).bandes_de_ciutat()==3){
                obert += 3;
            }
            else{
                obert += 2;
            }

            if(i>0){
                obert -= 2;
            }
        }
        return obert==0;
    }

    //Pre:---
    //Post:retorna el/s propietari/s de la ciutat altrament llista buida
    public List<Integer> propietari(){
        List<Integer> pro = new ArrayList<Integer>(Arrays.asList(0,0,0,0));

        for (int i =0; i<getConjunt().size(); i++){
            if((getConjunt().get(i).regio_n()=='V' || getConjunt().get(i).regio_n()=='E') && getConjunt().get(i).regio_n_seguidor()>0){
                pro.set(getConjunt().get(i).regio_n_seguidor(),pro.get(getConjunt().get(i).regio_n_seguidor())+1);
            }
            if((getConjunt().get(i).regio_e()=='V' || getConjunt().get(i).regio_e()=='E') && getConjunt().get(i).regio_e_seguidor()>0){
                pro.set(getConjunt().get(i).regio_e_seguidor(),pro.get(getConjunt().get(i).regio_e_seguidor())+1);
            }
            if((getConjunt().get(i).regio_s()=='V' || getConjunt().get(i).regio_s()=='E') && getConjunt().get(i).regio_s_seguidor()>0){
                pro.set(getConjunt().get(i).regio_s_seguidor(),pro.get(getConjunt().get(i).regio_s_seguidor())+1);
            }
            if((getConjunt().get(i).regio_o()=='V' || getConjunt().get(i).regio_o()=='E') && getConjunt().get(i).regio_o_seguidor()>0){
                pro.set(getConjunt().get(i).regio_o_seguidor(),pro.get(getConjunt().get(i).regio_o_seguidor())+1);
            }
        }

        return llistaPropietari(pro);
    }


    //Pre:---
    //Post:retorna punts total de la ciutat
    public int punts(){

        int puntuacio = 0;

        for(int i=0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).teEscut()){
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
