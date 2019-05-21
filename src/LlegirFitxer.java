import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

///@class LlegirFitxer

///@brief Classe encarregada de llegir el fitxer i retornar la informació necessària al projecte per tal de la realització
///de la partida

public class LlegirFitxer {

    private static File fitxer;///<Fitxer d'entrada
    private static int _nJugadors;///<Nombre de Jugador s
    private static ArrayList<Jugador> _jugadors;///<Llista de Jugador s
    private static Baralla baralla;///<Baralla de la partida
    private static Fitxa _inicial;///<Fitxa inicial utilitzada
    private static boolean _camperols;///<Retorna true si existeixen camperols
    private static boolean fitxerOK = false;///<Retorna true si ha llegit correctament el fitxer

    ///@pre --
    ///@post lectura completa del fitxer
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

    ///@pre sscanner del fitxer d'entrada
    ///@post llegeix i guarda la fitxa inicial i si hi ha camperols
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

    ///@pre scanner del fitxer d'entrada
    ///@post llegeix i guarda les diferents fitxes
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

    ///@pre scanner del fitxer d'entrada
    ///@post llegeix i guarda el nombre de jugadors i quins d'aquests són màquina
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

    ///@pre nom del fitxer d'entrada
    ///@post guarda el nom del fitxer d'entrada i crida el mètode per llegir el fitxer
   public static void nomFitxer(String text) throws FileNotFoundException {
        Gui.print("Fitxer '"+text+"' carregant...");
        File f = new File(text);
        fitxer = f;
        llegirFitxer();
    }

    ///@pre --
    ///@post retorna una llista de quins dels jugadors són controlats per màquina
    public static ArrayList<Jugador> getJugadors() {
        return _jugadors;
    }

    ///@pre --
    ///@post retorna la fitxa inicial
    public static Fitxa getInicial() {
        return _inicial;
    }

    ///@pre --
    ///@post cert si hi ha camperols
    public static boolean isCamperols() {
        return _camperols;
    }

    ///@pre --
    ///@post cert si la lectura s'ha realitzat satisfactòriament
    public static boolean lecturaCorrecta() {
        return fitxerOK;
    }

    ///@pre  --
    ///@post retorna la baralla
    public static Baralla getBaralla() {//TODO getBaralla no funciona ARREGLAR!
        return baralla;
    }
}
