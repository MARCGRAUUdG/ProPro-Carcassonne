public class Esglesia extends Estructura {

    public Esglesia(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    public boolean tancat() {
        return getConjunt().size()==9;
    }

    public int punts(){
        if(tancat()){
            return 9;
        }
        return 0;
    }
}
