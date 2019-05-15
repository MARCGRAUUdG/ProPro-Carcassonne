
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Possessio {
    private List<Integer> propietari;
    private List<Fitxa> conjunt;

    //Pre:---
    //Post: guardar fitxa i propietari
    public Possessio(Fitxa inici){
        this.propietari = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        conjunt = new ArrayList<>();
        conjunt.add(inici);
    }

    public boolean tancat(){
        return true;
    }

    //Pre:---
    //Post:afegir les fitxes i els propietaris a la possessio actual
    public void unir_possessions(Possessio aux){

        for(int i=0; i<aux.tots_els_seguidors().size(); i++){
            if(aux.tots_els_seguidors().get(i)>0) {
                afegir_propietari(aux.tots_els_seguidors().get(i));
            }
        }

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

    //Pre:---
    //Post:guarda el nou propietari
    public void afegir_propietari(int j){
        propietari.set(j,propietari.get(j)+1);
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
    //Post:retorna el/s propietari/s de la possessio
    public List<Integer> getPropietari(){
        List<Integer> pro = new ArrayList<>();
        int major = 0;

        for (int i =0; i<propietari.size(); i++){
            if(propietari.get(i)>major){
                major = propietari.get(i);
            }
        }

        for (int i=0; i<propietari.size(); i++){
            if(propietari.get(i)==major){
                pro.add(i+1);
            }
        }

        return pro;
    }

    //Pre:---
    //Post:retorna el/s seguidor/s que esta/n a la possessio
    public List<Integer> tots_els_seguidors(){
        return propietari;
    }

    //Pre:---
    //Post:retorna la llista de fitxes
    public List<Fitxa> getConjunt(){
        return conjunt;
    }

    @Override
    public String toString() {
        return getConjunt().toString();
    }
}
