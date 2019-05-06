import java.util.ArrayList;

public class Fitxa extends Excepcio{

    //Descripcio: Fitxa fromat per regions C, N, E, S i O;

    private ArrayList<Regio> regions;
    private Posicio pos;

    //Pre:---
    //Post:copia la fitxa f a l'actual
    public Fitxa(Fitxa f){
        regions = new ArrayList<Regio>(5);
        for (int i = 0; i < f.getRegions().size(); i++) {
            regions.add(i,f.getRegions().get(i));
        }
        pos.setPosicio(f.getPosicio().getPosicioX(),f.getPosicio().getPosicioY());
    }

    //Pre: lletres mida = 5
    //Post: guarda a regions el format de la fitxa
    public Fitxa(String lletres)throws Excepcio{
        if(lletres.length()==5) {
            regions = new ArrayList<Regio>(5);
            for (int i = 0; i < lletres.length(); i++) {
                char lletra = lletres.charAt(i);
                Regio nou = new Regio(lletra);
                regions.add(nou);
            }
            pos.setRotacio(0);
        }
        else {
            throw new Excepcio("La mida és incorrecta");
        }
    }

    //Pre:---
    //Post: retorna la llista de regions de la fitxa
    public ArrayList<Regio> getRegions(){
        return regions;
    }

    //Pre: regio = C, N, E, S o O
    //Post: afegeix el jugador a la regio de la fitxa
    public void assignar_seguidor(char regio, String jugador)throws Excepcio{
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
            } else {
                throw new Excepcio("La regio és incorrecta");
            }
        }
        catch (Excepcio e){
            System.err.println(e);
        }
    }

    //Pre:---
    //Post: retorna seguidor de la regio del centre
    public String regio_c_seguidor(){
        if(regions.get(0).nom_jugador()==null){
            return null;
        }
        return regions.get(0).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format de la regio del centre
    public char regio_c(){

        return regions.get(0).lletra();

    }

    //Pre:---
    //Post: retorna seguidor de la regio del nort
    public String regio_n_seguidor(){
        if(regions.get(1).nom_jugador()==null){
            return null;
        }
        return regions.get(1).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format dela regio del nort
    public char regio_n(){

        return regions.get(1).lletra();
    }

    //Pre:---
    //Post: retorna seguidor de la regio del est
    public String regio_e_seguidor(){
        if(regions.get(2).nom_jugador()==null){
            return null;
        }
        return regions.get(2).nom_jugador();
    }
    //Pre:----
    //Post:retorna el format de la regio de l'est
    public char regio_e(){

        return regions.get(2).lletra();
    }

    //Pre:---
    //Post: retorna seguidor de la regio del sud
    public String regio_s_seguidor(){
        if(regions.get(3).nom_jugador()==null){
            return null;
        }
        return regions.get(3).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format de la regio del sud
    public char regio_s() {
        return regions.get(3).lletra();
    }

    //Pre:---
    //Post: retorna seguidor de la regio del oest
    public String regio_o_seguidor(){
        if(regions.get(4).nom_jugador()==null){
            return null;
        }
        return regions.get(4).nom_jugador();
    }

    //Pre:---
    //Post:retorna el format de la regio de l'oest
    public char regio_o(){

        return regions.get(4).lletra();
    }

    //Pre: rotar = 90 o 180 o 270
    //Post: retorna una fitxa rotada
    public Fitxa rotar_fitxa(int rotar)throws Excepcio{

        Fitxa nou;
        if(rotar == 90 || rotar == 180 || rotar == 270) {
            nou = new Fitxa(this);
            nou.pos.setRotacio(rotar);
            Regio aux;
            if (rotar == 90) {
                aux = new Regio(regio_n());
                for (int i = 1; i < 4; i++) {
                    nou.getRegions().remove(i);
                    nou.getRegions().add(i, nou.getRegions().get(i + 1));
                }
                nou.getRegions().remove(4);
                nou.getRegions().add(4, aux);
            } else if (rotar == 180) {
                aux = new Regio(regio_n());
                nou.getRegions().remove(1);
                nou.getRegions().add(1, nou.getRegions().get(3));
                nou.getRegions().remove(3);
                nou.getRegions().add(3, aux);

                aux = new Regio(regio_e());
                nou.getRegions().remove(2);
                nou.getRegions().add(2, nou.getRegions().get(4));
                nou.getRegions().remove(4);
                nou.getRegions().add(4, aux);
            } else {
                aux = new Regio(regio_o());
                for (int i = 4; i > 1; i--) {
                    nou.getRegions().remove(i);
                    nou.getRegions().add(i, nou.getRegions().get(i - 1));
                }
                nou.getRegions().remove(1);
                nou.getRegions().add(1, aux);
            }
        }
        else{
            throw new Excepcio("Rotació incorrecta");
        }
        return nou;
    }

    //Pre:---
    //Post: guardar pos
    public void setPosicio(Posicio pos) {
        this.pos = pos;
    }

    //Pre:---
    //Post: retorna posicio
    public Posicio getPosicio() {
        return pos;
    }

    //Pre:---
    //Post:retorna cert si els camps de la fitxa coincideix amb els del seu adjacent camp per camp
    public boolean fitxa_encaixa(char nort, char est, char sud, char oest){
        if(regio_n()==nort && regio_e()==est && regio_s()==sud && regio_o()==oest){
            return true;
        }
        return false;
    }

    //Pre:---
    //Post:retorna cert si la fitxa actual encaixa amb la fitxa 'f' en la posicio del costat de 'direccio' ('N','E','S' o 'O')
    public boolean fitxaActualEncaixaAmb(Fitxa f , char direccio) throws Excepcio{
        if(direccio == 'N'){
            if(f.regio_n()==this.regio_s()){
                return true;
            }
        }
        else if(direccio == 'E'){
            if(f.regio_e()==this.regio_o()){
                return true;
            }
        }
        else if(direccio == 'S'){
            if(f.regio_s()==this.regio_n()){
                return true;
            }
        }
        else if(direccio == 'O'){
            if(f.regio_o()==this.regio_e()){
                return true;
            }
        }
        else{
            throw new Excepcio("Direcció incorrecta");
        }
        return false;
    }


    //Pre:---
    //Post:retorna el format de la fitxa si existeix altrament null
    public String format_fitxa(){
        String fitxa = "";

        if(regions.size()>0) {
            for (int i = 0; i < regions.size(); i++) {
                fitxa = fitxa + regions.get(i).lletra();
            }
        }

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
}
