import java.util.ArrayList;

public class Fitxa extends Excepcio{

    //Descripcio: Fitxa fromat per regions C, N, E, S i O;

    private ArrayList<Regio> regions;
    private Posicio pos;
    
    //Pre:---
    //Post: retorna la llista de regions de la fitxa
    public ArrayList<Regio> getRegions(){
        return regions;
    }

    //Pre: lletres mida = 5
    //Post: guarda a regions el format de la fitxa
    public Fitxa(String lletres)throws Excepcio{
        if(lletres.length()==5) {
            regions = new ArrayList<Regio>();
            for (int i = 0; i < lletres.length(); i++) {
                char lletra = lletres.charAt(i);
                Regio nou = new Regio(lletra);
                regions.add(nou);
            }
            pos = new Posicio();

        }
        else {
            throw new Excepcio("La mida és incorrecta");
        }
    }

    //Pre: regio = C, N, E, S o O
    //Post: afegeix el jugador a la regio de la fitxa
    public void assignar_seguidor(char regio, int jugador){
        try {
            if (regio == 'C') {
                regions.get(0).posar_seguidor(jugador);
            } else if (regio == 'N') {
                regions.get(1).posar_seguidor(jugador);
            } else if (regio == 'E') {
                regions.get(2).posar_seguidor(jugador);
            } else if (regio == 'S') {
                regions.get(3).posar_seguidor(jugador);
            } else if (regio == 'O') {
                regions.get(4).posar_seguidor(jugador);
            }
        }
        catch (Excepcio e){
            System.err.println(e);
        }
    }

    //Pre:---
    //Post: retorna seguidor de la regio del centre
    public Integer regio_c_seguidor(){
        return regions.get(0).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format de la regio del centre
    public char regio_c(){

        return regions.get(0).lletra();

    }

    //Pre:---
    //Post: retorna seguidor de la regio del nort
    public Integer regio_n_seguidor(){
        return regions.get(1).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format dela regio del nort
    public char regio_n(){

        return regions.get(1).lletra();
    }

    //Pre:---
    //Post: retorna seguidor de la regio del est
    public Integer regio_e_seguidor(){
        return regions.get(2).nom_jugador();
    }
    //Pre:----
    //Post:retorna el format de la regio de l'est
    public char regio_e(){

        return regions.get(2).lletra();
    }

    //Pre:---
    //Post:si hi ha seguidors en la fitxa retorna el propietari del seguidor que esta a la fitxa altrament -1
    public int jugadorTeLaSeguidor(){
        int jugador=-1;
        if(regio_s_seguidor()!=-1)
            jugador=regio_s_seguidor();
        if(regio_n_seguidor()!=-1)
            jugador=regio_n_seguidor();
        if(regio_e_seguidor()!=-1)
            jugador=regio_e_seguidor();
        if(regio_o_seguidor()!=-1)
            jugador=regio_o_seguidor();
        if(regio_c_seguidor()!=-1)
            jugador=regio_c_seguidor();
        return jugador;
    }


    //Pre:---
    //Post: retorna seguidor de la regio del sud
    public Integer regio_s_seguidor(){
        return regions.get(3).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format de la regio del sud
    public char regio_s() {
        return regions.get(3).lletra();
    }

    //Pre:---
    //Post: retorna seguidor de la regio del oest
    public Integer regio_o_seguidor(){
        return regions.get(4).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format de la regio de l'oest
    public char regio_o(){

        return regions.get(4).lletra();
    }

    //Pre:---
    //Post: la fitxa actual la regio nort = a la regio sud de f
    public boolean nord_igual_sud(Fitxa f){
        return regio_n() == f.regio_s();
    }

    //Pre:---
    //Post: la fitxa actual la regio sud = a la regio nort de f
    public boolean sud_igual_nord(Fitxa f){
        return regio_s() == f.regio_n();
    }

    //Pre:---
    //Post: la fitxa actual la regio est = a la regio oest de f
    public boolean est_igual_oest(Fitxa f){
        return regio_e() == f.regio_o();
    }

    //Pre:---
    //Post: la fitxa actual la regio oest = a la regio est de f
    public boolean oest_igual_est(Fitxa f){
        return regio_o() == f.regio_e();
    }

    //Pre: rotar = 0 o 90 o 180 o 270
    //Post: fitxa rotada
    public void rotar(int rotar){
        if(rotar == 0 || rotar == 90 || rotar == 180 || rotar == 270) {
            Regio aux;
            int dif = rotar - pos.getRotacio();
            if (dif == -90 || dif == 270) {
                aux = new Regio(regio_n());
                for (int i = 1; i < 4; i++) {
                    regions.add(i, regions.get(i + 1));
                    regions.remove(i + 1);
                }
                regions.remove(4);
                regions.add(4, aux);
            } else if (dif == 180 || dif == -180) {
                aux = new Regio(regio_n());
                regions.add(1, regions.get(3));
                regions.remove(2);
                regions.add(3, aux);
                regions.remove(4);

                aux = new Regio(regio_e());
                regions.add(2, regions.get(4));
                regions.remove(3);
                regions.add(4, aux);
                regions.remove(5);
            } else {
                if (dif == -270 || dif == 90) {
                    aux = new Regio(regio_o());
                    for (int i = 4; i > 1; i--) {
                        regions.add(i, regions.get(i - 1));
                        regions.remove(i + 1);
                    }
                    regions.add(1, aux);
                    regions.remove(2);
                }
            }
            pos.setRotacio(rotar);
        }
        else{
            Gui.print("Rotacio de fitxa incorrecte");
        }
    }

    //Pre:---
    //Post: guardar pos
    public void setPosicio(Posicio novaPos) {
        rotar(novaPos.getRotacio());
        this.pos = novaPos;
    }

    //Pre:---
    //Post: retorna posicio
    public Posicio getPosicio() {
        return pos;
    }

    //Pre:---
    //Post:retorna cert si els camps de la fitxa coincideix amb els del seu adjacents camp per camp
    public boolean fitxaEncaixaEls4Costats(char nort, char est, char sud, char oest){
        if(regio_n()==nort && regio_e()==est && regio_s()==sud && regio_o()==oest){
            return true;
        }
        return false;
    }

    //Pre:---
    //Post:retorna cert si la fitxa actual encaixa amb la fitxa 'f' en la posicio del costat de 'direccio' ('N','E','S' o 'O')
    public boolean fitxaActualEncaixaAmb(Fitxa f , char direccio){
        boolean encaixa=false;

        if(f!=null){
            if(direccio == 'N'){
                encaixa= sud_igual_nord(f);
            }
            else if(direccio == 'E'){
                encaixa= oest_igual_est(f);

            }
            else if(direccio == 'S'){
                encaixa= nord_igual_sud(f);

            }
            else if(direccio == 'O'){
                encaixa= est_igual_oest(f);
            }
            else{
                //Gui.print("Posició de fitxa incorrecte");
            }
        }else {
            encaixa = true;
        }
        return encaixa;
    }

    //Pre:---
    //Post: retorna cert si alguna regio té el format E
    public boolean teEscut(){
        if(regio_n()=='E' || regio_e()=='E' || regio_s()=='E' || regio_o()=='E'){
            return true;
        }
        return false;
    }


    //Pre:---
    //Post: retorna cert si la fitxa el centre és X o V o M
    public boolean es_fi_o_inici_de_cami(){
        if(regio_c() == 'X' || regio_c() == 'V' || regio_c() == 'M'){
            return true;
        }
        return false;
    }

    //Pre:---
    //Post: retorna cert si la fitxa el centre no és V i E
    public boolean es_fi_o_inici_de_ciutat(){
        if(regio_c() !='V' && regio_c()!='E'){
            return true;
        }
        return false;
    }

    //Pre:---
    //Post: retorna el numero de bandes que son ciutat
    public Integer bandes_de_ciutat(){
        int num = 0;
        for(int i = 1; i<regions.size(); i++){
            if(regions.get(i).lletra()=='E' || regions.get(i).lletra()=='V'){
                num +=1;
            }
        }
        return num;
    }

    //Pre:---
    //Post: retorna el numero de bandes que son cami
    public Integer bandes_de_cami(){
        int num = 0;
        for(int i = 1; i<regions.size(); i++){
            if(regions.get(i).lletra()=='C'){
                num++;
            }
        }
        return num;
    }

    //Pre:---
    //Post:retorna el format de la fitxa si existeix amb rotacio 0
    public String formatNormal(){
        String fitxa = "";
        int rotacioOriginal=pos.getRotacio();
        rotar(0);
        if(regions.size()>0) {
            for (int i = 0; i < regions.size(); i++) {
                fitxa = fitxa + regions.get(i).lletra();
            }
        }
        rotar(rotacioOriginal);
        return fitxa;
    }

    @Override
    public String toString(){
        String fitxa = "{";

        for(int i=0; i<regions.size(); i++){
            fitxa = fitxa + regions.get(i).lletra();
        }

        fitxa = fitxa +"}";

        return fitxa;
    }

    public boolean envoltada(Tauler tauler) {
        return tauler.getFitxa(this.pos.getPosicioX(), this.pos.getPosicioY()) != null &&
                tauler.getFitxa(this.pos.getPosicioX(), this.pos.getPosicioY()+1) != null &&
                tauler.getFitxa(this.pos.getPosicioX(), this.pos.getPosicioY()-1) != null &&
                tauler.getFitxa(this.pos.getPosicioX()+1, this.pos.getPosicioY()) != null &&
                tauler.getFitxa(this.pos.getPosicioX()-1, this.pos.getPosicioY()) != null &&
                tauler.getFitxa(this.pos.getPosicioX()+1, this.pos.getPosicioY()+1) != null &&
                tauler.getFitxa(this.pos.getPosicioX()+1, this.pos.getPosicioY()-1) != null &&
                tauler.getFitxa(this.pos.getPosicioX()-1, this.pos.getPosicioY()+1) != null &&
                tauler.getFitxa(this.pos.getPosicioX()-1, this.pos.getPosicioY()-1) != null;}

    public boolean elSeguidorEstaEnElSeuTipusDeRegio(char tipus) {
        char regio='X';
        if(regio_c_seguidor()!=-1)     regio=regio_c();
        else if(regio_n_seguidor()!=-1)regio=regio_n();
        else if(regio_e_seguidor()!=-1)regio=regio_e();
        else if(regio_s_seguidor()!=-1)regio=regio_s();
        else if(regio_o_seguidor()!=-1)regio=regio_o();
        return regio==tipus;
    }
}