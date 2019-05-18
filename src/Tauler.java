import java.util.*;


public class Tauler
{
    private static final Fitxa[][] _tauler = new Fitxa[10][10];
    private static ArrayList<Possessio> _posCami= new ArrayList<>();
    private static ArrayList<Camp> _posCamp= new ArrayList<>();
    private static ArrayList<Esglesia> _posEsglesia= new ArrayList<>();
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

    ///Pre:--
    ///Post:Si de la llista p hi ha alguna possessio tancada assigna punts als jugadors, retorna els seguidors, i l'elimina de la llista
    private void comprovaPossessionsTancades(ArrayList<Possessio> p) {
        for(int i=p.size()-1;i>=0;i--) {
            //Gui.print(_posCiutat.get(i).toString());
            //Gui.print("Esta tancat: "+_posCiutat.get(i).tancat());
            if(p.get(i).tancat()) {
                List<Fitxa> fitxes=p.get(i).getConjunt();
                for(int y=0;y<fitxes.size();y++){
                    int jugador=fitxes.get(y).jugadorTeLaSeguidor();
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
                for (int x=(fitxes.size()-1); x>=0; x--) {//Nomes elimina els seguidors on la seva regio esta afectada
                    if (!fitxes.get(x).elSeguidorEstaEnElSeuTipusDeRegio(p.get(i).tipus()))
                        fitxes.remove(x);
                }
                Gui.treuSeguidorsDe(fitxes);
                p.remove(i);
            }
        }
    }

    ///Pre: lletra==('C' o 'V')
    ///Post:Posa la fitxa f a la possessio p del tipus lletra si no hi es a la llista p
    private void posaFitxaAPossessioSiNoEstaPosat(Fitxa f, ArrayList<Possessio> p, char lletra) {
        if(!estaEnLaLlista(f,p) && (f.regio_n()==lletra || f.regio_s()==lletra || f.regio_e()==lletra || f.regio_o()==lletra)){//Posa el cami
            if(lletra=='C')
                p.add(new Cami(f));
            else if(lletra=='V')
                p.add(new Ciutat(f));
        }
    }

    ///Pre:fAnterior, fNova inicializats amb posicio, reg==('C' o 'F' o 'V' o 'M' o 'E')
    ///Post:Afegeix la fitxa fNova a la mateixa possessio que fAnterior del tipus reg
    private void afegirPossessio(Fitxa fAnterior, Fitxa fNova, char reg) {
        if (reg == 'C') {
            //Posa la fitxa en la possessio de la fitxa del costat
            int pin=getPossessioDeFitxa(fAnterior,_posCami);
            if(pin!=-1)
                _posCami.get(pin).afegir_fitxa(fNova);
        }else if(reg=='F'){

        }else if(reg=='V'){
            int pin=getPossessioDeFitxa(fAnterior,_posCiutat);
            if(pin!=-1)
                _posCiutat.get(pin).afegir_fitxa(fNova);

        }else if(reg=='M'){

        }else{//TODO Falta E

        }
    }

    ///Pre:f inicialitzat
    ///Post:Retorna la posicio de la llista possessio de on es troba la fitxa f, -1 si no hi es en la llista
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

    ///Pre:f inicialitzat
    ///Post:Retorna cert si la fitxa f esta en alguna llista de possessio fals altrament
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
}
