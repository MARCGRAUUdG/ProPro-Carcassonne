import java.util.NoSuchElementException;

public class Regio extends Excepcio{

    //Descripcio: Regio d'una fitxa

    private char zona;
    private String seguidor;

    //Pre:---
    //Post: zona = lletra
    Regio(char lletra){
        zona = lletra;
    }

    //Pre:---
    //Post: retorna la regio
    char lletra(){
        return zona;
    }

    //Pre:---
    //Post: retorna el nom del jugador
    String nom_jugador(){
        return seguidor;
    }

    //Pre:---
    //Post: posar un seguidor de jugador a la regio si no és X altrament llança excepcio
    void posar_seguidor(String jugador)throws Excepcio{
        if(zona != 'X'){
            seguidor = jugador;
        }
        else{
            throw new Excepcio("No és pot possar seguidor a l'encreuament");
        }
    }

}