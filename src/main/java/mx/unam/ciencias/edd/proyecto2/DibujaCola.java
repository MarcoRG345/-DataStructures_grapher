package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.Color;  
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.MeteSaca;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Pila;
import mx.unam.ciencias.edd.VerticeGrafica;

/**
 * Clase para dibujar una cola.
 */
public class DibujaCola extends DibujaLista {

    /**
     * Clase interna para dibujar nodos de la cola.
     */
    private class DibujaNodoCola extends Nodo {

        /**
         * Constructor para dibujar un nodo de cola con un elemento.
         * @param elemento el elemento del nodo.
         */
        public DibujaNodoCola(int elemento) {
            super(elemento);
        }

        /** Constructor para un nodo de cola vacío. */
        DibujaNodoCola() {
            super();
        }
    }
    
    /** La cola para almacenar nodos. */
    private Cola<DibujaNodoCola> cola;

    /** El número total de elementos en la cola. */
    private int total;
    
    /** Constructor para dibujar la cola. */
    public DibujaCola() {
        cola = new Cola<>();
    }

    @Override
    public void almacena(int entero) {
        cola.mete(new DibujaNodoCola(entero));
        total++;
    } 

    /**
     * Arma el contenido SVG de la cola.
     * @return el contenido SVG de la cola.
     */
    private String armaCola() {
        String dibujaCola = "";
        DibujaNodoCola siguiente = new DibujaNodoCola();
        dibujaCola = siguiente.nodoVacio();
        DibujaNodoCola actual = cola.saca();
        actual.setCoordX(25);
        dibujaCola += actual.toString();
        dibujaCola += actual.linea(0, 25 ,15, 15);
        dibujaCola += actual.linea(0, 3, 15, 13);
        dibujaCola += actual.linea(0, 3, 15, 17);
        while (!cola.esVacia()) {
            siguiente = cola.saca();
            siguiente.setCoordX(actual.getCoordX() + actual.getAnchura());
            dibujaCola += siguiente.toString();
            actual = siguiente;
        }
        if (siguiente != null) {
            setDimensiones(siguiente.getCoordX() + siguiente.getAnchura() + siguiente.getAnchuraVacio());
            int origen_x = siguiente.getCoordX() + siguiente.getAnchura();
            dibujaCola += siguiente.linea(origen_x, (origen_x + siguiente.getAnchuraVacio()), 15, 15);
            dibujaCola += siguiente.linea(origen_x, origen_x + 3, 15, 13);
            dibujaCola += siguiente.linea(origen_x, origen_x + 3, 15, 17);
        }
        return dibujaCola;
    }
    
    @Override
    public String formatoSVG() {
        setElementos(total);
        int dimension = (getDimensiones() == 0 ? 100 : getDimensiones());
        String contenido = armaCola();
        String cadena = "<svg width=\"" + dimension + "\" height=\"" + 25 + "\">\n";
        cadena += contenido;
        cadena += "</svg>";
        return cadena;
    }
}
