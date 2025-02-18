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
 * Clase para dibujar una pila.
 */
public class DibujaPila extends DibujaLista {

    /**
     * Clase interna para dibujar nodos de la pila.
     */
    private class DibujaNodoPila extends DibujaNodo {

        /**
         * Constructor para dibujar un nodo de pila con un elemento.
         * @param elemento el elemento del nodo.
         */
        public DibujaNodoPila(int elemento) {
            this.elemento = elemento;
            altura = 25;
            anchura = 50;
        }

        @Override
        public String insertaElemento() {
            int centro_x = 25;
            int centro_y = coordY + (altura % 2 == 0 ? altura / 2 : (altura + 1) / 2);
            String cadena = " <text x=\"" + centro_x + "\" y=\"" + centro_y + "\" font-family=\"sans-serif\"";
            cadena += " text-anchor=\"middle\" fill=\"black\" font-size=\"" + 12 + "\"";
            cadena += ">" + elemento + "</text>\n";
            return cadena;
        }

        @Override
        public String toString() {
            String cadena = " <rect stroke=\"black\" stroke-width=\"2\" fill=\"white\"";
            cadena += " width=\"" + anchura + "\" height=\"" + altura + "\" y=\"" + coordY + "\"/>\n";
            cadena += insertaElemento();
            return cadena;
        }
    }

    /** La pila para almacenar nodos */
    private Pila<DibujaNodoPila> pila;

    /** Lista para almacenar nodos de la pila */
    private Lista<DibujaNodoPila> lista;

    /** Constructor para dibujar la pila */
    public DibujaPila() {
        pila = new Pila<>();
        lista = new Lista<>();
    }

    @Override
    public void almacena(int entero) {
        pila.mete(new DibujaNodoPila(entero));
        lista.agrega(new DibujaNodoPila(entero));
    }

    @Override
    public String formatoSVG() {
        setElementos(lista.getLongitud());
        String contenido = armaPila();
        String cadena = "<svg width=\"" + 50 + "\" height=\"" + (getDimensiones() + 50) + "\">\n";
        cadena += contenido;
        cadena += "</svg>";
        return cadena;
    }

    /**
     * Arma el contenido SVG de la pila.
     * @return el contenido SVG de la pila.
     */
    public String armaPila() {
        String contenido = "";
        DibujaNodoPila actual = pila.saca();
        DibujaNodoPila siguiente = null;
        actual.setCoordX(50);
        actual.setCoordY(25);
        contenido = actual.toString();
        while (!pila.esVacia()) {
            siguiente = pila.saca();
            siguiente.setCoordY(actual.getCoordY() + 25);
            siguiente.setCoordX(actual.getCoordX());
            contenido += siguiente.toString();
            actual = siguiente;
        }

        if (siguiente != null) {
            setDimensiones(siguiente.getCoordY() - siguiente.getAltura());
        } else {
            setDimensiones(actual.getCoordY() - actual.getAltura());
        }

        return contenido;
    }
}
