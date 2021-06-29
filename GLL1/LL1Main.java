/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll1;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author srr_s
 */
public class LL1Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner leer = new Scanner(System.in);
        
                Ll1 ll1 = new Ll1(test());
   
                ll1.resultados();
          while (true) {
                    leer = new Scanner(System.in);

                    System.out.println("Cadena a verificar:");
                    String cadena = leer.nextLine();

                    ll1.verificarCadena(cadena);

                    System.out.println("¿Verificar otra cadena?\n1. Si\n2. No");
                    int opCad = leer.nextInt();
                    if (opCad == 2) {
                        break;
                    }
                }
        }

    

    private static String test() {
        return "E->E+T\n"
                + "E->T\n"
                + "T->T*F\n"
                + "T->F\n"
                + "F->i\n"
                + "F->(E)";
    }

}
