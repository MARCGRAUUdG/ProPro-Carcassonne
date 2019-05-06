
import java.util.ArrayList;
import java.util.List;

public abstract class Possessio {
    private List<Integer> propietari;
    private List<Fitxa> conjunt;

    //Pre:---
    //Post: guardar fitxa i propietari
    public Possessio(int propietari, Fitxa inici){
        this.propietari = new ArrayList<Integer>();
        this.propietari.add(propietari,1);
        conjunt = new ArrayList<>();
        conjunt.add(inici);
    }

    //Pre:---
    //Post:afegir les fitxes i els propietaris a la possessio actual
    public void unir_possessions(List<Integer> propietari, List<Fitxa> conjunt){

        for(int i=0; i<propietari.size(); i++){
            if(propietari.get(i)!=null) {
                afegir_propietari(propietari.get(i));
            }
        }

        for(int i=conjunt.size()-1; i>=0; i--){
            afegir_fitxa(conjunt.get(i));
        }
    }

    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f){
        conjunt.add(f);
    }

    //Pre:---
    //Post:guarda el nou propietari
    public void afegir_propietari(int j){
        if(propietari.get(j)!=null){
            propietari.set(j,propietari.get(j)+1);
        }
        propietari.add(j,1);
    }

    //Pre:---
    //Post:treu els propietaris
    public void eliminar_propietari(){
        propietari.clear();
    }

    //Pre:---
    //Post:elimina el propietari i la llista de fitxes
    public void eliminar_possessio(){
        conjunt.clear();
        propietari.clear();
    }

    //Pre:---
    //Post:retorna la llista de propietaris
    public List<Integer> getPropietari(){
        return propietari;
    }

    //Pre:---
    //Post:retorna la llista de fitxes
    public List<Fitxa> getConjunt(){
        return conjunt;
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    abstract public boolean tancat ();

    //Pre:---
    //Post:retorna punts
    abstract public int punts();
}
