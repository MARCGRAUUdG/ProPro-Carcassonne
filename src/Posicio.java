import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Posicio implements Comparable<Posicio>{
    private int _x;
    private int _y;
    private int _rotacio;

    //Constructor per defecte
    public Posicio(){
        _x=-1;
        _y=-1;
        _rotacio=0;
    }

    //Constructor amb parametres definits x,y i rotacio
    public Posicio(int x,int y, int rotacio){
        _x=x;
        _y=y;
        _rotacio=rotacio;
    }

    //Constructor amb parametres definits x,y
    public Posicio(int x,int y){
        _x=x;
        _y=y;
        _rotacio=0;
    }

    //Pre: --
    //Post:defineix cordenades x i y
    public void setPosicio(int x, int y){
        _x=x;
        _y=y;
    }

    //Pre: --
    //Post: defineix la _rotacio
    public void setRotacio(int rotacio){
        _rotacio=rotacio;
    }

    //Pre: --
    //Post: retorna _x
    public int getPosicioX(){
        return _x;
    }

    //Pre: --
    //Post: retorna _y
    public int getPosicioY(){
        return _y;
    }

    //Pre: --
    //Post: retorna _rotacio
    public int getRotacio(){
        return _rotacio;
    }

    //Pre: --
    //Post:Retorna string de la descripcio de Posicio
    @Override
    public String toString() {
        return "Posicio{"+"x=" + _x +", y=" + _y +"} "+_rotacio+"º";
    }

    @Override
    public int compareTo(Posicio o)
    {
        int r=-1;
        if(_x==o._x && _y==o._y && _rotacio==o._rotacio)
            r=0;
        else if(_x==o._x && _y==o._y)
            r=1;
        return r;
    }

    @Override
    public boolean equals(Object o) {
        return (_x==((Posicio)o)._x && _y==((Posicio)o)._y && _rotacio==((Posicio)o)._rotacio);
    }

    public int simularPunts(Fitxa fitxaActual, Baralla baralla, Tauler tauler) {
        Fitxa fitxaNord, fitxaSud, fitxaEst, fitxaOest;
        char regioNord = 'X', regioSud = 'X', regioEst = 'X', regioOest = 'X';
        int puntsRotacio = 0;

        fitxaNord = tauler.getFitxa(_x+1, _y);
        fitxaSud = tauler.getFitxa(_x-1, _y);
        fitxaEst = tauler.getFitxa(_x, _y+1);
        fitxaOest = tauler.getFitxa(_x, _y-1);

        if (fitxaNord != null) regioNord = fitxaNord.regio_s();
        if (fitxaSud != null) regioSud = fitxaSud.regio_n();
        if (fitxaEst != null) regioEst = fitxaEst.regio_o();
        if (fitxaOest != null) regioOest = fitxaOest.regio_e();

        ArrayList<Regio> regionsActual = fitxaActual.getRegions(); // N, E, S, O

        if (fitxaActual.regio_c() == 'M')
        {
            if (fitxaActual.envoltada(tauler))
            {
                puntsRotacio += 9;
            }
        }
        else
        {
            if (regioNord != 'X' && regioNord == regionsActual.get(1).lletra())
            {
                puntsRotacio += getPunts(regioNord, tauler, fitxaNord, fitxaActual);
            }
            if (regioEst != 'X' && regioEst == regionsActual.get(2).lletra())
            {
                puntsRotacio += getPunts(regioEst, tauler, fitxaEst,fitxaActual);
            }
            if (regioSud != 'X' && regioSud == regionsActual.get(3).lletra())
            {
                puntsRotacio += getPunts(regioSud, tauler, fitxaSud, fitxaActual);
            }
            if (regioOest != 'X' && regioOest == regionsActual.get(4).lletra())
            {
                puntsRotacio += getPunts(regioOest, tauler, fitxaOest, fitxaActual);
            }
        }

        return puntsRotacio;
    }

    private int getPunts(char regio, Tauler tauler, Fitxa fitxa, Fitxa fitxaActual) {
        int punts = 0;
        Possessio actual;
        if (regio == 'V') //Village
        {
            actual = tauler.get_posCiutat().get(tauler.getPossessioDeFitxa(fitxa, tauler.get_posCiutat())); //Obtenim la possessió
            Gui.print(actual.toString());
            actual.afegir_fitxa(fitxaActual);
            if (actual.tancat())
            {
                punts += actual.punts();
            }
            actual.eliminar_fitxa(fitxaActual);
        }
        else if (regio == 'C') //Cami
        {
            actual = tauler.get_posCami().get(tauler.getPossessioDeFitxa(fitxa, tauler.get_posCami())); //Obtenim la possessió
            actual.afegir_fitxa(fitxaActual);
            if (actual.tancat())
            {
                punts += actual.punts();
            }
            actual.eliminar_fitxa(fitxaActual);
        }
        return  punts;
    }
}
