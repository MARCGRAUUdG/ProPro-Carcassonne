public class Ciutat extends Estructura {

    public Ciutat(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    public boolean ciutat_acabat(){
        return true;
    }

    public void actualitzar_propietari(){

    }

    public int punts(){
        //complet 2 punts per peça i escut, altrament 1
        return 0;
    }
}
