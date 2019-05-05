import java.util.*;
import java.util.stream.Collectors;


public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];

    //alp.add(new Posicio(4,5,90))

    //Pre:Hi ha  almenys una fitxa al tauler en la posicio inicial (5,5)
    //Post:Retorna llista de les posicions on es pot ficar la fitxa f
    public ArrayList<Posicio> getPosDisponibles(Fitxa f) {
        ArrayList<Posicio> alp=new ArrayList<>();
        ArrayList<Posicio> posicionsVisitades=new ArrayList<>();
        alp=buscaColocacioFitxes(5,5,f,posicionsVisitades);

        return alp;
    }

    private ArrayList<Posicio> buscaColocacioFitxes(int x, int y, Fitxa f,ArrayList<Posicio> posicionsVisitades) {
        ArrayList<Posicio> p=new ArrayList<Posicio>();
        if(x+1>9);
        else if(getFitxa(x+1,y)==null){
            p.add(new Posicio(x+1,y,0));
        }else if(!posicionsVisitades.contains(new Posicio(x+1,y))){
            posicionsVisitades.add(new Posicio(x+1,y));
            p.addAll(buscaColocacioFitxes(x+1,y,f,posicionsVisitades));
        }

        if(x-1<0);
        else if(getFitxa(x-1,y)==null){
            p.add(new Posicio(x-1,y,0));
        }else if(!posicionsVisitades.contains(new Posicio(x-1,y))){
            posicionsVisitades.add(new Posicio(x-1,y));
            p.addAll(buscaColocacioFitxes(x-1,y,f,posicionsVisitades));
        }

        if(y+1>9);
        else if(getFitxa(x,y+1)==null){
            p.add(new Posicio(x,y+1,0));
        }else if(!posicionsVisitades.contains(new Posicio(x,y+1))){
            posicionsVisitades.add(new Posicio(x,y+1));
            p.addAll(buscaColocacioFitxes(x,y+1,f,posicionsVisitades));
        }

        if(y-1<0);
        else if(getFitxa(x,y-1)==null){
            p.add(new Posicio(x,y-1,0));
        }else if(!posicionsVisitades.contains(new Posicio(x,y-1))){
            posicionsVisitades.add(new Posicio(x,y-1));
            p.addAll(buscaColocacioFitxes(x,y-1,f,posicionsVisitades));
        }
        //posicionsVisitades.add(new Posicio(x,y));
        return p;
    }

    public boolean tencaRegions(Fitxa f) {
        return false;
    }

    public void actualitzarPunts(Fitxa f) {
    }

    //Pre:La posicio de f es correcte in la fitxa encaixa
    //Post:S'ha colocat la fitxa en el tauler
    public void posarFitxaTauler(Fitxa f) {
        Posicio p=f.getPosicio();
        _tauler[p.getPosicioX()][p.getPosicioY()]=f;
    }

    public Fitxa getFitxa(int x, int y){
        try {
            return _tauler[x][y];
        }catch(Exception e) {
            return null;
        }
    }
}
