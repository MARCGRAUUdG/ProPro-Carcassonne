import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Fitxa {

    //Descripcio: Fitxa fromat per regions C, N, E, S i O;

    private ArrayList<Regio> regions;

    //Pre: lletres mida = 5
    //Post: guarda a regions el format de la fitxa
    Fitxa(String lletres){
        regions = new ArrayList<Regio>(5);
        for(int i=0; i<lletres.length(); i++){
            char lletra = lletres.charAt(i);
            Regio nou = new Regio(lletra);
            regions.add(nou);
        }
    }

    //Pre: regio = C, N, E, S o O
    //Post: afegeix el jugador a la regio de la fitxa
    void assignar_seguidor(char regio, String jugador){
        if(regio=='C'){
            regions.get(0).posar_seguidor(jugador);
        }
        else if(regio=='N') {
            regions.get(1).posar_seguidor(jugador);
        }
        else if(regio=='E') {
            regions.get(2).posar_seguidor(jugador);
        }
        else if(regio=='S') {
            regions.get(3).posar_seguidor(jugador);
        }
        else {
            regions.get(4).posar_seguidor(jugador);
        }
    }

    //Pre:---
    //Post: retorna cert si es buida altrament fals
    boolean buit(){
        return regions.isEmpty();
    }

    //Pre:!buit
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor, el seu propietari
    // altrament llança excepcio de null
    void mostrar_regio_c()throws Exception{
        if(regions != null) {
            System.out.println("Regio tipus: " + regions.get(0).lletra());
            try {
                System.out.println("Propietari del seguidor:" + regions.get(0).nom_jugador());
            } catch (NoSuchElementException e) {
                System.out.println("No hi ha cap seguidor");
            }
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post:retorna el format de la regio del centre si existeix altrament llança excepcio de null
    char regio_c() throws Exception{
        if(regions != null) {
            return regions.get(0).lletra();
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post: si existeix la regio del nort mostra el format de la regio i si te seguidor, el seu propietari
    // altrament llança excepcio de null
    void mostrar_regio_n()throws Exception{
        if(regions != null) {
            System.out.println("Regio tipus: " + regions.get(1).lletra());
            try {
                System.out.println("Propietari del seguidor:" + regions.get(1).nom_jugador());
            } catch (NoSuchElementException e) {
                System.out.println("No hi ha cap seguidor");
            }
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post:retorna el format dela regio del nort si existeix altrament llança excepcio de null
    char regio_n() throws Exception{
        if(regions.get(1) != null){
            return regions.get(1).lletra();
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor, el seu propietari
    // altrament llança excepcio de null
    void mostrar_regio_e()throws Exception{
        if(regions != null) {
            System.out.println("Regio tipus: " + regions.get(2).lletra());
            try {
                System.out.println("Propietari del seguidor:" + regions.get(2).nom_jugador());
            } catch (NoSuchElementException e) {
                System.out.println("No hi ha cap seguidor");
            }
        }
        else{
            throw new NullPointerException();
        }
    }
    //Pre:!buit
    //Post:retorna el format de la regio de l'est si existeix altrament llança excepcio de null
    char regio_e() throws Exception{
        if(regions.get(2) != null){
            return regions.get(2).lletra();
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor, el seu propietari
    // altrament llança excepcio de null
    void mostrar_regio_s()throws Exception{
        if(regions != null) {
            System.out.println("Regio tipus: " + regions.get(3).lletra());
            try {
                System.out.println("Propietari del seguidor:" + regions.get(3).nom_jugador());
            } catch (NoSuchElementException e) {
                System.out.println("No hi ha cap seguidor");
            }
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post:retorna el format de la regio del sud si existeix altrament llança excepcio de null
    char regio_s() throws Exception{
        if(regions.get(3)!=null) {
            return regions.get(3).lletra();
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor, el seu propietari
    // altrament llança excepcio de null
    void mostrar_regio_o()throws Exception{
        if(regions != null) {
            System.out.println("Regio tipus: " + regions.get(4).lletra());
            try {
                System.out.println("Propietari del seguidor:" + regions.get(4).nom_jugador());
            } catch (NoSuchElementException e) {
                System.out.println("No hi ha cap seguidor");
            }
        }
        else{
            throw new NullPointerException();
        }
    }

    //Pre:!buit
    //Post:retorna el format de la regio de l'oest si existeix altrament llança excepcio de null
    char regio_o()throws Exception{
        if(regions.get(4) !=null) {
            return regions.get(4).lletra();
        }
        else{
            throw new NullPointerException();
        }
    }
}
