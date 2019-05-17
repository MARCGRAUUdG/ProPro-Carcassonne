
import java.lang.reflect.Array;
import java.util.*;

public abstract class Possessio {
    private List<Fitxa> conjunt;

    public abstract boolean tancat();
    public abstract List<Integer> propietari();
    public abstract int punts();
    public abstract char tipus();
    //Pre:---
    //Post: guardar fitxa i propietari
    public Possessio(Fitxa inici){
        conjunt = new ArrayList<>();
        conjunt.add(inici);
    }

    //Pre:---
    //Post:afegir les fitxes de aux a la possessio actual
    public void unir_possessions(Possessio aux){

        for(int i=aux.getConjunt().size()-1; i>=0; i--){
            afegir_fitxa(aux.getConjunt().get(i));
        }
    }

    public boolean pertanyLaFitxa(Fitxa f){
        int i=0;
        boolean trobat=false;
        while(!trobat && i<conjunt.size()){
            if(conjunt.get(i).getPosicio()==f.getPosicio())
                trobat=true;
            else i++;
        }
        return trobat;
    }

    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f){
        conjunt.add(f);
    }

    public void eliminar_fitxa(Fitxa f){
        for (Fitxa fitxa : conjunt)
        {
            if (f == fitxa)
            {
                conjunt.remove(f);
            }
        }
    }

    //Pre:---
    //Post:retorna la llista de fitxes
    public List<Fitxa> getConjunt(){
        return conjunt;
    }

    //Pre: la llista propietari a de tenir el numero de seguidor que te cada jugador a la possessio
    //Post: retorna una llista dels propietaris de la possesio
    public List<Integer> llistaPropietari(List<Integer> propietari){
        int major = 0;
        for (int i=0; i<propietari.size(); i++){
            if(propietari.get(i) > major){
                major = propietari.get(i);
            }
        }

        List<Integer> sol = new ArrayList<>();
        for (int i =0; i<propietari.size(); i++){
            if(propietari.get(i) == major && major > 0){
                sol.add(i+1);
            }
        }
        return sol;
    }

    @Override
    public String toString() {
        return getConjunt().toString();
    }
}
