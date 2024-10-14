package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos 
    private Nodo primero;
    private Nodo punteroFinal;
    private Nodo punteroInicial;

    private class Nodo {
        // Completar
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T v){
            valor = v;
        }
    }
    // Constructor
    public ListaEnlazada() {
        this.primero = null;
        // this.primero.anterior = null;
        // this.primero.siguiente = null;
        this.punteroInicial = this.primero;
        // this.punteroFinal = this.primero.siguiente; // Quizas este mal este cosntructor
        this.punteroFinal = this.primero;
    }

    public int longitud() {
        int contador = 0;
        Nodo otro = this.primero;
        while(otro != null){
            otro = otro.siguiente;
            contador++;
        }
        return contador;
    }

    public void agregarAdelante(T elem) {
        // Creo nodo nuevo, asigno el valor

        Nodo nuevo = new Nodo(elem);
        this.punteroInicial = nuevo;
        if (this.primero == null){//caso donde no hay elementos
            nuevo.siguiente = this.primero;
            //nuevo.anterior = this.primero;
            nuevo.anterior = null; // Sera necesario decir explicitamente que el anterior es null??
            this.primero = nuevo;
            this.punteroFinal = this.primero; //El primer elemento sera mi puntero fINAL
                                              //ya que a medida que se van metiendo los elems el primer elemento anadido va a ser el ultimo
        }
        else{//caso donde hay por lo menos un elemm=ento
            nuevo.siguiente = this.primero;
            this.primero = nuevo;
            //this.punteroFinal = nuevo;

            Nodo actual = this.primero;
            actual = actual.siguiente;
            actual.anterior= this.primero;
        }
        
    
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (this.primero == null){
            this.primero = nuevo;
            this.punteroFinal = nuevo;
        }else{
            Nodo actual = this.primero; //Metavariable para iterar en la lista
            while(actual.siguiente!=null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            this.punteroFinal = nuevo;
        }

    }

    public T obtener(int i) {
        int contador = 0;
        Nodo actual = this.primero;
        while (contador < i ) {
            actual = actual.siguiente;
            contador++;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        // if i == longitud - 1 ; Hacer cambios al punteroFinal, el punteroFinal apuntara
        //al penultimo nodo
        Nodo actual = this.primero;
        Nodo previo = this.primero;
        //Ubicamos nuestro nodo previo al que vamos a eliminar
        for(int j = 0; j < i ; j++){
            previo = actual;
            actual = actual.siguiente;
        }
        if(i==0){
            this.primero = actual.siguiente;
            this.punteroInicial = actual.siguiente; //o this.primero
        }
        else{
            //Significa que estamos parados en el ultimo elemento de la lista
            //Apuntamos al nodo anterior del ultimo
            Nodo ultimo = actual;
            Nodo chequeo = actual;
            if(chequeo.siguiente == null){
                this.punteroFinal = ultimo.anterior; 
            }
            Nodo enlazado = actual.siguiente;
            previo.siguiente = enlazado; //'Eliminacion' de nodo
            enlazado.anterior = previo; 
        }
    }
    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
