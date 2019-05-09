import java.util.ArrayList;

public class Camp extends Possessio {
    public Camp(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    //Pre:---
    //Post:retorna punts totals de camp
    public int punts(ArrayList<Ciutat> llista){

        ArrayList<Ciutat> aux = new ArrayList<>(llista);
        int puntuacio = 0;

        for(int i=0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).te_ciutat()){
                boolean existeix = false;
                int compt = 0;
                while(!existeix && compt <aux.size()){
                    existeix=conteFitxa(aux.get(compt),getConjunt().get(i));
                    compt++;
                }
                if(existeix) {
                    puntuacio += 3;
                    aux.remove(compt - 1);
                }
            }
        }

        return puntuacio;
    }

    //Pre:---
    //Post:retorna cert si la fitxa f esta a la ciutat c
    private boolean conteFitxa(Ciutat c, Fitxa f){
        int i =0;
        boolean trobat = false;
        while (!trobat && i<c.getConjunt().size()){
            if(c.getConjunt().get(i)==f){
                trobat = true;
            }
            else {
                i++;
            }

        }

        return trobat;
    }

}
