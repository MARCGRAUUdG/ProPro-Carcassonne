import java.util.ArrayList;
import java.util.List;

public class Possessio {
    private Jugador propietari;
    private List<Fitxa> conjunt;


    //Pre:---
    //Post: guardar fitxa i propietari
    public Possessio(Jugador propietari, Fitxa inici){
        this.propietari = propietari;
        conjunt = new ArrayList<>();
        conjunt.add(inici);
    }

    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f){
        conjunt.add(f);
    }

    //Pre:---
    //Post:elimina el propietari i la llista de fitxes
    public void eliminar_possessio(){
        conjunt.clear();
        propietari = null;
    }

    //Pre:---
    //Post:retorna el propietari
    public Jugador getPropietari(){
        return propietari;
    }

    //Pre:---
    //Post:retorna la llista de fitxes
    public List<Fitxa> getConjunt(){
        return conjunt;
    }
}
