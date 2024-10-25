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

public class DibujaMonticulo extends DibujaArbol{


    private ArbolBinarioCompleto<Integer> arbol;
    Lista<Integer> lista;
    
    public DibujaMonticulo(){
	arbol = new ArbolBinarioCompleto<>();
	lista = new Lista<>();
    }

    @Override public void almacena(int entero){
        lista.agrega(entero);
    }
    @Override public String formatoSVG(){
	setElementos(arbol.getElementos());
	String a = "<svg width= \""+getAncho()+"\" height= \""+(getAlto() + 50)+"\""+">\n";
	a += dibuja(arbol);
	a += "</svg>";
	return a;
    }
    @Override public String dibuja(ArbolBinario<Integer> arbol){
	String cadena = "";
	lista = Lista.mergeSort(lista);
	int[] monticulo = new int[lista.getElementos()]; 
	for (int entero : lista){
	    arbol.agrega(entero);
	}
	cadena += super.dibuja(arbol);
	return cadena;
    }
}
