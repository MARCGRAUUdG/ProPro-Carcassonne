import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LlegirFitxer {

    private static File fitxer;
    private static int _nJugadors;
    private static List<Integer> _jugadorsMaquina;
    private static List<Fitxa> _llistaFitxes;
    private static Fitxa _inicial;
    private static boolean _camperols;


    ///Pre: ---
    ///Post: lectura completa del fitxer
    public static void llegirFitxer() throws FileNotFoundException {
        Scanner input = new Scanner(fitxer);

        llegirJugadors(input);
        llegirRajoles(input);
        llegirDadesPartida(input);
    }

    ///Pre: Scanner del fitxer d'entrada
    ///Post: Llegeix i guarda la fitxa inicial i si hi ha camperols
    private static void llegirDadesPartida(Scanner input)
    {
        input.next("rajola_inicial"); //saltem String
        _inicial = new Fitxa(input.next());

        //Gui.addRow(_inicial.toString());
        input.next("camperols"); //saltem String
        String camperols = input.next();
        if (camperols.equals("si"))
        {
            _camperols = true;
            //Gui.addRow("TRUE");
        }
        else
        {
            _camperols = false;
            //Gui.addRow("FALSE");
        }
    }

    ///Pre: Scanner del fitxer d'entrada
    ///Post: Llegeix i guarda les diferents fitxes
    private static void llegirRajoles(Scanner input)
    {
        List<Fitxa> llistaFitxes = new ArrayList<Fitxa>();

        input.next(); //saltem "rajoles"
        String valFitxa = input.next();

        int numFitxes = input.nextInt();
        while (!valFitxa.equals("#"))
        {
            for (int i = 0; i < numFitxes; i++)
            {
                Fitxa f = new Fitxa(valFitxa);
                llistaFitxes.add(f);
            }

            valFitxa = input.next();

            if (!valFitxa.equals("#")) {
                numFitxes = input.nextInt();
            }
        }
        _llistaFitxes = llistaFitxes;

        /*for (Fitxa f : _llistaFitxes)
        {
            Gui.addRow(f.toString());
        }*/
    }

    ///Pre: Scanner del fitxer d'entrada
    ///Post: Llegeix i guarda el nombre de jugadors i quins d'aquests són màquina
    private static void llegirJugadors(Scanner input)
    {
        List <Integer>jugadorsMaquina = new ArrayList<>();

        input.next("nombre_jugadors"); //saltem String
        _nJugadors = input.nextInt(); //guardem el nombre de jugadors

        //Gui.addRow(String.valueOf(_nJugadors));

        input.next("jugadors_cpu"); //saltem String
        while (input.hasNextInt())
        {
            int element = input.nextInt();
            jugadorsMaquina.add(element);
        }
        _jugadorsMaquina = jugadorsMaquina;

        /*for (int j : _jugadorsMaquina)
        {
            Gui.addRow(String.valueOf(j));
        }*/
    }

    ///Pre: Nom del fitxer d'entrada
    ///Post: Guarda el nom del fitxer d'entrada i crida el mètode per llegir el fitxer
    public static void nomFitxer(String text) throws FileNotFoundException {
        Gui.print("Fitxer '"+text+"' carregant");
        File f = new File(text);
        fitxer = f;
        llegirFitxer();
    }


    ///Pre: ---
    ///Post: Retorna el nombre de jugadors
    public static int getJugadors() {
        return _nJugadors;
    }

    ///Pre: ---
    ///Post: Retorna una llista de quins dels jugadors són controlats per màquina
    public static List<Integer> getJugadorsMaquina() {
        return _jugadorsMaquina;
    }

    ///Pre: ---
    ///Post: Retorna la llista de fitxes
    public static List<Fitxa> getLlistaFitxes() {
        return _llistaFitxes;
    }

    ///Pre: ---
    ///Post: Retorna la fitxa inicial
    public static Fitxa getInicial() {
        return _inicial;
    }

    ///Pre: ---
    ///Post: Cert si hi ha camperols
    public static boolean isCamperols() {
        return _camperols;
    }
}
