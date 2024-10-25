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
import java.lang.NullPointerException;

import java.io.*;

/**
 * Clase que procesa opciones para dibujar estructuras de datos en SVG.
 */
public class Opcion {

    /** Bandera para indicar si se encontró la clase. */
    private static boolean flag;

    /** Arreglo para almacenar los elementos. */
    private static int[] contenido;

    /** Nombre de la estructura de datos. */
    private static String estructura;

    /** Bandera para indicar si se debe salir del programa. */
    private static boolean salir;

    /** Lista para almacenar los números. */
    static Lista<Integer> numeros;

    /**
     * Revisa una línea de texto para determinar la estructura de datos a dibujar.
     * @param linea la línea de texto a revisar.
     * @param salir indica si se debe salir del programa.
     */
    public static void revisaLinea(String linea, boolean salir) {
        String nombreDeClase = "";
        linea = linea.trim();
        for (int i = 0; i < linea.length(); i++) {

            if (!linea.contains("#") && linea.matches(".*[!@$%^&*()_+{}|:\"<>?\\[\\];',./\\\\].*")) {
                System.out.println("Asegúrate de que el archivo no contenga caracteres especiales (excepto \"#\").");
                salir = true;
                return;
            }

            // Si no se ha encontrado la clase.
            if (!flag && linea.matches("[a-zA-Z]+")) {
                nombreDeClase += linea.charAt(i);
                validaClase(nombreDeClase);
            } else if (!flag && Character.isDigit(linea.charAt(i))) {
                System.out.println("Asegúrate de que primero se encuentre el nombre de la clase.");
                salir = true;
                return;
            }
        }
    }

    /**
     * Define la estructura de datos.
     * @param nombreDeClase el nombre de la clase que representa la estructura de datos.
     */
    private static void defineEstructura(String nombreDeClase) {
        estructura = nombreDeClase;
    }

    /**
     * Valida la clase a partir de su nombre.
     * @param nombreDeClase el nombre de la clase a validar.
     */
    public static void validaClase(String nombreDeClase) {
        switch (nombreDeClase) {
            case "Lista":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "Pila":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "Cola":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "ArbolBinarioCompleto":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "ArbolBinarioOrdenado":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "ArbolRojinegro":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "ArbolAVL":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
            case "Grafica":
                flag = true;
                defineEstructura(nombreDeClase);
                break;
        }
    }

    /**
     * Recorre la lista de números y almacena cada uno en la estructura de datos correspondiente.
     * @param tipo el tipo de estructura de datos.
     */
    private static void recorre(Contenido tipo) {
        for (int e : numeros) {
            tipo.almacena(e);
        }
    }

    /**
     * Regresa la representación SVG de la estructura de datos.
     */
    private static void regresaSVG() {
		try {
        switch (estructura) {
            case "Lista":
                DibujaLista dl = new DibujaLista();
                recorre(dl);
                System.out.println(dl.formatoSVG());
                break;
            case "Pila":
                DibujaPila dp = new DibujaPila();
                recorre(dp);
                System.out.println(dp.formatoSVG());
                break;
            case "Cola":
                DibujaCola dc = new DibujaCola();
                recorre(dc);
                System.out.println(dc.formatoSVG());
                break;
            case "ArbolBinarioCompleto":
                DibujaArbolCompleto dac = new DibujaArbolCompleto();
                recorre(dac);
                System.out.println(dac.formatoSVG());
                break;
            case "ArbolBinarioOrdenado":
                DibujaArbol da = new DibujaArbol();
                recorre(da);
                System.out.println(da.formatoSVG());
                break;
            case "ArbolRojinegro":
                DibujaArbolRojinegro dar = new DibujaArbolRojinegro();
                recorre(dar);
                System.out.println(dar.formatoSVG());
                break;
            case "ArbolAVL":
                DibujaArbolAVL avl = new DibujaArbolAVL();
                recorre(avl);
                System.out.println(avl.formatoSVG());
                break;
            case "Grafica":
                DibujaGrafica dg = new DibujaGrafica();
				if (numeros.getLongitud()%2 == 0){
					Lista<Integer> copia = numeros.copia();
				    while (copia.getElementos() != 0){
						int N = copia.eliminaPrimero();
						int N2 = copia.eliminaPrimero();
						dg.almacena(N, N2);
					}
				}
                System.out.println(dg.formatoSVG());
                break;
            default:
                break;
        }
		} catch (NullPointerException npe) {
		}
    }

    /**
     * Procesa una línea de texto para extraer los números y almacenarlos.
     * @param linea la línea de texto a procesar.
     */
    private static void procesarLinea(String linea) {
        String lineaTrimmed = linea.toString().trim();
        char[] digitos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder numeroElemento = new StringBuilder();
        String numeroAgregado = "hola";

        for (int i = 0; i < lineaTrimmed.length(); i++) {
            char c = lineaTrimmed.charAt(i);
            if (esDigito(c, digitos)) {
                numeroElemento.append(c);
            } else if (c == '#') {
                if (numeroElemento.length() != 0) {
                    numeroAgregado = numeroElemento.toString();
                    procesarNumero(Integer.parseInt(numeroAgregado));
                    numeroElemento = new StringBuilder();
                }
            } else {
                if (numeroElemento.length() != 0) {
                    numeroAgregado = numeroElemento.toString();
                    procesarNumero(Integer.parseInt(numeroAgregado));
                }
                numeroElemento = new StringBuilder();
            }
        }
    }

    /**
     * Verifica si un carácter es un dígito.
     * @param c el carácter a verificar.
     * @param digitos arreglo de dígitos.
     * @return true si el carácter es un dígito, false en caso contrario.
     */
    private static boolean esDigito(char c, char[] digitos) {
        for (char digito : digitos) {
            if (c == digito) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procesa un número y lo almacena en la lista de números.
     * @param numero el número a procesar.
     */
    private static void procesarNumero(int numero) {
        numeros.agrega(numero);
    }

    /**
     * Lee la entrada estándar y procesa las líneas de texto.
     * @param args los argumentos pasados al programa.
     */
    public static void entradaEstandar(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int lineas = 0;
            numeros = new Lista<>();
            String linea = "";
            Lista<String> c = new Lista<>();
            salir = false;
            while ((linea = br.readLine()) != null) {
                c.agrega(linea);
                revisaLinea(linea, salir);
            }
            if (!salir) {
                for (String s : c) {
                    procesarLinea(s);
                }
                regresaSVG();
            }
            br.close();
        } catch (IOException ioe) {
            System.out.println("Ocurrió un error al leer el archivo.");
        }
    }

    
}
