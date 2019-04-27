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


    public static void llegirFitxer() throws FileNotFoundException {
        Scanner input = new Scanner(fitxer);

        llegirJugadors(input);
        llegirRajoles(input);
        llegirDadesPartida(input);
    }

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

    public static void nomFitxer(String text) throws FileNotFoundException {
        Gui.addRow("Fitxer '"+text+"' carregant");
        File f = new File(text);
        fitxer = f;
        llegirFitxer();
    }

    public static int get_nJugadors() {
        return _nJugadors;
    }

    public static List<Integer> get_jugadorsMaquina() {
        return _jugadorsMaquina;
    }

    public static List<Fitxa> get_llistaFitxes() {
        return _llistaFitxes;
    }

    public static Fitxa get_inicial() {
        return _inicial;
    }

    public static boolean is_camperols() {
        return _camperols;
    }
}
