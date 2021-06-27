/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subconjuntos;

import java.util.HashSet;

/**
 *
 * @author srr_s
 */
public class SubConjuntos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

 AFDConstructor AFD = new AFDConstructor();
Automata afn_prueba= new Automata();        
       
        
        Estado a0 = new Estado(0);
        afn_prueba.setEstadoInicial(a0);
        afn_prueba.addEstados(a0);
        
        Estado a1 = new Estado(1);
        Estado a2 = new Estado(2);
        Estado a3 = new Estado(3);
        Estado a4 = new Estado(4);
        Estado a5 = new Estado(5);
        Estado a6 = new Estado(6);
        Estado a7 = new Estado(7);
        Estado a8 = new Estado(8);
        Estado a9 = new Estado(9);
        Estado a10 = new Estado(10);
        Estado a11 = new Estado(11);
        
        
        a0.setTransiciones(new Transicion(a0, a1,"a"));
        a1.setTransiciones(new Transicion(a1, a2,"E"));
        a1.setTransiciones(new Transicion(a1, a8,"E"));
        a2.setTransiciones(new Transicion(a2, a3,"E"));
        a2.setTransiciones(new Transicion(a2, a5,"E"));
        a3.setTransiciones(new Transicion(a3, a4,"a"));
        a4.setTransiciones(new Transicion(a4, a7,"E"));
        a5.setTransiciones(new Transicion(a5, a6,"b"));
        a6.setTransiciones(new Transicion(a6, a7,"E"));
        a7.setTransiciones(new Transicion(a7, a2,"E"));
        a7.setTransiciones(new Transicion(a7, a8,"E"));
        a8.setTransiciones(new Transicion(a8, a9,"a"));
        a9.setTransiciones(new Transicion(a9, a10,"b"));
        a10.setTransiciones(new Transicion(a10, a11,"b"));
        
        afn_prueba.addEstados(a1);
        afn_prueba.addEstados(a2);
        afn_prueba.addEstados(a3);
        afn_prueba.addEstados(a4);
        afn_prueba.addEstados(a5);
        afn_prueba.addEstados(a6);
        afn_prueba.addEstados(a7);
        afn_prueba.addEstados(a8);
        afn_prueba.addEstados(a9);
        afn_prueba.addEstados(a10);
        afn_prueba.addEstados(a11);
  
        afn_prueba.addEstadosAceptacion(a11);
        
        HashSet alfabeto = new HashSet();
        alfabeto.add("a");
        alfabeto.add("b");
        afn_prueba.setAlfabeto(alfabeto);


//convertir el AFN a AFD
        AFD.conversionAFN(afn_prueba);
        //obtener el AFD resultante
        Automata afd_result = AFD.getAfd();
       System.out.println(AFD);


    }
    
}
