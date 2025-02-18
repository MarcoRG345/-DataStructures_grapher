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
 * Clase abstracta que representa un esqueleto para dibujar un vértice.
 */
public abstract class EsqueletoVertice {

    private int centro_x;
    private int centro_y;
    private int radio;
    private int elemento;
    protected VerticeArbolBinario<Integer> vertice;

    /**
     * Dibuja el vértice en formato SVG.
     * @param centro_x la coordenada X del centro del círculo.
     * @param centro_y la coordenada Y del centro del círculo.
     * @param radio el radio del círculo.
     * @param color el color de relleno del círculo.
     * @param color_borde el color del borde del círculo.
     * @return la representación SVG del vértice.
     */
    public String dibujaVertice(int centro_x, int centro_y, int radio, String color, String color_borde) {
        String c = " <circle cx=\"" + centro_x + "\" cy=\"" + centro_y + "\" r=\"" + radio + "\" fill=\"" + color + "\" stroke=";
        c += " \"" + color_borde + "\" stroke-width=\"0.5\" opacity=\"1\"/>\n";
        return c;
    }

    /**
     * Devuelve la coordenada X del centro del vértice.
     * @return la coordenada X del centro del vértice.
     */
    public int getCentro_x() {
        return centro_x;
    }

    /**
     * Establece la coordenada X del centro del vértice.
     * @param centro_x la coordenada X a establecer.
     */
    public void setCentro_x(int centro_x) {
        this.centro_x = centro_x;
    }

    /**
     * Devuelve la coordenada Y del centro del vértice.
     * @return la coordenada Y del centro del vértice.
     */
    public int getCentro_y() {
        return centro_y;
    }

    /**
     * Establece la coordenada Y del centro del vértice.
     * @param centro_y la coordenada Y a establecer.
     */
    public void setCentro_y(int centro_y) {
        this.centro_y = centro_y;
    }

    /**
     * Devuelve el entero almacenado en el vértice.
     * @return el entero almacenado en el vértice.
     */
    public int getEntero() {
        return vertice.get();
    }

    /**
     * Establece el radio del círculo que representa al vértice.
     * @param radio el radio del círculo.
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /**
     * Inserta el elemento en el vértice.
     * @param elemento el elemento a insertar.
     * @param font el tamaño de la fuente del texto.
     * @param centro_x la coordenada X del centro del texto.
     * @param centro_y la coordenada Y del centro del texto.
     * @param color el color del texto.
     * @return la representación SVG del elemento insertado en el vértice.
     */
    public String insertaElemento(int elemento, int font, int centro_x, int centro_y, String color) {
        String texto = " <text fill=\"" + color + "\" font-family=\"sans-serif\" text-anchor=\"middle\"";
        texto += " x=\"" + centro_x + "\" y=\"" + centro_y + "\" font-size=\"" + font + "\">" + elemento + "</text>\n";
        return texto;
    }
}

