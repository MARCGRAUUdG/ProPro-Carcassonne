import java.util.ArrayList;
import java.util.List;

public class Esglesia extends Estructura {

    public Esglesia(Fitxa inici, Regio r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat() {
        return getConjunt().size()==9;
    }

    //Pre:---
    //Post:retorna el propietari de l'esglesia si existeix altrament -1
    /*public Integer propietari(){
        int i =0;
        boolean trobat = false;

        while (!trobat && i<getConjunt().size()){
            if(getConjunt().get(i).regio_c()=='M'){
                trobat = true;
            }
            else {
                i++;
            }
        }

        if(!trobat){
            return -1;
        }
        else {
            return getConjunt().get(i).regio_c_seguidor();
        }
    }*/
    public List<Integer> propietari(){
        return new ArrayList<>();
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
