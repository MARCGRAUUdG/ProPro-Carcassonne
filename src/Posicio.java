import javafx.geometry.Pos;

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

    //Constructor amb parametres definits x,y i posicio
    public Posicio(int x,int y, int rotacio){
        _x=x;
        _y=y;
        _rotacio=rotacio;
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
        return (this.compareTo((Posicio)o))==0;
    }
}
