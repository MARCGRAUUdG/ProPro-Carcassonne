import java.util.List;

public class Maquina extends Jugador {
    Maquina(int i)
    {
        super (i);
    }

    @Override
    public boolean esControlable() {
        return false;
    }
}
