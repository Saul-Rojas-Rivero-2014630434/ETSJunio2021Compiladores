/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subconjuntos;

/**
 *
 * @author srr_s
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * Clase para utilizar el metodo de move, e-closure y simulacion de
 * un automata
 * Incluye también un método para generar archivos DOT
 * @author Pablo
 */
public class Simulador {
    
    private String resultado;
    
    public Simulador(){
        
    }
    
    
    public HashSet<Estado> eClosure(Estado eClosureEstado){
        Stack<Estado> pilaClosure = new Stack();
        Estado actual = eClosureEstado;
        actual.getTransiciones();
        HashSet<Estado> resultado = new HashSet();
        
        pilaClosure.push(actual);
        while(!pilaClosure.isEmpty()){
            actual = pilaClosure.pop();
           
            for (Transicion t: (ArrayList<Transicion>)actual.getTransiciones()){
                
                if (t.getSimbolo().equals('E') && !resultado.contains(t.getFin())){
                    resultado.add(t.getFin());
                    pilaClosure.push(t.getFin());
                }
            }
        }
        resultado.add(eClosureEstado); //la operacion e-Closure debe tener el estado aplicado
        return resultado;
    }
    
    public HashSet<Estado> move(HashSet<Estado> estados, String simbolo){
       
        HashSet<Estado> alcanzados = new HashSet();
        Iterator<Estado> iterador = estados.iterator();
        while (iterador.hasNext()){
            
            for (Transicion t: (ArrayList<Transicion>)iterador.next().getTransiciones()){
                Estado siguiente = t.getFin();
                String simb = (String) t.getSimbolo();
                if (simb.equals(simbolo)){
                    alcanzados.add(siguiente);
                }
                
            }
            
        }
        return alcanzados;
        
    }
    
    public Estado move(Estado estado, String simbolo){
        ArrayList<Estado> alcanzados = new ArrayList();
           
        for (Transicion t: (ArrayList<Transicion>)estado.getTransiciones()){
            Estado siguiente = t.getFin();
            String simb = (String) t.getSimbolo();
            
            if (simb.equals(simbolo)&&!alcanzados.contains(siguiente)){
                alcanzados.add(siguiente);
            }

        }
       
        return alcanzados.get(0);
    }
    




   
}