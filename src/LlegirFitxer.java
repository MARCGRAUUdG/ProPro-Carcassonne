import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LlegirFitxer {

    private static File fitxer;
    private static int _nJugadors;
    private static List<Jugador> _jugadors;
    private static Baralla baralla;
    private static Fitxa _inicial;
    private static boolean _camperols;
    private static boolean fitxerOK = false;


    ///Pre: ---
    ///Post: lectura completa del fitxer
    public static void llegirFitxer() throws FileNotFoundException {
        try (Scanner input = new Scanner(fitxer))
        {
            llegirJugadors(input);
            llegirRajoles(input);
            llegirDadesPartida(input);
            fitxerOK = true;
        } catch (IOException | NoSuchElementException | IllegalArgumentException | NullPointerException ex)
        {
            Gui.informarFitxerEntradaIncorrecte(ex.getMessage());
        }
    }

    ///Pre: Scanner del fitxer d'entrada
    ///Post: Llegeix i guarda la fitxa inicial i si hi ha camperols
    private static void llegirDadesPartida(Scanner input)
    {
        input.next("rajola_inicial"); //saltem String

        try {
            _inicial = new Fitxa(input.next());
        } catch (Excepcio excepcio) {
            Gui.informarFitxerEntradaIncorrecte("Format de la fitxa incorrecte ("+input.next()+")");
        }

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

    ///Pre: Scanner del fitxer d'entrada
    ///Post: Llegeix i guarda les diferents fitxes
    private static void llegirRajoles(Scanner input)
    {
        input.next(); //saltem "rajoles"
        String valFitxa = input.next();

        int numFitxes = input.nextInt();
        while (!valFitxa.equals("#"))
        {
            for (int i = 0; i < numFitxes; i++)
            {
                Fitxa f = null;
                try {
                    f = new Fitxa(valFitxa);
                } catch (Excepcio excepcio) {
                    Gui.informarFitxerEntradaIncorrecte("Format de la fitxa incorrecte ("+valFitxa+")");
                }
                baralla.afegirFitxa(f);
            }

            valFitxa = input.next();

            if (!valFitxa.equals("#")) {
                numFitxes = input.nextInt();
            }
        }

        /*for (Fitxa f : _llistaFitxes)
        {
            Gui.print(f.toString());
        }*/
    }

    ///Pre: Scanner del fitxer d'entrada
    ///Post: Llegeix i guarda el nombre de jugadors i quins d'aquests són màquina
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

        for (int i = 1; i <= _nJugadors; i++)
        {
            Jugador j = new Controlable(i);
            _jugadors.add(j);
        }

        /*for (int j : _jugadorsMaquina)
        {
            Gui.print(String.valueOf(j));
        }*/
    }

    ///Pre: Nom del fitxer d'entrada
    ///Post: Guarda el nom del fitxer d'entrada i crida el mètode per llegir el fitxer
    public static void nomFitxer(String text) throws FileNotFoundException {
        Gui.print("Fitxer '"+text+"' carregant...");
        File f = new File(text);
        fitxer = f;
        llegirFitxer();
    }

    ///Pre: ---
    ///Post: Retorna una llista de quins dels jugadors són controlats per màquina
    public static List<Jugador> getJugadors() {
        return _jugadors;
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

    ///Pre:
    ///Post: Cert si la lectura s'ha realitzat satisfactòriament
    public static boolean lecturaCorrecta() {
        return fitxerOK;
    }

    ///Pre: ---
    ///Post: Retorna la baralla
    public Baralla getBaralla() {
        return baralla;
    }
}
