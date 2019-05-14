public class Ciutat extends Estructura {

    public Ciutat(int propietari, Fitxa inici) {
        super(propietari, inici);
    }

    //Pre:---
    //Post:retorna cert si la possessio esta completa altrament false
    public boolean tancat(){

        int obert = 0;
        for(int i=0; i<getConjunt().size(); i++){

            if(getConjunt().get(i).es_fi_o_inici_de_ciutat()){
                obert++;
            }
            else if(getConjunt().get(i).bandes_de_ciutat()==3){
                obert += 3;
            }
            else{
                obert += 2;
            }

            if(i>0){
                obert -= 2;
            }
        }
        return obert==0;
    }



    //Pre:---
    //Post:retorna punts total de la ciutat
    public int punts(){

        int puntuacio = 0;

        for(int i=0; i<getConjunt().size(); i++){
            if(getConjunt().get(i).teEscut()){
                puntuacio += 2;
            }
        }

        puntuacio += getConjunt().size()*2;

        if(!tancat()){
            puntuacio /=2;
        }

        return puntuacio;
    }
}
