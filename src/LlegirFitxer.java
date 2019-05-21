import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

///@class LlegirFitxer

///@brief Descripcio de la clase...

public class LlegirFitxer {

    private static File fitxer;///<Descripcio...
    private static int _nJugadors;///<Descripcio...
    private static ArrayList<Jugador> _jugadors;///<Descripcio...
    private static Baralla baralla;///<Descripcio...
    private static Fitxa _inicial;///<Descripcio...
    private static boolean _camperols;///<Descripcio...
    private static boolean fitxerOK = false;///<Descripcio...

    ///@pre  ---
    ///@post  lectura completa del fitxer
    public static void llegirFitxer() {
        try (Scanner input = new Scanner(fitxer))
        {
            llegirJugadors(input);
            llegirRajoles(input);
            llegirDadesPartida(input);
            Gui.print("S'ha llegit el fitxer correctament.");
            fitxerOK = true;
        } catch (IOException | NoSuchElementException | IllegalArgumentException | NullPointerException ex)
        {
            Gui.informarFitxerEntradaIncorrecte(ex.getMessage());
        }
    }

    ///@pre  Scanner del fitxer d'entrada
    ///@post  Llegeix i guarda la fitxa inicial i si hi ha camperols
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

    ///@pre  Scanner del fitxer d'entrada
    ///@post  Llegeix i guarda les diferents fitxes
    private static void llegirRajoles(Scanner input)
    {
        baralla = new Baralla();
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

    ///@pre  Scanner del fitxer d'entrada
    ///@post  Llegeix i guarda el nombre de jugadors i quins d'aquests són màquina
    private static void llegirJugadors(Scanner input)
    {
        _jugadors = new ArrayList();
        ArrayList <Integer>jugadorsMaquina = new ArrayList<>();

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
            if (!jugadorsMaquina.contains(i))
            {
                Jugador j = new Controlable(i);
                _jugadors.add(j);
            }
            else
            {
                Jugador j = new Maquina(i);
                _jugadors.add(j);
            }
        }
        /*for (int j : _jugadorsMaquina)
        {
            Gui.print(String.valueOf(j));
        }*/
   }

    ///@pre  Nom del fitxer d'entrada
    ///@post  Guarda el nom del fitxer d'entrada i crida el mètode per llegir el fitxer
   public static void nomFitxer(String text) throws FileNotFoundException {
        Gui.print("Fitxer '"+text+"' carregant...");
        File f = new File(text);
        fitxer = f;
        llegirFitxer();
    }

    ///@pre  ---
    ///@post  Retorna una llista de quins dels jugadors són controlats per màquina
    public static ArrayList<Jugador> getJugadors() {
        return _jugadors;
    }

    ///@pre  ---
    ///@post  Retorna la fitxa inicial
    public static Fitxa getInicial() {
        return _inicial;
    }

    ///@pre  ---
    ///@post  Cert si hi ha camperols
    public static boolean isCamperols() {
        return _camperols;
    }

    ///@pre
    ///@post  Cert si la lectura s'ha realitzat satisfactòriament
    public static boolean lecturaCorrecta() {
        return fitxerOK;
    }

    ///@pre  ---
    ///@post  Retorna la baralla
    public static Baralla getBaralla() {//TODO getBaralla no funciona ARREGLAR!
        return baralla;
    }
}
