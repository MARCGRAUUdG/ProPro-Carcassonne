import java.util.List;

public class Controlable extends Jugador {

    Controlable(int i)
    {
        super (i);
    }

    @Override
    public boolean esControlable() {
        return true;
    }


}
