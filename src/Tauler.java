import java.util.*;


public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];
    private static ArrayList<Possessio> _posCami= new ArrayList<>();
    private static ArrayList<Camp> _posCamp= new ArrayList<>();
    private static ArrayList<Esglesia> _posEsglesia= new ArrayList<>();
    private static ArrayList<Ciutat> _posCiutat= new ArrayList<>();

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
        int rotacio[]={0,90,180,270};
        for(int i=0;i<4;i++)
        {
            boolean encaixa=true;
            f.rotar(rotacio[i]);
            if(x+1<10){
                if(!f.fitxaActualEncaixaAmb(_tauler[x+1][y],'O'))
                    encaixa=false;
            }
            if(y+1<10){
                if(!f.fitxaActualEncaixaAmb(_tauler[x][y+1],'N'))
                    encaixa=false;
            }
            if(x-1>=0){
                if(!f.fitxaActualEncaixaAmb(_tauler[x-1][y],'E'))
                    encaixa=false;
            }
            if(y-1>=0){
                if(!f.fitxaActualEncaixaAmb(_tauler[x][y-1],'S'))
                    encaixa=false;
            }

            if(encaixa)
                p.add(new Posicio(x, y, rotacio[i]));
        }
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

        assignarPossessio(f);
    }

    private void assignarPossessio(Fitxa f) {
        Posicio p=f.getPosicio();
        boolean esta=false;
        if(getFitxa(p.getPosicioX()-1,p.getPosicioY())!=null){
            afegirPossessio(getFitxa(p.getPosicioX()-1,p.getPosicioY()),f,f.regio_o());
        }
        if(getFitxa(p.getPosicioX()+1,p.getPosicioY())!=null){
            afegirPossessio(getFitxa(p.getPosicioX()+1,p.getPosicioY()),f,f.regio_e());
        }
        if(getFitxa(p.getPosicioX(),p.getPosicioY()-1)!=null){
            afegirPossessio(getFitxa(p.getPosicioX(),p.getPosicioY()-1),f,f.regio_n());
        }
        if(getFitxa(p.getPosicioX(),p.getPosicioY()+1)!=null){
            afegirPossessio(getFitxa(p.getPosicioX(),p.getPosicioY()+1),f,f.regio_s());
        }

        if(!estaEnLaLlista(f,_posCami) && conteCami(f)){
            _posCami.add(new Cami(f));
        }

        for(int i=0;i<_posCami.size();i++) {
            Gui.print(_posCami.get(i).toString());
        }
    }

    private boolean conteCami(Fitxa f) {
        return f.regio_n()=='C' || f.regio_s()=='C' || f.regio_e()=='C' || f.regio_o()=='C';
    }

    private void afegirPossessio(Fitxa fAnterior, Fitxa fNova, char reg) {
        if (reg == 'C') {
            //Posa la fitxa en la possessio de la fitxa del costat
            int pin=getPossessioDeFitxa(fAnterior,_posCami);
            _posCami.get(pin).afegir_fitxa(fNova);
        }else if(reg=='F'){

        }else if(reg=='V'){

        }else if(reg=='M'){

        }else{//TODO Falta E

        }
    }

    private int getPossessioDeFitxa(Fitxa f, ArrayList<Possessio> possessio) {
        boolean trobat=false;
        int i=0;
        while(!trobat && i<possessio.size()){
            if(possessio.get(i).pertanyLaFitxa(f))
                trobat=true;
            else
                i++;
        }
        if(!trobat)
            return -1;
        else
            return i;
    }

    private boolean estaEnLaLlista(Fitxa f, ArrayList<Possessio> possessio) {
        boolean trobat=false;
        int i=0;
        while(i<possessio.size()&& !trobat){
            if(possessio.get(i).pertanyLaFitxa(f))
                trobat=true;
            else
                i++;
        }
        return trobat;
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
