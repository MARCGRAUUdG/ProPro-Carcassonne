public class Esglesia extends Estructura {

    public Esglesia(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    public boolean esglesia_acabat(){

        if(getConjunt().size()==9) {
            return true;
        }
        return false;
    }

    public int punts(){
        return getConjunt().size();
    }
}
