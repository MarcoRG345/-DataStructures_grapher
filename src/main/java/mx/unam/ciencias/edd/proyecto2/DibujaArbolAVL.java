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
 * Clase para dibujar un árbol AVL.
 */
public class DibujaArbolAVL extends DibujaArbol {

    /**
     * Clase interna para dibujar vértices AVL.
     */
    private class DibujaVerticeAVL extends DibujaVertice {

        /**
         * Constructor que recibe un vértice y crea su representación gráfica.
         * @param vertice el vértice a representar gráficamente.
         */
        public DibujaVerticeAVL(VerticeArbolBinario<Integer> vertice) {
            super(vertice);
        }

        /**
         * Calcula el factor de balance del vértice AVL.
         * @return el factor de balance del vértice AVL.
         */
        private int balance() {
            if (vertice.hayIzquierdo() && vertice.hayDerecho())
                return (vertice.izquierdo().altura() - vertice.derecho().altura());
            else {
                if (vertice.hayIzquierdo() && !vertice.hayDerecho())
                    return (vertice.izquierdo().altura() - (-1));
                else if (!vertice.hayIzquierdo() && vertice.hayDerecho())
                    return (-1 - vertice.derecho().altura());
            }
            return 0;
        }

        /**
         * Genera la representación en SVG del vértice AVL, incluyendo el elemento
         * y el factor de balance.
         * @param elemento el elemento del vértice.
         * @param font el tamaño de fuente.
         * @param centro_x la coordenada x del centro del vértice.
         * @param centro_y la coordenada y del centro del vértice.
         * @param color el color del texto.
         * @return la representación en SVG del vértice AVL.
         */
        @Override
        public String insertaElemento(int elemento, int font, int centro_x, int centro_y, String color) {
            String texto = " <text fill=\"" + color + "\" font-family=\"sans-serif\" text-anchor=\"middle\"";
            texto += " x=\"" + centro_x + "\" y=\"" + centro_y + "\" font-size=\"" + font + "\" >" + elemento + "</text>\n";
            String balanceAltura = " <text fill=\"" + color + "\" font-family=\"sans-serif\" text-anchor=\"middle\"";
            balanceAltura += " x=\"" + (centro_x) + "\" y=\"" + (centro_y - 5) + "\" font-size=\"" + font + "\" >";
            balanceAltura += "(" + vertice.altura() + "/" + balance() + ")" + "</text>\n";
            texto += balanceAltura;
            return texto;
        }
    }

    /** El árbol AVL. */
    private ArbolAVL<Integer> arbol;

    /** Constructor para dibujar un árbol AVL. */
    public DibujaArbolAVL() {
        arbol = new ArbolAVL<>();
    }

    @Override
    public void almacena(int entero) {
        arbol.agrega(entero);
    }

    @Override
    public String formatoSVG() {
        setElementos(arbol.getElementos());
        String a = "<svg width=\"" + getAncho() + "\" height=\"" + getAlto() + "\"" + ">\n";
        a += dibuja(arbol);
        a += "</svg>";
        return a;
    }

    @Override
    public String dibuja(ArbolBinario<Integer> arbol) {
        String cadena = "";
        EsqueletoVertice v =  darTipo(arbol.raiz());
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
            DibujaVerticeAVL d = (DibujaVerticeAVL)q.saca();
            cadena += d.dibujaVertice(d.getCentro_x(), d.getCentro_y(), radio, "white", "black");
            cadena += d.insertaElemento(d.getEntero(), radio, d.getCentro_x(), d.getCentro_y(), "black");
        }
        return cadena;
    }

    @Override
    public EsqueletoVertice darTipo(VerticeArbolBinario<Integer> vertice) {
        return new DibujaVerticeAVL(vertice);
    }
}
