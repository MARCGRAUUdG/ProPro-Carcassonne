public class Camp extends Possessio {
    public Camp(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    public boolean camp_acabat(){
        return true;
    }

    public int punts(){
        return 0;
    }
}
