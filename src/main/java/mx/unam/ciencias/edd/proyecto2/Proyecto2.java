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
import java.io.IOException;

public class Proyecto2{

    public static void main(String[] args){
		if (args.length == 0 && System.in != null && !inputDataFound()){
			System.out.println("NameOfClass N");
			return;
		}else if (System.in != null && inputDataFound()){
			Opcion.entradaEstandar(args);
			return;
		}
		claseValida(args);
	 }
    

    public static void recorre(String[] args, Contenido tipo){
	for (int i = 1; i < args.length; i++){
	    if (args[i] != null){
		int N = Integer.parseInt(args[i]);
		tipo.almacena(N);
	    }
	}
    }
    
    public static void claseValida(String[] args){
	String cadena = "";
        cadena = args[0];
        switch (cadena){
	case "Lista":
	    DibujaLista dl = new DibujaLista();
	    recorre(args, dl);
	    System.out.println(dl.formatoSVG());
	    break;
	case "Pila":
	    DibujaPila dp = new DibujaPila();
	    recorre(args, dp);
	    System.out.println(dp.formatoSVG());
	    break;
	case "Cola":
	    DibujaCola dc = new DibujaCola();
	    recorre(args, dc);
	    System.out.println(dc.formatoSVG());
	    break;
	case "ArbolBinarioCompleto":
	    DibujaArbol da = new DibujaArbolCompleto();
	    recorre(args, da);
	    System.out.println(da.formatoSVG());
	    break;
	case "ArbolBinarioOrdenado":
	    DibujaArbol db = new DibujaArbol();
	    recorre(args, db);
	    System.out.println(db.formatoSVG());
	    break;
	case "ArbolRojinegro":
	    DibujaArbol rn = new DibujaArbolRojinegro();
	    recorre(args, rn);
	    System.out.println(rn.formatoSVG());
	    break;
	case "ArbolAVL":
	    DibujaArbol avl = new DibujaArbolAVL();
	    recorre(args, avl);
	    System.out.println(avl.formatoSVG());
	    break;
	case "Grafica":
	    DibujaGrafica grafica = new DibujaGrafica();
	    if ((args.length - 1)%2 == 0){
		for (int i = 1; i < args.length; i++){
		    int N = Integer.parseInt(args[i]);
		    int N2 = Integer.parseInt(args[i + 1]);
		    grafica.almacena(N, N2);
		    i += 1;
		}
	    }
	    System.out.println(grafica.formatoSVG());
	    break;
	default:
	    System.out.println("NombreDeClase N");
	    break;
		}
    }

	
	/**
     * Checa los datos ingresados por entrada estandar.
     * @return verdadero si hay datos disponibles, falso
     * en caso contrario cubriendo la excepcion.
     */
    private static boolean inputDataFound() {
        try {
            return (System.in.available() > 0);
        } catch (IOException e) {
            return false;
        }
    }
	
}
