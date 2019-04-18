import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        System.out.println("Entra Fitxa");
        Scanner teclat = new Scanner(System.in);

        ArrayList<Fitxa> prova = new ArrayList<Fitxa>(5);

        while(teclat.hasNext()){
            Fitxa f = new Fitxa(teclat.next());
            prova.add(f);
            System.out.println("Posar seguidor s/n");
            if(teclat.next().charAt(0) == 's'){
                int ultim = prova.size();
                System.out.println(ultim +"Regio");
                char regio = teclat.next().charAt(0);
                System.out.println("Nom jugador");
                String jugador = teclat.next();
                prova.get(ultim-1).assignar_seguidor(regio,jugador);
            }
            else{
                System.out.println("No hi ha seguidor");
            }
            System.out.println("Nova fitxa");
        }

        for(int i=0; i<prova.size(); i++){
            try {
                prova.get(i).mostrar_regio_c();
                prova.get(i).mostrar_regio_n();
                prova.get(i).mostrar_regio_e();
                prova.get(i).mostrar_regio_s();
                prova.get(i).mostrar_regio_o();
            }
            catch(NullPointerException e){
                System.out.println("S'ha de crear la fitxa" + i);
            }
        }
    }
}
