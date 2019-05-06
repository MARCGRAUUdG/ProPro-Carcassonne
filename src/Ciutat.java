public class Ciutat extends Estructura {

    public Ciutat(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat(){
        return true;
    }



    //Pre:---
    //Post:retorna punts
    public int punts(){
        //complet 2 punts per peça i escut, altrament 1
        return 0;
    }
}
