import java.util.NoSuchElementException;

public class Regio {

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
    //Post: retorna el nom del jugador si existeix altrament llança excepcio de no trobat
    String nom_jugador() throws Exception{
        if(seguidor != null) {
            return seguidor;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    //Pre:---
    //Post: posar un seguidor de jugador a la regio
    void posar_seguidor(String jugador){
        seguidor = jugador;
    }
}
