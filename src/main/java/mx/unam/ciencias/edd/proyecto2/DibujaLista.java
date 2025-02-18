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
 * Clase para dibujar una lista.
 */
public class DibujaLista extends Contenido {

    /**
     * Clase interna para dibujar nodos de la lista.
     */
    protected class Nodo extends DibujaNodo {

        /**
         * Constructor para dibujar un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(int elemento) {
            this.elemento = elemento;
            altura = 25;
            anchura = 50;
        }

        /** Constructor para dibujar un nodo vacío. */
        public Nodo() {}

        /**
         * Dibuja un nodo vacío.
         * @return la representación en cadena del nodo vacío.
         */
        public String nodoVacio() {
            String nodo = " <rect stroke=\"white\" fill=\"white\"";
            nodo += " width=\"" + (anchura % 2 == 0 ? anchura / 2 : (anchura + 1) / 2) + "\"";
            nodo += " height=\"" + altura + "\" x=\"" + coordX + "\"/>\n";
            return nodo;
        }

        /**
         * Dibuja una línea entre dos nodos.
         * @param origen_x la coordenada x del origen de la línea.
         * @param destino_x la coordenada x del destino de la línea.
         * @param origen_y la coordenada y del origen de la línea.
         * @param destino_y la coordenada y del destino de la línea.
         * @return la representación en cadena de la línea.
         */
        public String linea(int origen_x, int destino_x, int origen_y, int destino_y) {
            String linea = " <line x1=\"" + origen_x + "\" y1=\"" + origen_y + "\"";
            linea += " x2=\"" + destino_x + "\" y2=\"" + destino_y + "\"";
            linea += " stroke=\"black\" stroke-width=\"1\"/>\n";
            return linea;
        }

        /**
         * Obtiene la anchura del nodo vacío.
         * @return la anchura del nodo vacío.
         */
        public int getAnchuraVacio() {
            return (anchura % 2 == 0 ? anchura / 2 : (anchura + 1) / 2);
        }

        @Override
        public String toString() {
            String cadena = " <rect stroke=\"black\" stroke-width=\"2\" fill=\"white\"";
            cadena += " width=\"" + anchura + "\" height=\"" + altura + "\" x=\"" + coordX + "\"/>\n";
            cadena += insertaElemento();
            return cadena;
        }

        @Override
        public String insertaElemento() {
            int centro_x = coordX + (anchura % 2 == 0 ? anchura / 2 : (anchura + 1) / 2);
            int centro_y = coordY + (altura % 2 == 0 ? altura / 2 : (altura + 1) / 2);
            String cadena = " <text x=\"" + centro_x + "\" y=\"" + centro_y + "\" font-family=\"sans-serif\"";
            cadena += " text-anchor=\"middle\" fill=\"black\" font-size=\"" + 12 + "\">" + elemento + "</text>\n";
            return cadena;
        }
    }

    /** Lista para almacenar los nodos */
    private Lista<Nodo> lista;

    /** Constructor para dibujar la lista */
    public DibujaLista() {
        lista = new Lista<Nodo>();
    }

    /**
     * Almacena un entero en la lista.
     * @param entero el entero a almacenar.
     */
    public void almacena(int entero) {
        lista.agrega(new Nodo(entero));
    }

    @Override
    public String formatoSVG() {
        setElementos(lista.getLongitud());
        String contenido = armaLista(0);
        String cadena = "<svg width=\"" + getDimensiones() + "\" height=\"" + lista.get(0).getAltura() + "\">\n";
        for (Nodo db : lista) {
            db.anchura = (getDimensiones() / (get() + (get() - 1)));
        }
        cadena += contenido;
        cadena += "</svg>";
        return cadena;
    }
    /**
     * Arma la lista en el lienzo.
     * @param indice el indice del elemento.
     * @return la cadena svg.
     */
    private String armaLista(int indice) {
        String cadena = "";
        if (indice < lista.getLongitud()) {
            cadena += lista.get(indice) + "\n";
            if ((indice + 1) < lista.getLongitud()) {
                lista.get(indice + 1).setCoordX(lista.get(indice).getCoordX() + lista.get(indice).getAnchura());
                cadena += lista.get(indice + 1).nodoVacio();
                int origen_x = lista.get(indice + 1).getCoordX();
                lista.get(indice + 1).setCoordX(lista.get(indice + 1).getCoordX() + lista.get(indice + 1).getAnchuraVacio());
                int destino_x = lista.get(indice + 1).getCoordX();
                Nodo db = new Nodo();
                cadena += db.linea(origen_x, destino_x, lista.get(indice).getAltura() / 2, lista.get(indice).getAltura() / 2);
                cadena += db.linea(origen_x, origen_x + 3, lista.get(indice).getAltura() / 2, (lista.get(indice).getAltura() / 2) - 2);
                cadena += db.linea(destino_x, destino_x - 3, lista.get(indice).getAltura() / 2, (lista.get(indice).getAltura() / 2) - 2);
                cadena += db.linea(origen_x, origen_x + 3, lista.get(indice).getAltura() / 2, (lista.get(indice).getAltura() / 2) + 2);
                cadena += db.linea(destino_x, destino_x - 3, lista.get(indice).getAltura() / 2, (lista.get(indice).getAltura() / 2) + 2);
            }
            cadena += armaLista(indice + 1);
        }
        if ((indice) >= lista.getLongitud()) {
            setDimensiones(lista.get(indice - 1).getCoordX() + lista.get(indice - 1).getAnchura());
        }
        return cadena;
    }
}



