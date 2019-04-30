import java.util.List;

public class Possessio {
    private String propietari;
    private List<Fitxa> conjunt;

    public Possessio(String propietari, Fitxa inici){
        this.propietari = propietari;
        conjunt.add(inici);
    }
}
