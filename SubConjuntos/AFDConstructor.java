package subconjuntos;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Clase que construye un AFD a partir de un AFN
 * @author Pablo
 */
public class AFDConstructor {
    
    private Automata afd;
    private Automata afdDirecto;
    private final Simulador simulador;
    private final HashMap resultadoFollowPos;
  
    
    public AFDConstructor(){
        this.resultadoFollowPos = new HashMap();
        simulador = new Simulador();
        afd = new Automata();
    }
    
    
    /**
     * Conversion de un automata AFN a uno AFD por el
     * metodo de subconjuntos
     * @param afn AFN
     */
    public void conversionAFN(Automata afn){
        //se crea una estructura vacia
        Automata automata = new Automata();
        //se utiliza una cola como la estructura para guardar los subconjuntos a analizar
        Queue<HashSet<Estado>> cola = new LinkedList();
        //se crea un nuevo estado inicial
        Estado inicial = new Estado(0);
        automata.setEstadoInicial(inicial);
        automata.addEstados(inicial);
       

        //el algoritmo empieza con el e-Closure del estado inicial del AFN
        HashSet<Estado> array_inicial = simulador.eClosure(afn.getEstadoInicial());
        //si el primer e-closure contiene estados de aceptacion hay que agregarlo
        for (Estado aceptacion:afn.getEstadosAceptacion()){
            if (array_inicial.contains(aceptacion))
                automata.addEstadosAceptacion(inicial);
        }
        
        //lo agregamos a la pila
        cola.add(array_inicial);
        //variable temporal para guardar el resultado todos los subconjuntos creados
        ArrayList<HashSet<Estado>> temporal = new ArrayList();
       //se utilizan esetos indices para saber el estado actuales y anterior
       int indexEstadoInicio = 0;
       while (!cola.isEmpty()){
           //actual subconjunto
            HashSet<Estado> actual = cola.poll();
            //se recorre el subconjunto con cada simbolo del alfabeto del AFN
            for (Object simbolo: afn.getAlfabeto())
            {
                //se realiza el move con el subconjunto
                HashSet<Estado> move_result = simulador.move(actual, (String) simbolo);

                HashSet<Estado> resultado = new HashSet();
                //e-Closure con cada estado del resultado del move y 
                //se guarda en un solo array (merge)
                for (Estado e : move_result) 
                {
                    resultado.addAll(simulador.eClosure(e));
                }

                Estado anterior = (Estado) automata.getEstados().get(indexEstadoInicio);
                /*Si el subconjunto ya fue creado una vez, solo se agregan
                transiciones al automata*/
                if (temporal.contains(resultado))
                {
                    ArrayList<Estado> array_viejo = automata.getEstados();
                    Estado estado_viejo = anterior;
                    //se busca el estado correspondiente y se le suma el offset
                    Estado estado_siguiente = array_viejo.get(temporal.indexOf(resultado)+1);
                    estado_viejo.setTransiciones(new Transicion(estado_viejo,estado_siguiente,simbolo));

                }
                //si el subconjunto no existe, se crea un nuevo estado
                else
                {
                    temporal.add(resultado);
                    cola.add(resultado);

                    Estado nuevo = new Estado(temporal.indexOf(resultado)+1);
                    anterior.setTransiciones(new Transicion(anterior,nuevo,simbolo));
                    automata.addEstados(nuevo);
                    //se verifica si el estado tiene que ser de aceptacion
                    for (Estado aceptacion:afn.getEstadosAceptacion()){
                        if (resultado.contains(aceptacion))
                            automata.addEstadosAceptacion(nuevo);
                    }
                }
               

            }
            indexEstadoInicio++;

           }
        
        this.afd = automata;
        //metodo para definir el alfabeto, se copia el del afn
        definirAlfabeto(afn);
        this.afd.setTipo("AFD");
        System.out.println(afd);
        
    }
    
    
    /**
     * Copiar el alfabeto del AFN al AFD
     * @param afn 
     */
    private void definirAlfabeto(Automata afn){
        //System.out.println("ALFABETO");
        //System.out.println(afn.getAlfabeto());
        this.afd.setAlfabeto(afn.getAlfabeto());
    }
    
    
       
    
     /**
     * Retornar el AFD creado
     * @return Autoamta generado
     */
    public Automata getAfd() {
        return afd;
    }
}
 