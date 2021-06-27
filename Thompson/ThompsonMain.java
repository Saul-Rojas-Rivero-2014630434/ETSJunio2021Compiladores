/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson;

import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author srr_s
 */
public class ThompsonMain {

 public static void main(String[] args) {
     
     String regex = JOptionPane.showInputDialog(
           null,
          "ingrese la expresión regular, el string epsilon es: " + 'E',
           "(a|b)*abb");  // el icono sera un iterrogante
          

            
                   
       String lenguaje = regex;
        Scanner teclado = new Scanner(System.in);
        ExpRegConvertidor convert = new ExpRegConvertidor();
        try{
            
           regex = convert.infixToPostfix(regex);
            
        }catch(Exception e){
            System.out.println("Expresión mal ingresada");
        }
     AFNConstructor ThomsonAlgorithim = new AFNConstructor(regex);
        //aplicar el algoritmo de thomson para crear el automata
        ThomsonAlgorithim.construct();
        
        System.out.println(regex);
        Automata afn_result = ThomsonAlgorithim.getAfn();
        System.out.println(afn_result);
                try {
            PrintWriter writer = new PrintWriter("C:\\Users\\srr_s\\OneDrive\\Documentos\\NetBeansProjects\\Thompson\\Afn.txt", "UTF-8");
            writer.println(afn_result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
 
 }
 }
    
}
