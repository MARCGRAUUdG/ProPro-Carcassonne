import java.util.*;

public class Tauler
{
    //Pre:Hi ha  almenys una fitxa al tauler en la posicio inicial (5,5)
    //Post:Retorna llista de les posicions on es pot ficar la fitxa f
    public ArrayList<Posicio> getPosDisponibles(Fitxa f) {
        ArrayList<Posicio> alp=new ArrayList<Posicio>();//TODO treure
        alp.add(new Posicio(5,4,0));//TODO treure es nomes per mostrar ja haurien de estar inicialitzades
        alp.add(new Posicio(5,6,90));//TODO treure
        alp.add(new Posicio(6,5,0));//TODO treure
        alp.add(new Posicio(4,5,90));//TODO treure

        return alp;
    }

    public boolean tencaRegions(Fitxa f) {
        return false;
    }

    public void actualitzarPunts(Fitxa f) {
    }

    //Pre:La posicio de f es correcte in la fitxa encaixa
    //Post:S'ha colocat la fitxa en el tauler
    public void posarFitxaTauler(Fitxa f) {
    }
}
