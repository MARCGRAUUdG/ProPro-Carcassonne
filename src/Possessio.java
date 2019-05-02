import java.util.ArrayList;
import java.util.List;

public class Possessio {
    private List<Integer> propietari;
    private List<Fitxa> conjunt;


    //Pre:---
    //Post: guardar fitxa i propietari
    public Possessio(int propietari, Fitxa inici){
        this.propietari = new ArrayList<>(4);
        this.propietari.set(propietari,1);
        conjunt = new ArrayList<>();
        conjunt.add(inici);
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
        propietari.set(j,1);
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
}
