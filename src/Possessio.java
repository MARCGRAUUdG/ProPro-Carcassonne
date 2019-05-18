
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public abstract class Possessio {
    private List<Pair<Fitxa,List<Character>>> conjunt;

    public abstract boolean tancat();
    public abstract int punts();
    public abstract char tipus();
    public abstract List<Integer> propietari();
    //Pre:---
    //Post: guardar fitxa i regio
    public Possessio(Fitxa inici, List<Character> r){
        conjunt = new ArrayList<>();
        conjunt.add(new Pair<>(inici,r));
    }

    //Pre:---
    //Post:afegir les fitxes amb la seva regio de aux a la possessio actual
    public void unir_possessions(Possessio aux){

        for(int i=aux.getConjunt().size()-1; i>=0; i--){
            afegir_fitxa(aux.getConjunt().get(i).getKey(),aux.getConjunt().get(i).getValue());
        }
    }

    //Pre:---
    //Post: retorna cert si la regio r de la fitxa f esta en aquesta possessio altrament fals
    public boolean pertanyLaFitxa(Fitxa f, Character r){
        int i=0;
        boolean trobat=false;
        while(!trobat && i<conjunt.size()){
            if(conjunt.get(i).getKey().getPosicio()==f.getPosicio()&& conjunt.get(i).getValue().contains(r)) {
                trobat = true;
            }else i++;
        }
        return trobat;
    }

    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f,List<Character> r){
        conjunt.add(new Pair<>(f,r));
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


    //Pre:---
    //Post:retorna la llista de fitxes
    public List<Pair<Fitxa,List<Character>>> getConjunt(){
        return conjunt;
    }


    @Override
    public String toString() {
        return getConjunt().toString();
    }

}
