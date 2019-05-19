import javafx.util.Pair;

import java.util.*;


public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];
    private static ArrayList<Possessio> _posCami= new ArrayList<>();
    private static ArrayList<Possessio> _posCamp= new ArrayList<>();
    private static ArrayList<Possessio> _posEsglesia= new ArrayList<>();
    private static ArrayList<Possessio> _posCiutat= new ArrayList<>();

    ///Pre:Hi ha  almenys una fitxa al tauler en la posicio inicial (5,5)
    ///Post:Retorna llista de les posicions on es pot ficar la fitxa f
    public ArrayList<Posicio> getPosDisponibles(Fitxa f) {
        ArrayList<Posicio> alp=new ArrayList<>();
        ArrayList<Posicio> posicionsVisitades=new ArrayList<>();
        posicionsVisitades.add(new Posicio(5,5));
        alp=buscaColocacioFitxes(5,5,f,posicionsVisitades);
        removeDouble(alp);
        return alp;
    }

    ///Pre:Funcio recursiva de getPosDisponibles(), 9<=x>=0 && 9<=y>=0
    ///Post:Retorna la llista de posicions on es pot colocar la fitxa
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

    ///Pre:9<=x>=0 && 9<=y>=0, f inicialitzada
    ///Post:Si encaixa la fitxa f amb la posicio x, y en qualsevol rotacio afageix les posicions (considerant cada rotacio) a la llista p
    private void afegeixPosicioSiEncaixaFitxa(Fitxa f, int x, int y, ArrayList<Posicio> p) {
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

    ///Pre:La posicio de f es correcte in la fitxa encaixa
    ///Post:S'ha colocat la fitxa en el tauler
    public void posarFitxaTauler(Fitxa f) {
        Posicio p=f.getPosicio();
        _tauler[p.getPosicioX()][p.getPosicioY()]=f;

        assignarPossessio(f);
    }

    ///Pre: f inicialitzada amb posicio
    ///Post:Assigna la fitxa f en alguna/es possessio/s de la llista de tipus de possessions si cal
    private void assignarPossessio(Fitxa f) {
        Posicio p=f.getPosicio();
        if(getFitxa(p.getPosicioX()-1,p.getPosicioY())!=null){
            afegirPossessio(getFitxa(p.getPosicioX()-1,p.getPosicioY()),f,f.regio_o(),'O');
        }
        if(getFitxa(p.getPosicioX()+1,p.getPosicioY())!=null){
            afegirPossessio(getFitxa(p.getPosicioX()+1,p.getPosicioY()),f,f.regio_e(),'E');
        }
        if(getFitxa(p.getPosicioX(),p.getPosicioY()-1)!=null){
            afegirPossessio(getFitxa(p.getPosicioX(),p.getPosicioY()-1),f,f.regio_n(),'N');
        }
        if(getFitxa(p.getPosicioX(),p.getPosicioY()+1)!=null){
            afegirPossessio(getFitxa(p.getPosicioX(),p.getPosicioY()+1),f,f.regio_s(),'S');
        }

        posaFitxaAPossessioSiNoEstaPosat(f, _posCami, 'C');
        posaFitxaAPossessioSiNoEstaPosat(f,_posCiutat, 'V');

        comprovaPossessionsTancades(_posCiutat);
        comprovaPossessionsTancades(_posCami);
    }

    ///Pre:--
    ///Post:Si de la llista p hi ha alguna possessio tancada assigna punts als jugadors, retorna els seguidors, i l'elimina de la llista
    private void comprovaPossessionsTancades(ArrayList<Possessio> p) {
        for(int i=p.size()-1;i>=0;i--) {
            //Gui.print("Regio "+p.get(i).tipus()+i+" "+p.get(i).toString()+" Esta tancat: "+p.get(i).tancat());
            if(p.get(i).tancat()) {
                List<Pair<Fitxa,List<Character>>> lp=p.get(i).getConjunt();
                for(int y=0;y<lp.size();y++){
                    int jugador=lp.get(y).getKey().jugadorTeLaSeguidor();
                    if(jugador!=-1)
                        Joc.AfegeixSeguidorAJugador(jugador);
                }
                List<Integer> JugadorGuanyador = p.get(i).propietari();
                if(JugadorGuanyador.size()>0) {
                    int puntsTotals = p.get(i).punts();
                    puntsTotals = puntsTotals / JugadorGuanyador.size();
                    for (int x = 0; x < JugadorGuanyador.size(); x++) {
                        Joc.AfegeixPuntuacioAJugador(JugadorGuanyador.get(x), puntsTotals);
                    }
                }else{
                    Gui.print("Ningu dominava la possessio completada");
                }
                List<Fitxa> fitxes=new ArrayList<>();
                for (int x=(lp.size()-1); x>=0; x--) {//Afegeix les fitxes on es te que eliminar el seguidor en la gui
                    if (lp.get(x).getKey().elSeguidorEstaEnElSeuTipusDeRegio(p.get(i).tipus(),lp.get(x).getValue()))
                        fitxes.add(lp.get(x).getKey());
                }
                Gui.treuSeguidorsDe(fitxes);
                Gui.print("Possessio tancada");
                p.remove(i);
            }
        }
    }

    ///Pre: lletra==('C' o 'V')
    ///Post:Posa la fitxa f a la possessio p del tipus lletra si no hi es a la llista p
    private void posaFitxaAPossessioSiNoEstaPosat(Fitxa f, ArrayList<Possessio> p, char lletra) {
        if (lletra == 'C' || lletra == 'V') {
            if (f.regio_c() == lletra) {
                List<Character> pos = new ArrayList<>();
                if(getPossessioDeFitxa(f,p,'C')==-1) pos.add('C');
                if (f.regio_n() == lletra) if(getPossessioDeFitxa(f,p,'N')==-1) pos.add('N');
                if (f.regio_e() == lletra) if(getPossessioDeFitxa(f,p,'E')==-1) pos.add('E');
                if (f.regio_s() == lletra) if(getPossessioDeFitxa(f,p,'S')==-1) pos.add('S');
                if (f.regio_o() == lletra) if(getPossessioDeFitxa(f,p,'O')==-1) pos.add('O');
                if(pos.size()>0){
                    if (lletra == 'C') p.add(new Cami(f, pos));
                    else if (lletra == 'V') p.add(new Ciutat(f, pos));
                }
            } else {
                if (f.regio_n() == lletra) {
                    if(getPossessioDeFitxa(f,p,'N')==-1) {
                        List<Character> pos = new ArrayList<>();
                        pos.add('N');
                        if (lletra == 'C') p.add(new Cami(f, pos));
                        else if (lletra == 'V') p.add(new Ciutat(f, pos));
                    }
                }
                if (f.regio_e() == lletra) {
                    if(getPossessioDeFitxa(f,p,'E')==-1) {
                        List<Character> pos = new ArrayList<>();
                        pos.add('E');
                        if (lletra == 'C') p.add(new Cami(f, pos));
                        else if (lletra == 'V') p.add(new Ciutat(f, pos));
                    }
                }
                if (f.regio_s() == lletra) {
                    if(getPossessioDeFitxa(f,p,'S')==-1) {
                        List<Character> pos = new ArrayList<>();
                        pos.add('S');
                        if (lletra == 'C') p.add(new Cami(f, pos));
                        else if (lletra == 'V') p.add(new Ciutat(f, pos));
                    }
                }
                if (f.regio_o() == lletra) {
                    if(getPossessioDeFitxa(f,p,'O')==-1) {
                        List<Character> pos = new ArrayList<>();
                        pos.add('O');
                        if (lletra == 'C') p.add(new Cami(f, pos));
                        else if (lletra == 'V') p.add(new Ciutat(f, pos));
                    }
                }
            }
        } else {
            //TODO Falta implementar les altres possessions
        }
    }

    ///Pre:fAnterior, fNova inicializats amb posicio, reg==('C' o 'F' o 'V' o 'M' o 'E'), loc==('C' o 'N' o 'E' o 'S' o 'O')
    ///Post:Afegeix la fitxa fNova a la mateixa possessio que fAnterior del tipus reg en la part de regio loc
    private void afegirPossessio(Fitxa fAnterior, Fitxa fNova, char reg, char loc) {
        char lletraInvertida=loc;
        if(loc=='N')lletraInvertida='S';
        else if(loc=='E')lletraInvertida='O';
        else if(loc=='S')lletraInvertida='N';
        else if(loc=='O')lletraInvertida='E';
        if (reg == 'C') {
            //Posa la fitxa en la possessio de la fitxa del costat
            int pin=getPossessioDeFitxa(fAnterior,_posCami,lletraInvertida);
            List<Character> lPos=new ArrayList<>();
            lPos = getPosicionsDePossessio(fNova,reg,loc);
            if(pin!=-1)
                _posCami.get(pin).afegir_fitxa(fNova,lPos);
        }else if(reg=='F'){

        }else if(reg=='V'){
            int pin=getPossessioDeFitxa(fAnterior,_posCiutat,lletraInvertida);
            List<Character> lPos=new ArrayList<>();
            lPos = getPosicionsDePossessio(fNova,reg,loc);
            if(pin!=-1)
                _posCiutat.get(pin).afegir_fitxa(fNova,lPos);

        }else if(reg=='M'){

        }else{//TODO Falta E

        }
    }

    ///Pre:f inicializat amb posicio, reg==('C' o 'F' o 'V' o 'M' o 'E'), loc==('C' o 'N' o 'E' o 'S' o 'O')
    ///Post:Retorna una llista de posicions on la fitxa f es ve afectada per el tipus de possessio reg en la localitat loc
    private List<Character> getPosicionsDePossessio(Fitxa f, char reg, char loc) {
        List<Character> lPos=new ArrayList<>();
        if(f.regio_c()=='X')lPos.add(loc);
        else{
            if(f.regio_c()==reg)lPos.add('C');
            if(f.regio_n()==reg)lPos.add('N');
            if(f.regio_e()==reg)lPos.add('E');
            if(f.regio_s()==reg)lPos.add('S');
            if(f.regio_o()==reg)lPos.add('O');
        }
        return lPos;
    }

    ///Pre:f inicialitzat, loc==('C' o 'N' o 'E' o 'S' o 'O')
    ///Post:Retorna la posicio de la llista possessio de on es troba la fitxa f, -1 si no hi es en la llista
    private int getPossessioDeFitxa(Fitxa f, ArrayList<Possessio> possessio, char loc) {
        boolean trobat=false;
        int i=0;
        while(!trobat && i<possessio.size()){
            if(possessio.get(i).pertanyLaFitxa(f,loc))
                trobat=true;
            else
                i++;
        }
        if(!trobat)
            return -1;
        else
            return i;
    }

    ///Pre:f inicialitzat
    ///Post:Retorna cert si la fitxa f esta en alguna llista de possessio fals altrament
    private boolean estaEnLaLlista(Fitxa f, ArrayList<Possessio> possessio, char loc) {
        boolean trobat=false;//TODO Treure aquesta funcio no s'utilitza i esta desactualitzada
        int i=0;
        while(i<possessio.size()&& !trobat){
            if(possessio.get(i).pertanyLaFitxa(f,loc))
                trobat=true;
            else
                i++;
        }
        return trobat;
    }

    ///Pre:--
    ///Post:Retorna la fitxa del tauler de posicio x,y, null si no hi ha cap fitxa o esta fora de rang
    public Fitxa getFitxa(int x, int y){
        try {
            return _tauler[x][y];
        }catch(Exception e) {
            return null;
        }
    }

    ///Pre:--
    ///Post:Elimina les posicions repetides de la llista alp
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

    ///Pre:f inicialitzada amb posicio
    ///Post:retorna una llista de Caracters en les regions on es pot ficar el seguidor
    public ArrayList<Character> onEsPotFicarSeguidor(Fitxa f) {
        Posicio p=f.getPosicio();
        ArrayList<Character> loc=new ArrayList<>();
        Posicio pos=f.getPosicio();
        Fitxa fVoltant;
        loc.add('C');loc.add('N');loc.add('E');loc.add('S');loc.add('O');

        fVoltant=getFitxa(pos.getPosicioX()-1,pos.getPosicioY());
        if(fVoltant!=null){//FitxaEsquerra
            ArrayList<Possessio> possessio=getLlistaTipusDePossessio(f.regio_o());
            int i=getPossessioDeFitxa(fVoltant, possessio, 'E');
            eliminaPosicionsIncompatibles(possessio,i,'O', f, loc);
        }
        fVoltant=getFitxa(pos.getPosicioX()+1,pos.getPosicioY());
        if(fVoltant!=null){//FitxaDreta
            ArrayList<Possessio> possessio=getLlistaTipusDePossessio(f.regio_e());
            int i=getPossessioDeFitxa(fVoltant, possessio, 'O');
            eliminaPosicionsIncompatibles(possessio,i,'E', f, loc);
        }
        fVoltant=getFitxa(pos.getPosicioX(),pos.getPosicioY()+1);
        if(fVoltant!=null){//FitxaAbaix
            ArrayList<Possessio> possessio=getLlistaTipusDePossessio(f.regio_s());
            int i=getPossessioDeFitxa(fVoltant, possessio, 'N');
            eliminaPosicionsIncompatibles(possessio,i,'S', f, loc);
        }
        fVoltant=getFitxa(pos.getPosicioX(),pos.getPosicioY()-1);
        if(fVoltant!=null){//FitxaAdalt
            ArrayList<Possessio> possessio=getLlistaTipusDePossessio(f.regio_n());
            int i=getPossessioDeFitxa(fVoltant, possessio, 'S');
            eliminaPosicionsIncompatibles(possessio,i,'N', f, loc);
        }
        if(f.regio_c()=='X')if(loc.contains('C'))loc.remove(loc.indexOf('C'));
        return loc;
    }

    ///Pre:f inicialitzada
    ///Post:Elimina de loc les possibilitats de ficar seguidor en les regions regions de la fitxa f on la possessio.get(i) estan ja ocupades per un jugador
    private void eliminaPosicionsIncompatibles(ArrayList<Possessio> possessio, int i, char e, Fitxa f, ArrayList<Character> loc) {
        if(i!=-1) {
            Possessio pAct = getTipusDePossessio(possessio.get(i).tipus(), i);
            if (pAct.propietari().size() > 0) {
                if (loc.contains(e)) loc.remove(loc.indexOf(e));
                if (f.regio_c() == pAct.tipus()) {
                    if (loc.contains('C')) loc.remove(loc.indexOf('C'));
                    if (f.regio_n() == pAct.tipus() && (loc.contains('N'))) loc.remove(loc.indexOf('N'));
                    if (f.regio_o() == pAct.tipus() && (loc.contains('O'))) loc.remove(loc.indexOf('O'));
                    if (f.regio_s() == pAct.tipus() && (loc.contains('S'))) loc.remove(loc.indexOf('S'));
                    if (f.regio_e() == pAct.tipus() && (loc.contains('E'))) loc.remove(loc.indexOf('E'));
                }
            }
        }
    }

    private ArrayList<Possessio> getLlistaTipusDePossessio(char reg) {
        if(reg=='V')return _posCiutat;
        else if(reg=='C')return _posCami;
        else if(reg=='E')return _posEsglesia;
        else return _posCamp;
    }

    private Possessio getTipusDePossessio(char reg,int i) {
        if(reg=='V')return _posCiutat.get(i);
        else if(reg=='C')return _posCami.get(i);
        else if(reg=='E')return _posEsglesia.get(i);
        else return _posCamp.get(i);
    }
}
