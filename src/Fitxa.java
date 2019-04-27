import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Fitxa {

    //Descripcio: Fitxa fromat per regions C, N, E, S i O;

    private ArrayList<Regio> regions;
    private int rotacio;

    //Pre: lletres mida = 5
    //Post: guarda a regions el format de la fitxa
    public Fitxa(String lletres)throws Excepcio{
        if(lletres.length()==5) {
            regions = new ArrayList<Regio>(5);
            for (int i = 0; i < lletres.length(); i++) {
                char lletra = lletres.charAt(i);
                Regio nou = new Regio(lletra);
                regions.add(nou);
            }
            rotacio = 0;
        }
        else {
            throw new Excepcio("La mida és incorrecta");
        }
    }

    //Pre: regio = C, N, E, S o O
    //Post: afegeix el jugador a la regio de la fitxa
    public void assignar_seguidor(char regio, String jugador)throws Excepcio{
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
        else if(regio=='O'){
            regions.get(4).posar_seguidor(jugador);
        }
        else{
            throw new Excepcio("La regio és incorrecta");
        }
    }

    //Pre:---
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor
    public void mostrar_regio_c(){

        System.out.println("Regio tipus: " + regions.get(0).lletra());
        if(regions.get(0).nom_jugador()==null){
            System.out.println("No hi ha cap seguidor");
        }
        else {
            System.out.println("Propietari del seguidor:" + regions.get(0).nom_jugador());
        }
    }

    //Pre:---
    //Post:retorna el format de la regio del centre
    public char regio_c(){

        return regions.get(0).lletra();

    }

    //Pre:---
    //Post: si existeix la regio del nort mostra el format de la regio i si te seguidor
    public void mostrar_regio_n(){
        System.out.println("Regio tipus: " + regions.get(1).lletra());
        if(regions.get(1).nom_jugador()==null){
            System.out.println("No hi ha cap seguidor");
        }
        else {
            System.out.println("Propietari del seguidor:" + regions.get(1).nom_jugador());
        }
    }

    //Pre:---
    //Post:retorna el format dela regio del nort
    public char regio_n(){

        return regions.get(1).lletra();
    }

    //Pre:---
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor
    public void mostrar_regio_e(){
        System.out.println("Regio tipus: " + regions.get(2).lletra());

        if(regions.get(2).nom_jugador()==null){
            System.out.println("No hi ha cap seguidor");
        }
        else {
            System.out.println("Propietari del seguidor:" + regions.get(2).nom_jugador());
        }
    }
    //Pre:----
    //Post:retorna el format de la regio de l'est
    public char regio_e(){

        return regions.get(2).lletra();
    }

    //Pre:---
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor
    public void mostrar_regio_s(){
        System.out.println("Regio tipus: " + regions.get(3).lletra());
        if(regions.get(3).nom_jugador()==null){
            System.out.println("No hi ha cap seguidor");
        }
        else {
            System.out.println("Propietari del seguidor:" + regions.get(3).nom_jugador());
        }
    }

    //Pre:---
    //Post:retorna el format de la regio del sud
    public char regio_s() {
        return regions.get(3).lletra();
    }

    //Pre:---
    //Post: si existeix la regio central mostra el format de la regio i si te seguidor
    public void mostrar_regio_o(){
        System.out.println("Regio tipus: " + regions.get(4).lletra());
        if(regions.get(4).nom_jugador()==null){
            System.out.println("No hi ha cap seguidor");
        }
        else {
            System.out.println("Propietari del seguidor:" + regions.get(4).nom_jugador());
        }
    }

    //Pre:---
    //Post:retorna el format de la regio de l'oest
    public char regio_o(){

        return regions.get(4).lletra();
    }

    //Pre: rotar = 0 o 90 o 180 o 270
    //Post: rotacio = rotar
    public void rator_fitxa(int rotar){
        rotacio = rotar;
    }

    //Pre:---
    //Post: retorna rotacio
    public int getRotacio() {
        return rotacio;
    }
}
