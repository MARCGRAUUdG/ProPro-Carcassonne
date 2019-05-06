public class Camp extends Possessio {
    public Camp(int propietari, Fitxa inici) {
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
        return 0;
    }
}
