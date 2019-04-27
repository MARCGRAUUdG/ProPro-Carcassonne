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

        //Gui.print(_inicial.toString());
        input.next("camperols"); //saltem String
        String camperols = input.next();
        if (camperols.equals("si"))
        {
            _camperols = true;
            //Gui.print("TRUE");
        }
        else
        {
            _camperols = false;
            //Gui.print("FALSE");
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
            Gui.print(f.toString());
        }*/
    }

    private static void llegirJugadors(Scanner input)
    {
        List <Integer>jugadorsMaquina = new ArrayList<>();

        input.next("nombre_jugadors"); //saltem String
        _nJugadors = input.nextInt(); //guardem el nombre de jugadors

        //Gui.print(String.valueOf(_nJugadors));

        input.next("jugadors_cpu"); //saltem String
        while (input.hasNextInt())
        {
            int element = input.nextInt();
            jugadorsMaquina.add(element);
        }
        _jugadorsMaquina = jugadorsMaquina;

        /*for (int j : _jugadorsMaquina)
        {
            Gui.print(String.valueOf(j));
        }*/
    }

    public static void nomFitxer(String text) throws FileNotFoundException {
        Gui.print("Fitxer '"+text+"' carregant");
        File f = new File(text);
        fitxer = f;
        llegirFitxer();
    }

    public static int getJugadors() {
        return _nJugadors;
    }

    public static List<Integer> getJugadorsMaquina() {
        return _jugadorsMaquina;
    }

    public static List<Fitxa> getLlistaFitxes() {
        return _llistaFitxes;
    }

    public static Fitxa getInicial() {
        return _inicial;
    }

    public static boolean isCamperols() {
        return _camperols;
    }
}
