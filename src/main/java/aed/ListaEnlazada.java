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
                //*TODO: Falta agregar los punteros de atras, de cada Nodo*
        Nodo nuevo = new Nodo(elem);
        if (this.primero == null){
            this.primero = nuevo;
            this.punteroInicial = nuevo;
            this.punteroFinal = nuevo;
        }else{
            Nodo actual = this.primero; //Metavariable para iterar en la lista
            while(actual.siguiente!=null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.anterior = actual; //asignamos a cada nodo puntero al anterior
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
    //Para metodo eliminar se podria rehacer desde otra perspectiva, usando punteros inicio y punteros FINAL PARA LA ELIMINACION DE LAS PUNTAS 
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
                Nodo enlazado = actual.siguiente;
                previo.siguiente = enlazado; 
                //caso especial porque cuando elimino ultimo nodo, el siguiente es null y null.anterior da error de NullpOINTER
            }
            else{
                Nodo enlazado = actual.siguiente;
                previo.siguiente = enlazado; //'Eliminacion' de nodo
                enlazado.anterior = previo; //El problema viene cuando eliminamos ultimo elemento, null apunta a previo
            }
            
        }
    }
    public void modificarPosicion(int indice, T elem) {
        if(indice==0){
            Nodo first = this.punteroInicial;
            first.valor = elem;
        }
        else{
            if (indice == this.longitud()-1){ //CChequea lo que hice con this
                Nodo last = this.punteroFinal;
                last.valor = elem;
            }
            else{
                Nodo actual = this.primero;
                for(int j = 0; j<indice;j++){
                    actual = actual.siguiente;
                }
                actual.valor = elem;
            }
        }
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        //A veces solo hay que codear pensando que esta bien y luego sorprenderse por el resultado
        ListaEnlazada noAliasing = new ListaEnlazada<>();
        //Preguntar si esta bien implementado
        
        // this.primero = lista.primero;
        // this.punteroFinal = lista.punteroInicial;
        // this.punteroInicial = lista.punteroFinal;
        Nodo actual = lista.primero;
        //this.primero = actual;
        int contador = 0;
        int longitud = lista.longitud();
        while(contador<=longitud-1){//ESTA MAL THIS.PRIMERO ES IGUAL A NULL 
            
            noAliasing.agregarAtras(actual.valor);
            actual = actual.siguiente;
            contador++;
        }
        this.primero = noAliasing.primero;
        this.punteroFinal = noAliasing.punteroFinal;
        this.punteroInicial = noAliasing.punteroInicial;

    }
    
    @Override
    public String toString() {
        String str = new String();
        str = "[" ;
        int longitud = this.longitud();
        //Itero hasta el penultimo elemento para luego a mano poner el ultimo sin coma
        //[1,2,3] al termianr el loop [1, 2, ]
        for (int i=0; i<=longitud-2;i++){
            T valor = (T) this.obtener(i);
            str = str + valor + ", ";
            
        }
        str = str + this.obtener(longitud-1);

        return str + "]";
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        private int dedito;
        private int tamano;
        private Nodo recorrer;
        public ListaIterador(){
            this.dedito=0;
            int contador = 0;
            if (primero == null){
                contador = 0;
            }
            else{
                Nodo actual = primero;   
                while(actual!=null){
                    actual= actual.siguiente;
                    contador++;
                }
            }
            this.tamano = contador;  
            this.recorrer = primero;

        }
        public boolean haySiguiente() {
            // while (recorrer.siguiente!= null) {
            //     recorrer = recorrer.siguiente;
            // }
            // return recorrer != null;
            return dedito != tamano;
        }
        
        public boolean hayAnterior() {
            if (dedito == 0){
                return false;
            }
            else{
                return dedito != tamano;
            }

	        
        }

        public T siguiente() {
	        int i = dedito;
            // int contador = 0;
            int contador = dedito;
            while (contador < i && recorrer!= null) {
                recorrer = recorrer.siguiente;
                contador++;
            }
            dedito++;
            
            return recorrer.valor;
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }
  
}
