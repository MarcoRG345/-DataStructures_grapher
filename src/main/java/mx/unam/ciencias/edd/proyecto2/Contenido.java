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
 * Clase abstracta que representa un contenido que se puede dibujar.
 */
public abstract class Contenido {
    
    private int elementos;
    private int dimensiones;
    
    /**
     * Devuelve el número de elementos del contenido.
     * @return el número de elementos del contenido.
     */
    public int get() {
        return elementos;
    }

    /**
     * Establece el número de elementos del contenido.
     * @param elementos el número de elementos a establecer.
     */
    public void setElementos(int elementos) {
        this.elementos = elementos;
    }

    /**
     * Devuelve las dimensiones del contenido.
     * @return las dimensiones del contenido.
     */
    public int getDimensiones() {
        return dimensiones;
    }

    /**
     * Establece las dimensiones del contenido.
     * @param dimensiones las dimensiones a establecer.
     */
    public void setDimensiones(int dimensiones) {
        this.dimensiones = dimensiones;
    }

    /**
     * Genera la representación SVG del contenido.
     * @return la representación SVG del contenido.
     */
    public abstract String formatoSVG();
    
    /**
     * Almacena un entero en el contenido.
     * @param entero el entero a almacenar.
     */
    public abstract void almacena(int entero);
}

