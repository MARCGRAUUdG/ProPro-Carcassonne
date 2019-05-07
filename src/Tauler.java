import java.util.*;


public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];

    //Pre:Hi ha  almenys una fitxa al tauler en la posicio inicial (5,5)
    //Post:Retorna llista de les posicions on es pot ficar la fitxa f
    public ArrayList<Posicio> getPosDisponibles(Fitxa f) {
        ArrayList<Posicio> alp=new ArrayList<>();
        ArrayList<Posicio> posicionsVisitades=new ArrayList<>();
        posicionsVisitades.add(new Posicio(5,5));
        alp=buscaColocacioFitxes(5,5,f,posicionsVisitades);
        removeDouble(alp);
        return alp;
    }

    private ArrayList<Posicio> buscaColocacioFitxes(int x, int y, Fitxa f,ArrayList<Posicio> posicionsVisitades) {
        ArrayList<Posicio> p=new ArrayList<Posicio>();
        if(x+1>9);
        else if(getFitxa(x+1,y)==null)
            afegeixPosicioSiEncaixaFitxa(f, x + 1, y,p);
        else if(!posicionsVisitades.contains(new Posicio(x+1,y))){
            posicionsVisitades.add(new Posicio(x+1,y));
            p.addAll(buscaColocacioFitxes(x+1,y,f,posicionsVisitades));
        }

        if(x-1<0);
        else if(getFitxa(x-1,y)==null)
            afegeixPosicioSiEncaixaFitxa(f, x - 1, y,p);
        else if(!posicionsVisitades.contains(new Posicio(x-1,y))){
            posicionsVisitades.add(new Posicio(x-1,y));
            p.addAll(buscaColocacioFitxes(x-1,y,f,posicionsVisitades));
        }

        if(y+1>9);
        else if(getFitxa(x,y+1)==null)
            afegeixPosicioSiEncaixaFitxa(f, x, y + 1,p);
        else if(!posicionsVisitades.contains(new Posicio(x,y+1))){
            posicionsVisitades.add(new Posicio(x,y+1));
            p.addAll(buscaColocacioFitxes(x,y+1,f,posicionsVisitades));
        }

        if(y-1<0);
        else if(getFitxa(x,y-1)==null)
            afegeixPosicioSiEncaixaFitxa(f, x, y - 1,p);
        else if(!posicionsVisitades.contains(new Posicio(x,y-1))){
            posicionsVisitades.add(new Posicio(x,y-1));
            p.addAll(buscaColocacioFitxes(x,y-1,f,posicionsVisitades));
        }
        return p;
    }

    private void afegeixPosicioSiEncaixaFitxa(Fitxa f, int x, int y,ArrayList<Posicio> p) {
        int rotacio=0;
        boolean encaixa=true;
        if(x+1<10){
            if(!f.fitxaActualEncaixaAmb(_tauler[x+1][y],'O'))
                encaixa=false;
            if(y+1<10){
                if(!f.fitxaActualEncaixaAmb(_tauler[x][y+1],'N'))
                    encaixa=false;
                if(x-1>=0){
                    if(!f.fitxaActualEncaixaAmb(_tauler[x-1][y],'E'))
                        encaixa=false;
                    if(y-1>=0){
                        if(!f.fitxaActualEncaixaAmb(_tauler[x][y-1],'S'))
                            encaixa=false;
                    }
                }
            }
        }

        if(encaixa)
            p.add(new Posicio(x, y, rotacio));
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

    public void removeDouble(ArrayList<Posicio> alp) {
        for (int i = 0; i < alp.size(); i++) {
            for (int j = i + 1; j < alp.size(); j++) {
                if (alp.get(i).equals(alp.get(j))) {
                    alp.remove(j);
                    j = j - 1;
                }
            }
        }
    }
}
