import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ciutat extends Estructura {

    public Ciutat(Fitxa inici, Regio r) {
        super(inici,r);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat(){

        int obert = 0;
        for(int i=0; i<getConjunt().size(); i++){

            if(getConjunt().get(i).getKey().es_fi_o_inici_de_ciutat()){
                obert++;
            }
            else if(getConjunt().get(i).getKey().bandes_de_ciutat()==3){
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
