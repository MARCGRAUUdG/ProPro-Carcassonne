
public class Regio extends Excepcio{

    ///@class Regio

    ///@brief Descripcio: Regio d'una fitxa

    private char zona;/// La regio de la fitxa, pot ser C, N, E ,S o O
    private int seguidor=-1;///Número del propietari que té seguidor a aquesta regio, -1 no té cap seguidor

    ///@pre ---
    ///@post  zona = lletra
    Regio(char lletra){
        zona = lletra;
    }

    ///@pre ---
    ///@post  retorna la regio
    char lletra(){
        return zona;
    }

    ///@pre ---
    ///@post  retorna el nom del jugador
    Integer nom_jugador(){
        return seguidor;
    }

    ///@pre ---
    ///@post  posar un seguidor de jugador a la regio si no és X altrament llança excepcio
    void posar_seguidor(int jugador)throws Excepcio{
        if(zona != 'X'){
            seguidor = jugador;
        }
        else{
            throw new Excepcio("No és pot possar seguidor a l'encreuament");
        }
    }

    @Override
    public String toString() {
        return "Regio{" +
                "zona=" + zona +
                '}';
    }
}