///@class Posicio

///@brief Cordenades i rotació que especifica un estat de localització

import java.util.ArrayList;
import java.util.List;

public class Posicio implements Comparable<Posicio>{
    private int _x;///<Coordenada x
    private int _y;///<Coordenada y
    private int _rotacio;///<Rotacio d'estat

    ///@pre --
    ///@post Constuctor per defecte de Posicio
    public Posicio(){
        _x=-1;
        _y=-1;
        _rotacio=0;
    }

    ///@pre --
    ///@post Constuctor per valor x,y i rotacio de Posicio
    public Posicio(int x,int y, int rotacio){
        _x=x;
        _y=y;
        _rotacio=rotacio;
    }

    ///@pre --
    ///@post Constuctor per valor x,y de Posicio
    public Posicio(int x,int y){
        _x=x;
        _y=y;
        _rotacio=0;
    }

    ///@pre  --
    ///@post defineix cordenades x i y
    public void setPosicio(int x, int y){
        _x=x;
        _y=y;
    }

    ///@pre  --
    ///@post  defineix la _rotacio
    public void setRotacio(int rotacio){
        _rotacio=rotacio;
    }

    ///@pre  --
    ///@post  retorna _x
    public int getPosicioX(){
        return _x;
    }

    ///@pre  --
    ///@post  retorna _y
    public int getPosicioY(){
        return _y;
    }

    ///@pre  --
    ///@post  retorna _rotacio
    public int getRotacio(){
        return _rotacio;
    }

    ///@pre  --
    ///@post Retorna string de la descripcio de Posicio
    @Override
    public String toString() {
        return "Posicio{"+"x=" + _x +", y=" + _y +"} "+_rotacio+"º";
    }

    ///@pre  --
    ///@post Retorna 0 si x,y i rotacio coincideix, 0 1 si x,y coincideix, -1 altrament
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

    ///@pre  --
    ///@post Retorna cert si this==o
    @Override
    public boolean equals(Object o) {
        return (_x==((Posicio)o)._x && _y==((Posicio)o)._y && _rotacio==((Posicio)o)._rotacio);
    }
}
