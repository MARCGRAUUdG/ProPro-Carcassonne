import java.util.List;

public class Esglesia extends Estructura {

    public Esglesia(int propietari, Fitxa inici) {
        super(inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat() {
        return getConjunt().size()==9;
    }

    //Pre:---
    //Post:retorna el propietari de l'esglesia
    public Integer propietari(){
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

        return getConjunt().get(i).regio_c_seguidor();
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
