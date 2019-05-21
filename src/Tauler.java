import javafx.util.Pair;
import java.util.*;

///@class Tauler

///@brief Descripcio de la clase...

public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];///<Descripcio...
    private static ArrayList<Possessio> _posCami= new ArrayList<>();///<Descripcio...
    private static ArrayList<Possessio> _posCamp= new ArrayList<>();///<Descripcio...
    private static ArrayList<Possessio> _posEsglesia= new ArrayList<>();///<Descripcio...
    private static ArrayList<Possessio> _posCiutat= new ArrayList<>();///<Descripcio...

    ///@pre Hi ha  almenys una fitxa al tauler en la posicio inicial (5,5)
    ///@post Retorna llista de les posicions on es pot ficar la fitxa f
    public ArrayList<Posicio> getPosDisponibles(Fitxa f) {
        ArrayList<Posicio> alp=new ArrayList<>();
        ArrayList<Posicio> posicionsVisitades=new ArrayList<>();
        posicionsVisitades.add(new Posicio(5,5));
        alp=buscaColocacioFitxes(5,5,f,posicionsVisitades);
        removeDouble(alp);
        return alp;
    }

    ///@pre Funcio recursiva de getPosDisponibles(), 9<=x>=0 && 9<=y>=0
    ///@post Retorna la llista de posicions on es pot colocar la fitxa
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

    ///@pre 9<=x>=0 && 9<=y>=0, f inicialitzada
    ///@post Si encaixa la fitxa f amb la posicio x, y en qualsevol rotacio afageix les posicions (considerant cada rotacio) a la llista p
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

    ///@pre La posicio de f es correcte in la fitxa encaixa
    ///@post S'ha colocat la fitxa en el tauler
    public void posarFitxaTauler(Fitxa f) {
        Posicio p=f.getPosicio();
        _tauler[p.getPosicioX()][p.getPosicioY()]=f;
        assignarPossessio(f);
    }
    public void treureFitxaTauler(Fitxa f) {
        Posicio p=f.getPosicio();
        _tauler[p.getPosicioX()][p.getPosicioY()]=null;

    }

    ///@pre  f inicialitzada amb posicio
    ///@post Assigna la fitxa f en alguna/es possessio/s de la llista de tipus de possessions si cal
    private void assignarPossessio(Fitxa f) {
        Posicio p=f.getPosicio();
        ArrayList<Boolean> esPotPosar=new ArrayList<>();
        ArrayList<Integer> nLoc;
        if(getFitxa(p.getPosicioX()-1,p.getPosicioY())!=null)esPotPosar.add(true);
        else esPotPosar.add(false);
        if(getFitxa(p.getPosicioX()+1,p.getPosicioY())!=null)esPotPosar.add(true);
        else esPotPosar.add(false);
        if(getFitxa(p.getPosicioX(),p.getPosicioY()-1)!=null)esPotPosar.add(true);
        else esPotPosar.add(false);
        if(getFitxa(p.getPosicioX(),p.getPosicioY()+1)!=null)esPotPosar.add(true);
        else esPotPosar.add(false);

        if(esPotPosar.get(0))afegirPossessio(getFitxa(p.getPosicioX() - 1, p.getPosicioY()), f, f.regio_o(), 'O');
        if(esPotPosar.get(1))afegirPossessio(getFitxa(p.getPosicioX() + 1, p.getPosicioY()), f, f.regio_e(), 'E');
        if(esPotPosar.get(2))afegirPossessio(getFitxa(p.getPosicioX(), p.getPosicioY() - 1), f, f.regio_n(), 'N');
        if(esPotPosar.get(3))afegirPossessio(getFitxa(p.getPosicioX(), p.getPosicioY() +1 ), f, f.regio_s(), 'S');

        posaFitxaAPossessioSiNoEstaPosat(f, _posCami, 'C');
        posaFitxaAPossessioSiNoEstaPosat(f,_posCiutat, 'V');

        unirPossessionsSiCal(f,_posCami);
        unirPossessionsSiCal(f,_posCiutat);

        comprovaPossessionsTancades(_posCiutat);
        comprovaPossessionsTancades(_posCami);
    }

    ///@pre f inicialitzada
    ///@post  Uneix les possessions si f pertany en mes d'una possessio del mateix tipus de la mateixa regio unida
    private void unirPossessionsSiCal(Fitxa f, ArrayList<Possessio> p) {
        if(p.size()>0) {
            ArrayList<Integer> nLoc = onEstaLaFitxaEnPossessions(f, p);
            if(nLoc.size()==2){
                p.get((int) nLoc.get(1)).eliminar_fitxa(f,'C');
                p.get((int) nLoc.get(0)).unir_possessions(p.get(nLoc.get(1)));
                p.remove((int) nLoc.get(1));
            }else if(nLoc.size()==3){
                p.get((int) nLoc.get(1)).eliminar_fitxa(f,'C');
                p.get((int) nLoc.get(2)).eliminar_fitxa(f,'C');
                p.get((int) nLoc.get(0)).unir_possessions(p.get(nLoc.get(1)));
                p.get((int) nLoc.get(0)).unir_possessions(p.get(nLoc.get(2)));
                p.remove((int) nLoc.get(1));p.remove((int) nLoc.get(2));
            }else if(nLoc.size()==4){
                p.get((int) nLoc.get(1)).eliminar_fitxa(f,'C');
                p.get((int) nLoc.get(2)).eliminar_fitxa(f,'C');
                p.get((int) nLoc.get(3)).eliminar_fitxa(f,'C');
                p.get((int) nLoc.get(0)).unir_possessions(p.get(nLoc.get(1)));
                p.get((int) nLoc.get(0)).unir_possessions(p.get(nLoc.get(2)));
                p.get((int) nLoc.get(0)).unir_possessions(p.get(nLoc.get(3)));
                p.remove((int) nLoc.get(1));p.remove((int) nLoc.get(2));p.remove((int) nLoc.get(3));
            }
        }
    }

    ///@pre f inicialitzada
    ///@post Retorna una llista de posicions de on pertany la fitxa f de la llista de possessions p
    private ArrayList<Integer> onEstaLaFitxaEnPossessions(Fitxa f, ArrayList<Possessio> p){
        ArrayList<Integer> nLoc = new ArrayList<>();
        if(p.size()>0) {
            if (f.regio_c() == p.get(0).tipus()) {
                for (int i = 0; i < p.size(); i++) {
                    if (p.get(i).pertanyLaFitxa(f, 'C'))
                        nLoc.add(i);
                }
            }
        }
        return nLoc;
    }

    ///@pre --
    ///@post Si de la llista p hi ha alguna possessio tancada assigna punts als jugadors, retorna els seguidors, i l'elimina de la llista
    private void comprovaPossessionsTancades(ArrayList<Possessio> p) {
        for(int i=p.size()-1;i>=0;i--) {
            if(p.get(i).tancat()) {
                List<Pair<Fitxa,List<Character>>> lp=p.get(i).getConjunt();
                List<Integer> JugadorGuanyador = p.get(i).propietari();
                if(JugadorGuanyador.size()>0) {
                    int puntsTotals = p.get(i).punts();
                    puntsTotals = puntsTotals / JugadorGuanyador.size();
                    for (int x = 0; x < JugadorGuanyador.size(); x++) {
                        Joc.AfegeixPuntuacioAJugador(JugadorGuanyador.get(x), puntsTotals,p.get(i).tipus());
                    }
                }else{
                    Gui.print("Ningu dominava la possessio completada");
                }
                List<Fitxa> fitxes=new ArrayList<>();
                for (int x=(lp.size()-1); x>=0; x--) {//Afegeix les fitxes on es te que eliminar el seguidor en la gui
                    if (lp.get(x).getKey().elSeguidorEstaEnElSeuTipusDeRegio(p.get(i).tipus(),lp.get(x).getValue()))
                        fitxes.add(lp.get(x).getKey());
                }
                for(int y=0;y<fitxes.size();y++){//Retorna els seguifors amb possessio completada al jugador
                    int jugador=fitxes.get(y).jugadorTeLaSeguidor();
                    if(jugador!=-1)
                        Joc.AfegeixSeguidorAJugador(jugador);
                }
                Gui.treuSeguidorsDe(fitxes);
                Gui.print("Possessio tancada");
                p.remove(i);
            }
        }
    }

    ///@pre  lletra==('C' o 'V')
    ///@post Posa la fitxa f a la possessio p del tipus lletra si no hi es a la llista p
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

    ///@pre fAnterior, fNova inicializats amb posicio, reg==('C' o 'F' o 'V' o 'M' o 'E'), loc==('C' o 'N' o 'E' o 'S' o 'O')
    ///@post Afegeix la fitxa fNova a la mateixa possessio que fAnterior del tipus reg en la part de regio loc
    private void afegirPossessio(Fitxa fAnterior, Fitxa fNova, char reg, char loc) {
        char lletraInvertida=loc;
        if(loc=='N')lletraInvertida='S';
        else if(loc=='E')lletraInvertida='O';
        else if(loc=='S')lletraInvertida='N';
        else if(loc=='O')lletraInvertida='E';
        if (reg == 'C') {
            //Posa la fitxa en la possessio de la fitxa del costat
            int pin=getPossessioDeFitxa(fAnterior,_posCami,lletraInvertida);
            List<Character> lPos;
            lPos = getPosicionsDePossessio(fNova,reg,loc);
            if(pin!=-1 && !_posCami.get(pin).pertanyLaFitxa(fNova,loc))
                _posCami.get(pin).afegir_fitxa(fNova, lPos);
        }else if(reg=='F'){

        }else if(reg=='V'){
            int pin=getPossessioDeFitxa(fAnterior,_posCiutat,lletraInvertida);
            List<Character> lPos;
            lPos = getPosicionsDePossessio(fNova,reg,loc);
            if(pin!=-1 && !_posCiutat.get(pin).pertanyLaFitxa(fNova,loc))
                _posCiutat.get(pin).afegir_fitxa(fNova, lPos);

        }else if(reg=='M'){

        }else{//TODO Falta E

        }
    }

    ///@pre f inicializat amb posicio, reg==('C' o 'F' o 'V' o 'M' o 'E'), loc==('C' o 'N' o 'E' o 'S' o 'O')
    ///@post Retorna una llista de posicions on la fitxa f es ve afectada per el tipus de possessio reg en la localitat loc
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

    ///@pre f inicialitzat, loc==('C' o 'N' o 'E' o 'S' o 'O')
    ///@post Retorna la posicio de la llista possessio de on es troba la fitxa f, -1 si no hi es en la llista
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

    ///@pre --
    ///@post Retorna la fitxa del tauler de posicio x,y, null si no hi ha cap fitxa o esta fora de rang
    public Fitxa getFitxa(int x, int y){
        try {
            return _tauler[x][y];
        }catch(Exception e) {
            return null;
        }
    }

    ///@pre --
    ///@post Elimina les posicions repetides de la llista alp
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

    public Pair<Character, Integer> simularPunts(Posicio posicio, Fitxa fitxaActual, int nHumanets) {
        Fitxa fitxaNord, fitxaSud, fitxaEst, fitxaOest;
        char regioNord = 'N', regioSud = 'N', regioEst = 'N', regioOest = 'N';
        int puntsRotacio = 0, puntsOrigin = 0, puntsMax = 0;
        char regioHumanet = 'X';
        String accio = "crear";
        if (nHumanets <= 3){
            accio = "tancar"; }

        fitxaNord = this.getFitxa(posicio.getPosicioX(), posicio.getPosicioY()-1); if (fitxaNord != null) regioNord = fitxaNord.regio_s();
        fitxaSud = this.getFitxa(posicio.getPosicioX(), posicio.getPosicioY()+1); if (fitxaSud != null) regioSud = fitxaSud.regio_n();
        fitxaEst = this.getFitxa(posicio.getPosicioX()+1, posicio.getPosicioY()); if (fitxaEst != null) regioEst = fitxaEst.regio_o();
        fitxaOest = this.getFitxa(posicio.getPosicioX()-1, posicio.getPosicioY()); if (fitxaOest != null) regioOest = fitxaOest.regio_e();

        if (fitxaActual.regio_c() == 'M') {
            if (fitxaActual.envoltada(this)) {
                puntsRotacio += 9; } }

        puntsRotacio += getPunts(regioNord, fitxaNord, fitxaActual, 'S', accio, nHumanets);
        if ((puntsRotacio-puntsOrigin)>puntsMax) {
            regioHumanet = 'N';
            puntsMax=puntsRotacio;}
        puntsOrigin = puntsRotacio;

        puntsRotacio += getPunts(regioEst, fitxaEst,fitxaActual, 'O', accio,nHumanets);
        if ((puntsRotacio-puntsOrigin)>puntsMax) {
            regioHumanet = 'E';
            puntsMax=puntsRotacio; }
        puntsOrigin = puntsRotacio;

        puntsRotacio += getPunts(regioSud, fitxaSud, fitxaActual, 'N', accio,nHumanets);
        if ((puntsRotacio-puntsOrigin)>puntsMax) {
            regioHumanet = 'S';
            puntsMax=puntsRotacio;}
        puntsOrigin = puntsRotacio;

        puntsRotacio += getPunts(regioOest, fitxaOest, fitxaActual, 'E', accio,nHumanets);
        if ((puntsRotacio-puntsOrigin)>puntsMax) {
            regioHumanet = 'O';
            puntsMax=puntsRotacio;}

        Gui.print("Accio: "+accio+"Punts: "+puntsMax);

        return new Pair<Character, Integer>(regioHumanet, puntsRotacio);
    }

    private int getPunts(char regio, Fitxa fitxa, Fitxa fitxaActual, char loc, String accio, int nHumanets) {
        int punts = 0;
        if (regio == 'V') //Village
        {
            punts = getPuntsRegio(fitxa, fitxaActual, _posCiutat, loc, regio, accio, nHumanets);
        }
        if (regio == 'C') //Cami
        {
            punts = getPuntsRegio(fitxa, fitxaActual, _posCami, loc, regio, accio, nHumanets);
        }
        return  punts;
    }

    private int getPuntsRegio(Fitxa fitxa, Fitxa fitxaActual, ArrayList<Possessio> llistaPos, char loc, char regio, String accio, int nHumanets) {
        int index = getPossessioDeFitxa(fitxa, llistaPos, loc);
        int punts = 0;
        List<Character> regions = getPosicionsDePossessio(fitxaActual,regio,loc);
        if (index != -1) {
            Possessio pos = llistaPos.get(index);//Obtenim la possessió
            if (pos.propietari().size()==0)
            {
                if (accio == "tancar")
                {
                    pos.afegir_fitxa(fitxaActual, regions);
                    if (pos.tancat()) {
                        punts = pos.punts();
                    }
                    pos.eliminar_fitxa(fitxaActual, regions);
                    if (punts == 0 && nHumanets > 1){ //Si té 3 seguidors o menys però no pot tancar prova de crear (últim recurs)
                        return pos.getConjunt().size();
                    }
                } else if (accio == "crear")
                {
                    if (pos.getConjunt().size() == 0){ //Si té més de 3 seguidors però no pot crear, prova de tancar.
                        getPuntsRegio(fitxa, fitxaActual, llistaPos, loc, regio, "tancar", nHumanets); }
                    Gui.print("Creo "+ pos.getConjunt().size()+" punts");
                    return pos.getConjunt().size();
                }
            }
        }
        return punts;
    }

    ///@pre f inicialitzada amb posicio
    ///@post retorna una llista de Caracters en les regions on es pot ficar el seguidor
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

    ///@pre f inicialitzada
    ///@post Elimina de loc les possibilitats de ficar seguidor en les regions regions de la fitxa f on la possessio.get(i) estan ja ocupades per un jugador
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

    ///@pre reg==('C' o 'F' o 'V' o 'M' o 'E')
    ///@post Retorna la llista de possesions del tipus reg
    private ArrayList<Possessio> getLlistaTipusDePossessio(char reg) {
        if(reg=='V')return _posCiutat;
        else if(reg=='C')return _posCami;
        else if(reg=='E')return _posEsglesia;
        else return _posCamp;
    }

    ///@pre reg==('C' o 'F' o 'V' o 'M' o 'E')
    ///@post Retorna la possesio del tipus reg
    private Possessio getTipusDePossessio(char reg,int i) {
        if(reg=='V')return _posCiutat.get(i);
        else if(reg=='C')return _posCami.get(i);
        else if(reg=='E')return _posEsglesia.get(i);
        else return _posCamp.get(i);
    }

    ///@pre --
    ///@post Reparteix els punts de les possessions sense tancar als jugadors corresponents
    public void assignaPuntsAPossessionsSenseTancar(){
        reparteixPuntsDePossessio(_posCami);
        reparteixPuntsDePossessio(_posCiutat);
        reparteixPuntsDePossessio(_posEsglesia);
        reparteixPuntsDePossessio(_posCamp);
    }

    ///@pre --
    ///@post Reparteix els punts de la llista de possessions p sense tancar als jugadors corresponents
    private void reparteixPuntsDePossessio(ArrayList<Possessio> p){
        for(int i=0;i<p.size();i++){
            int punts=p.get(i).punts();
            List<Integer> jugador=p.get(i).propietari();
            for(int x=0;x<jugador.size();x++)
                Joc.AfegeixPuntuacioAJugador(jugador.get(x),punts/jugador.size(),p.get(i).tipus());
        }
    }
}
