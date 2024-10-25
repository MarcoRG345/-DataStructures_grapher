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
 * Clase abstracta para representar un nodo que se puede dibujar.
 */
public abstract class DibujaNodo {

    protected int elemento;
    protected int coordX;
    protected int coordY;
    protected int anchura;
    protected int altura;

    /**
     * Devuelve la coordenada X del nodo.
     * @return la coordenada X del nodo.
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Establece la coordenada X del nodo.
     * @param coordX la coordenada X a establecer.
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    
    /**
     * Devuelve la coordenada Y del nodo.
     * @return la coordenada Y del nodo.
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Establece la coordenada Y del nodo.
     * @param coordY la coordenada Y a establecer.
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * Inserta el elemento en el nodo.
     * @return la representación SVG del elemento insertado en el nodo.
     */
    public abstract String insertaElemento();

    /**
     * Devuelve la anchura del nodo.
     * @return la anchura del nodo.
     */
    public int getAnchura() {
        return anchura;
    }

    /**
     * Devuelve la altura del nodo.
     * @return la altura del nodo.
     */
    public int getAltura() {
        return altura;
    }    
}
