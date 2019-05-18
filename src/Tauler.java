import javafx.util.Pair;

import java.util.*;


public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];
    private static ArrayList<Possessio> _posCami= new ArrayList<>();
    private static ArrayList<Camp> _posCamp= new ArrayList<>();
    private static ArrayList<Esglesia> _posEsglesia= new ArrayList<>();
    private static ArrayList<Possessio> _posCiutat= new ArrayList<>();

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

        if(f.regio_c()=='X'){
            // creuament
            if(f.bandes_de_ciutat()>0){
                //amb ciutat
            }
            else{
                //sense ciutat
            }
        }
        else if(f.es_fi_o_inici_de_cami() && f.bandes_de_cami()==1){
            //fitxa te cami units amb monestir o ciutat

            if(f.regio_c()=='M'){
                // monestir
            }
            else{
                //ciutat
            }
        }
        else if(f.es_fi_o_inici_de_ciutat()){
            // fitxa no te ciutats el centre nomes bandes
            if(f.regio_c()=='C'){
                //amb cami
            }
            else if(f.bandes_de_ciutat()==2){
                //2 ciutats separats sense cami
            }
            else{
                //1 ciutat sense cami
            }
        }
        else if(f.regio_c()=='V') {
            //fitxa te un ciutat en el centre
            if(f.bandes_de_cami()==0){
                //sense cami
                if(f.teEscut()){
                    //amb escut
                }
                else{
                    //sense escut
                }
            }
            else{
                //amb cami
            }
        }
        else if(f.regio_c()=='C'){
            //cami sense ciutat ni monestir ni creuament
        }
        else{
            //monestir sense cami
        }
        posaFitxaAPossessioSiNoEstaPosat(f, _posCami, 'C');
        posaFitxaAPossessioSiNoEstaPosat(f,_posCiutat, 'V');

        comprovaPossessionsTancades(_posCiutat);
        comprovaPossessionsTancades(_posCami);
    }

    private void comprovaPossessionsTancades(ArrayList<Possessio> p) {
        for(int i=p.size()-1;i>=0;i--) {
            //Gui.print(_posCiutat.get(i).toString()+" Esta tancat: "+_posCiutat.get(i).tancat());
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
                for (int x=(lp.size()-1); x>=0; x--) {//Nomes elimina els seguidors on la seva regio esta afectada
                    if (lp.get(x).getKey().elSeguidorEstaEnElSeuTipusDeRegio(p.get(i).tipus()))//TODO Aquesta funcio no considera diferent possessions en una mateixa fitxa
                        fitxes.add(lp.get(x).getKey());
                }
                Gui.treuSeguidorsDe(fitxes);
                p.remove(i);
            }
        }
    }



    private void posaFitxaAPossessioSiNoEstaPosat(Fitxa f, ArrayList<Possessio> p, char lletra) {
        if(lletra=='C' || lletra=='V') {
            if(f.regio_c()==lletra){
                List<Character> pos=new ArrayList<>();
                pos.add('C');
                if(f.regio_n()==lletra)pos.add('N');
                if(f.regio_e()==lletra)pos.add('E');
                if(f.regio_s()==lletra)pos.add('S');
                if(f.regio_o()==lletra)pos.add('O');
                if(lletra=='C') p.add(new Cami(f,pos));
                else if (lletra=='V')p.add(new Ciutat(f,pos));
            }else{
                if(f.regio_n()==lletra){
                    List<Character> pos=new ArrayList<>();
                    pos.add('N');
                    if(lletra=='C') p.add(new Cami(f,pos));
                    else if (lletra=='V')p.add(new Ciutat(f,pos));
                }
                if(f.regio_e()==lletra){
                    List<Character> pos=new ArrayList<>();
                    pos.add('E');
                    if(lletra=='C') p.add(new Cami(f,pos));
                    else if (lletra=='V')p.add(new Ciutat(f,pos));
                }
                if(f.regio_s()==lletra){
                    List<Character> pos=new ArrayList<>();
                    pos.add('S');
                    if(lletra=='C') p.add(new Cami(f,pos));
                    else if (lletra=='V')p.add(new Ciutat(f,pos));
                }
                if(f.regio_o()==lletra){
                    List<Character> pos=new ArrayList<>();
                    pos.add('O');
                    if(lletra=='C') p.add(new Cami(f,pos));
                    else if (lletra=='V')p.add(new Ciutat(f,pos));
                }
            }
        }else{
            //TODO Falta implementar les altres possessions
        }
    }

    private void afegirPossessio(Fitxa fAnterior, Fitxa fNova, char reg, char loc) {
        if (reg == 'C') {
            //Posa la fitxa en la possessio de la fitxa del costat
            int pin=getPossessioDeFitxa(fAnterior,_posCami,loc);
            List<Character> lPos=new ArrayList<>();
            lPos = getPosicionsDePossessio(fNova,reg,loc);
            if(pin!=-1)
                _posCami.get(pin).afegir_fitxa(fNova,lPos);
        }else if(reg=='F'){

        }else if(reg=='V'){
            int pin=getPossessioDeFitxa(fAnterior,_posCiutat,loc);
            List<Character> lPos=new ArrayList<>();
            lPos = getPosicionsDePossessio(fNova,reg,loc);
            if(pin!=-1)
                _posCiutat.get(pin).afegir_fitxa(fNova,lPos);

        }else if(reg=='M'){

        }else{//TODO Falta E

        }
    }

    private List<Character> getPosicionsDePossessio(Fitxa f, char reg, char loc) {
        List<Character> lPos=new ArrayList<>();
        if(f.regio_c()=='X')lPos.add(loc);
        else{
            if(f.regio_n()==reg)lPos.add('N');
            if(f.regio_e()==reg)lPos.add('E');
            if(f.regio_s()==reg)lPos.add('S');
            if(f.regio_o()==reg)lPos.add('O');
        }
        return lPos;
    }

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

    private boolean estaEnLaLlista(Fitxa f, ArrayList<Possessio> possessio, char loc) {
        boolean trobat=false;
        int i=0;
        while(i<possessio.size()&& !trobat){
            if(possessio.get(i).pertanyLaFitxa(f,loc))
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
