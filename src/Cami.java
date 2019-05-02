public class Cami extends Estructura {

    public Cami(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    public boolean cami_acabat(){
        int ultim = getConjunt().size()-1;
        if ((getConjunt().get(0).regio_c() == 'X' || getConjunt().get(0).regio_c() == 'V' || getConjunt().get(0).regio_c() == 'M')
                && (getConjunt().get(ultim).regio_c() == 'X' || getConjunt().get(ultim).regio_c() == 'V' || getConjunt().get(ultim).regio_c() == 'M')) {

            return true;
        }
        return false;
    }

    public int punts(){
        return getConjunt().size();
    }
}
