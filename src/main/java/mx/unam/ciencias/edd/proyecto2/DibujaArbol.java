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
 * Clase para dibujar un árbol binario ordenado en formato SVG.
 */
public class DibujaArbol extends Contenido {

    /**
     * Clase interna para dibujar los vértices del árbol.
     */
    protected class DibujaVertice extends EsqueletoVertice {
        
        /**
         * Crea un nuevo vértice para dibujar.
         * @param vertice el vértice del árbol binario.
         */
        public DibujaVertice(VerticeArbolBinario<Integer> vertice) {
            this.vertice = vertice;
        }

        /**
         * Constructor por omisión.
         */
        public DibujaVertice() {}
    }

    private ArbolBinarioOrdenado<Integer> arbol;

    /**
     * Constructor. Inicializa el árbol binario ordenado.
     */
    public DibujaArbol() {
        arbol = new ArbolBinarioOrdenado<>();
    }

    /**
     * Almacena un entero en el árbol binario.
     * @param entero el entero a almacenar.
     */
    @Override public void almacena(int entero) {
        arbol.agrega(entero);
    }

    /**
     * Devuelve el formato SVG del árbol binario.
     * @return el formato SVG del árbol binario.
     */
    @Override public String formatoSVG() {
        setElementos(arbol.getElementos());
        String a = "<svg width= \"" + getAncho() + "\" height= \"" + getAlto() + "\""+">\n";
        a += dibuja(arbol);
        a += "</svg>";
        return a;
    }

    /**
     * Devuelve el ancho del árbol binario.
     * @return el ancho del árbol binario.
     */
    public int getAncho() {
        return getAlto() * 3;
    }

    /**
     * Devuelve la altura del árbol binario.
     * @return la altura del árbol binario.
     */
    public int getAlto() {
        return arbol.altura() + 100;
    }

    /**
     * Dibuja el árbol binario en formato SVG.
     * @param arbol el árbol binario a dibujar.
     * @return la representación SVG del árbol binario.
     */
    public String dibuja(ArbolBinario<Integer> arbol) {
        String cadena = "";
        EsqueletoVertice v = darTipo(arbol.raiz());
        Cola<EsqueletoVertice> q = new Cola<>();
        Cola<String> lineas = new Cola<>();
        int radio = 4;
        if (arbol.getElementos() >= 30 && arbol.getElementos() < 50)
            radio = 3;
        else if (arbol.getElementos() > 50)
            radio = (getAncho()/arbol.getElementos())/2;
        else if (radio == 0)
            radio = 1;
        v.setCentro_x(getAncho()/2);
        v.setCentro_y(radio + 10);
        ubicaCoordenadas(v, arbol.raiz(), v.getCentro_x()/2, q, lineas);
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

    /**
     * Ubica las coordenadas de los vértices del árbol.
     * @param w el vértice a ubicar.
     * @param v el vértice del árbol binario.
     * @param distancia la distancia entre vértices.
     * @param q una cola auxiliar para almacenar vértices.
     * @param q2 una cola auxiliar para almacenar líneas SVG.
     */
    protected  void ubicaCoordenadas(EsqueletoVertice w, VerticeArbolBinario<Integer> v, int distancia, Cola<EsqueletoVertice> q, Cola<String> q2) {
        int radio = 3;
        if (v.hayDerecho()) {
            EsqueletoVertice d = darTipo(v.derecho());
            d.setCentro_x(w.getCentro_x() + distancia);
            d.setCentro_y(w.getCentro_y() + 10);
            lineas(w.getCentro_x(), d.getCentro_x(), w.getCentro_y(), d.getCentro_y(), q2);
            q.mete(d);
            ubicaCoordenadas(d, v.derecho(), distancia/2, q, q2);
        }
        if (v.hayIzquierdo()) {
            EsqueletoVertice d = darTipo(v.izquierdo());
            d.setCentro_x(w.getCentro_x() - distancia);
            d.setCentro_y(w.getCentro_y() + 10);
            lineas(w.getCentro_x(), d.getCentro_x(), w.getCentro_y(), d.getCentro_y(), q2);
            q.mete(d);
            ubicaCoordenadas(d, v.izquierdo(), distancia/2, q, q2);
        }
    }

    /**
     * Dibuja una línea SVG entre dos puntos.
     * @param origen_x la coordenada x del origen.
     * @param destino_x la coordenada x del destino.
     * @param origen_y la coordenada y del origen.
     * @param destino_y la coordenada y del destino.
     * @param q la cola donde se almacenan las líneas SVG.
     */
    private void lineas(int origen_x, int destino_x, int origen_y, int destino_y, Cola<String> q) {
        String linea = " <line x1= \""+origen_x+"\""+" y1= \""+origen_y+"\"";
        linea += " x2= \""+destino_x+"\" y2=\""+destino_y+"\" stroke-width= \"0.3\" stroke= \"black\"/>\n";
        q.mete(linea);
    }
    
    /**
     * Devuelve el tipo de vértice para dibujar.
     * @param vertice el vértice del árbol binario.
     * @return el tipo de vértice para dibujar.
     */
    public EsqueletoVertice darTipo(VerticeArbolBinario<Integer> vertice) {
        return new DibujaVertice(vertice);
    }
}
