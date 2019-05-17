
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public abstract class Possessio {
    private List<Pair<Fitxa,Regio>> conjunt;
    private List<Integer> propietari;

    public abstract boolean tancat();
    public abstract int punts();
    public abstract char tipus();
    //Pre:---
    //Post: guardar fitxa i regio
    public Possessio(Fitxa inici, Regio r){
        this.propietari = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        conjunt = new ArrayList<>();
        conjunt.add(new Pair<Fitxa,Regio>(inici,r));
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
    public boolean pertanyLaFitxa(Fitxa f, Regio r){
        int i=0;
        boolean trobat=false;
        while(!trobat && i<conjunt.size()){
            if(conjunt.get(i).getKey().getPosicio()==f.getPosicio() && conjunt.get(i).getValue()==r)
                trobat=true;
            else i++;
        }
        return trobat;
    }

    //Pre:---
    //Post:guarda una nova fitxa
    public void afegir_fitxa(Fitxa f,Regio r){
        conjunt.add(new Pair<Fitxa,Regio>(f,r));
    }

    //Pre:---
    //Post:guardar el propietari j
    public void afegir_propietari(int j){
        propietari.set(j,propietari.get(j)+1);
    }

    //Pre:---
    //Post:retorna el/s propietari/s de la possessio
    public List<Integer> propietari(){
        List<Integer> prop = new ArrayList<>();

        int major =0;

        for(int i=0; i<propietari.size(); i++){
            if(propietari.get(i)>major){
                major = propietari.get(i);
            }
        }

        for(int i=0; i<propietari.size(); i++){
            if(propietari.get(i)==major){
                prop.add(i+1);
            }
        }

        return prop;
    }


    //Pre:---
    //Post:retorna la llista de fitxes
    public List<Pair<Fitxa,Regio>> getConjunt(){
        return conjunt;
    }


    @Override
    public String toString() {
        return getConjunt().toString();
    }

}
