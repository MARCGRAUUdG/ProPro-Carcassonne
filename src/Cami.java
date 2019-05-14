public class Cami extends Estructura {

    public Cami(Fitxa inici) {
        super(inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat(){
        int ultim = getConjunt().size()-1;
        if (getConjunt().size()>1 && getConjunt().get(0).es_fi_o_inici_de_cami() && getConjunt().get(ultim).es_fi_o_inici_de_cami()) {

            return true;
        }
        return false;
    }

    @Override
    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f){
        if(getConjunt().get(0).es_fi_o_inici_de_cami() || !f.es_fi_o_inici_de_cami()){
            getConjunt().add(f);
        }
        else{
            getConjunt().add(getConjunt().get(0));
            getConjunt().set(0,f);
        }
    }

    //Pre:---
    //Post:retorna punts total del cami
    public int punts(){
        return getConjunt().size();
    }
}
