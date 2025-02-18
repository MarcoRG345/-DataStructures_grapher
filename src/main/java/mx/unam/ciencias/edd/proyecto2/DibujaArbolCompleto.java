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
 * Clase para dibujar un árbol binario completo.
 */
public class DibujaArbolCompleto extends DibujaArbol {

    /**
     * Clase interna para dibujar vértices completos.
     */
    private class DibujaVerticeCompleto extends DibujaVertice {

        /**
         * Constructor que recibe un vértice y crea su representación gráfica.
         * @param vertice el vértice a representar gráficamente.
         */
        public DibujaVerticeCompleto(VerticeArbolBinario<Integer> vertice) {
            super(vertice);
        }
    }

    /** El árbol binario completo. */
    private ArbolBinarioCompleto<Integer> arbol;

    /** Constructor para dibujar un árbol binario completo. */
    public DibujaArbolCompleto() {
        arbol = new ArbolBinarioCompleto<>();
    }

    @Override
    public void almacena(int entero) {
        arbol.agrega(entero);
    }

    @Override
    public String formatoSVG() {
        setElementos(arbol.getElementos());
        String a = "<svg width=\"" + getAncho() + "\" height=\"" + getAlto() + "\"" + ">\n";
        a += super.dibuja(arbol);
        a += "</svg>";
        return a;
    }

    /**
     * Método privado que dibuja el árbol binario completo.
     * @param arbol el árbol binario completo a dibujar.
     * @return la representación en SVG del árbol binario completo.
     */
    private String dibuja(ArbolBinarioCompleto<Integer> arbol) {
        String cadena = "";
        DibujaVerticeCompleto v = new DibujaVerticeCompleto(arbol.raiz());
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
        cadena += v.dibujaVertice(v.getCentro_x(), v.getCentro_y(), radio, "white", "black");
        cadena += v.insertaElemento(v.getEntero(), radio, v.getCentro_x(), v.getCentro_y(), "black");
        while (!q.esVacia()) {
            EsqueletoVertice d = q.saca();
            cadena += d.dibujaVertice(d.getCentro_x(), d.getCentro_y(), radio, "white", "black");
            cadena += d.insertaElemento(d.getEntero(), radio, d.getCentro_x(), d.getCentro_y(), "black");
        }
        return cadena;
    }
}
