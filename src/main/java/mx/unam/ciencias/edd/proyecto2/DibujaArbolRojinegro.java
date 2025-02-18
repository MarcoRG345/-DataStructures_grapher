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
 * Clase para dibujar un árbol rojinegro en formato SVG.
 */
public class DibujaArbolRojinegro extends DibujaArbol {

    /**
     * Clase interna para dibujar los vértices rojinegros del árbol.
     */
    private class DibujaVerticeRojinegro extends DibujaVertice {

        /**
         * Constructor. Crea un nuevo vértice rojinegro.
         * @param vertice el vértice del árbol binario.
         */
        public DibujaVerticeRojinegro(VerticeArbolBinario<Integer> vertice) {
            super(vertice);
        }
    }

    private ArbolRojinegro<Integer> arbol;

    /**
     * Constructor. Inicializa el árbol rojinegro.
     */
    public DibujaArbolRojinegro() {
        arbol = new ArbolRojinegro<>();
    }

    /**
     * Almacena un entero en el árbol rojinegro.
     * @param entero el entero a almacenar.
     */
    @Override public void almacena(int entero) {
        arbol.agrega(entero);
    }

    /**
     * Devuelve el formato SVG del árbol rojinegro.
     * @return el formato SVG del árbol rojinegro.
     */
    @Override public String formatoSVG() {
        setElementos(arbol.getElementos());
        String a = "<svg width= \"" + getAncho() + "\" height= \"" + getAlto() + "\""+">\n";
        a += dibuja();
        a += "</svg>";
        return a;
    }

    /**
     * Dibuja el árbol rojinegro en formato SVG.
     * @return la representación SVG del árbol rojinegro.
     */
    public String dibuja() {
        String cadena = "";
        DibujaVertice v = new DibujaVerticeRojinegro(arbol.raiz());
        Cola<EsqueletoVertice> q = new Cola<>();
        Cola<String> lineas = new Cola<>();
        int radio = 4;
        if (arbol.getElementos() >= 30 && arbol.getElementos() < 50)
            radio = 3;
        else if (arbol.getElementos() > 50)
            radio = (getAncho() / arbol.getElementos()) / 2;
        else if (radio == 0)
            radio = 1;
        v.setCentro_x(getAncho() / 2);
        v.setCentro_y(radio + 10);
        super.ubicaCoordenadas(v, arbol.raiz(), v.getCentro_x() / 2, q, lineas);
        while (!lineas.esVacia()) {
            cadena += lineas.saca();
        }
        cadena += v.dibujaVertice(v.getCentro_x(), v.getCentro_y(), radio, "black", "black");
        cadena += v.insertaElemento(v.getEntero(), radio, v.getCentro_x(), v.getCentro_y(), "white");
        while (!q.esVacia()) {
            DibujaVertice d = (DibujaVertice)q.saca();
            if (arbol.getColor(d.vertice) == Color.ROJO) {
                cadena += d.dibujaVertice(d.getCentro_x(), d.getCentro_y(), radio, "red", "red");
            } else {
                cadena += d.dibujaVertice(d.getCentro_x(), d.getCentro_y(), radio, "black", "black");
            }
            cadena += d.insertaElemento(d.getEntero(), radio, d.getCentro_x(), d.getCentro_y(), "white");
        }
        return cadena;
    }
}

