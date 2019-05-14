public class Esglesia extends Estructura {

    public Esglesia(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat() {
        return getConjunt().size()==9;
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
