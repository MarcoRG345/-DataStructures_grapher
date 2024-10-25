package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador() {
            // Aquí va su código.
	    iterador = vertices.iterator();
	    
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            // Aquí va su código.
	    if (iterador.hasNext())
		return true;
	    return false;
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            // Aquí va su código.
	    if (hasNext())
		return iterador.next().elemento;
	    throw new NoSuchElementException();
        }
    }

    /* Clase interna privada para vértices. */
    private class Vertice implements VerticeGrafica<T> {

        /* El elemento del vértice. */
        private T elemento;
        /* El color del vértice. */
        private Color color;
        /* La lista de vecinos del vértice. */
        private Lista<Vertice> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            // Aquí va su código.
	    this.elemento = elemento;
	    color = Color.NINGUNO;
	    vecinos = new Lista<Vertice>();
        }

        /* Regresa el elemento del vértice. */
        @Override public T get() {
            // Aquí va su código.
	    return elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            // Aquí va su código.
	    return vecinos.getElementos();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            // Aquí va su código.
	    return color;
        }

        /* Regresa un iterable para los vecinos. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            // Aquí va su código.
	    return vecinos;
        }
    }

    /* Vértices. */
    private Lista<Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
        // Aquí va su código.
	vertices = new Lista<Vertice>();
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    @Override public int getElementos() {
        // Aquí va su código
	return vertices.getElementos();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() {
        // Aquí va su código
	return aristas;
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido agregado a
     *         la gráfica.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
	if (elemento == null || contiene(elemento))
	    throw new IllegalArgumentException();
	Vertice vertice = new Vertice(elemento);
	vertices.agrega(vertice);
    }
    
    /**
     * Encuentra el elemento en la lista de vertices de la grafica
     * y devuelve el vertice indicado como instacia de {@link#Vertice}.
     * @param elemento el elemento a buscar en la lista de vertices.
     * @return el vertice de la grafica.
     */
    private Vertice busca(T elemento){
	for (Vertice vertice : vertices){
	    if (vertice.elemento.equals(elemento))
		return vertice;
	}
	return null;
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica. El peso de la arista que conecte a los elementos será 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
        // Aquí va su código.
	if (!contiene(a) || !contiene(b))
	    throw new NoSuchElementException();
	else if (a.equals(b) || sonVecinos(a, b))
	    throw new IllegalArgumentException();
	Vertice vertex_a = busca(a);
	Vertice vertex_b = busca(b);
	vertex_a.vecinos.agrega(vertex_b);
	vertex_b.vecinos.agrega(vertex_a);
	aristas++;
    }

    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
        // Aquí va su código.
	if (!contiene(a) || !contiene(b))
	    throw new NoSuchElementException();
	if (a.equals(b)  || !sonVecinos(a, b))
	    throw new IllegalArgumentException();
	Vertice vertex_a = busca(a);
	Vertice vertex_b = busca(b);
	vertex_b.vecinos.elimina(vertex_a);
	vertex_a.vecinos.elimina(vertex_b);
	aristas--;
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <code>true</code> si el elemento está contenido en la gráfica,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        // Aquí va su código.
	for (Vertice vertice : vertices){
	    if (vertice.elemento.equals(elemento))
		return true;
	}
	return false;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
	if (!contiene(elemento))
	    throw new NoSuchElementException();
        Vertice vertice = busca(elemento);
	for (Vertice vecino : vertice.vecinos){
	    desconecta(vertice.elemento, vecino.elemento);
	}
	vertices.elimina(vertice);
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <code>true</code> si a y b son vecinos, <code>false</code> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
        // Aquí va su código.
	if (a.equals(b) || !contiene(a) || !contiene(b))
	    throw new NoSuchElementException();
	Vertice u = busca(a);
	Vertice v = busca(b);
	int cont = 0;
	//vecinos de b.
	for (Vertice w : v.vecinos){
	    if (w.elemento.equals(a))
		cont++;
	}
	//vecinos de a.
	for (Vertice w : u.vecinos){
	    if (w.elemento.equals(b))
		cont++;
	}
	if (cont == 2)
	    return true;
	return false;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
        // Aquí va su código.
	if (!contiene(elemento))
	    throw new NoSuchElementException();
	return busca(elemento);
    }

    /**
     * Define el color del vértice recibido.
     * @param vertice el vértice al que queremos definirle el color.
     * @param color el nuevo color del vértice.
     * @throws IllegalArgumentException si el vértice no es válido.
     */
    public void setColor(VerticeGrafica<T> vertice, Color color) {
        // Aquí va su código.
	if (vertice.getClass() != Vertice.class)
	    throw new IllegalArgumentException();
	else{
	    Vertice v = (Vertice) vertice;
	    v.color = color;
	}
    }

    /**
     * Nos dice si la gráfica es conexa.
     * @return <code>true</code> si la gráfica es conexa, <code>false</code> en
     *         otro caso.
     */
    public boolean esConexa() {
        // Aquí va su código.
	int cont = 0;
	Cola<Vertice> cola = new Cola<Vertice>();
	for (Vertice vertice : vertices){
	    vertice.color = Color.NINGUNO;
	}
        Vertice v = vertices.get(0);
	v.color = Color.ROJO;
	cola.mete(v);
        while (!cola.esVacia()) {
	    v = cola.saca();
	    for (Vertice u : v.vecinos){
		if (u.color != Color.ROJO){
		    u.color = Color.ROJO;
		    cola.mete(u);
		}
	    } 
	}
	for (Vertice vertice : vertices){
	    if (vertice.color != Color.ROJO)
		return false;
	}
	return true;
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
        // Aquí va su código.
	for (Vertice vertice : vertices)
	    accion.actua(vertice);
    }

    /**
     * Realiza el recorrido ya sea BFS o DFS dependiendo de la opcion. Como puede
     * ser tanto pila como cola recibimos una instancia de {@link#MeteSaca<T>}.
     * @param opcion recibe la estructura que va realizar BFS o DFS.
     * @param accion recibe la accion que se va actuar en relacion al vertice.
     */
    private void bfsOrDfs(Vertice v, MeteSaca<Vertice> opcion, AccionVerticeGrafica<T> accion){
	for (Vertice vertice : vertices){
	    vertice.color = Color.ROJO;
	}
	v.color = Color.NEGRO;
	opcion.mete(v);
	while (!opcion.esVacia()){
	    v = opcion.saca();
	    accion.actua(v);
	    for (Vertice vecino : v.vecinos){
		if (vecino.color != Color.NEGRO){
		    vecino.color = Color.NEGRO;
		    opcion.mete(vecino);
		}
	    }
	}
    }
    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
        // Aquí va su código.
	if (!contiene(elemento))
	    throw new NoSuchElementException();
	Cola<Vertice> cola = new Cola<Vertice>();
	Vertice w = busca(elemento);
	bfsOrDfs(w, cola, accion);
	paraCadaVertice((v) -> setColor(v, Color.NINGUNO));
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
        // Aquí va su código.
	if (!contiene(elemento))
	    throw new NoSuchElementException();
        Vertice w = busca(elemento);
	Pila<Vertice> pila = new Pila<Vertice>();
	bfsOrDfs(w, pila, accion);
	paraCadaVertice((v) -> setColor(v, Color.NINGUNO));
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        // Aquí va su código.
	return vertices.esVacia();
    }

    /**
     * Limpia la gráfica de vértices y aristas, dejándola vacía.
     */
    @Override public void limpia() {
        // Aquí va su código.
	aristas = 0;
	vertices.limpia();
    }
    
    /**
     * Recibe dos vertices adyacentes y verifica que no este la simetria
     * de los vertices, es decir, para toda (u,v) en E(G) no debe ocurrir
     * que (v,u) exista en E(G), ya que es un propiedad conjuntista. El
     * alogoritmo trabaja con una estructura auxiliar de arreglo, una lista
     * nos hubiera aumentado mas la complejidad de lo terrible que es cuando 
     * el metodo toString() manda a llamar al algoritmo.
     * @param vertice el vertice en cuestion.
     * @param vecino el vecino del vertice.
     * @param a el arreglo en cuestion.
     */
    private void aristaRepetida(Vertice vertice, Vertice vecino, String[] a){
	String cadena = "("+vecino.elemento+", "+vertice.elemento+")";
        for (int i = 0; i < a.length; i++){
	    if (a[i] != null && a[i].equals(cadena))
		return;
	}
	for (int i = 0; i < a.length; i++){
	    if (a[i] == null){
		a[i] = "("+vertice.elemento+", "+vecino.elemento+")";
		break;
	    }
	}
    }
    /**
     * Regresa una representación en cadena de la gráfica.
     * @return una representación en cadena de la gráfica.
     */
    @Override public String toString() {
        // Aquí va su código.
	String cadena = "{";
	String registro = "";
	for (Vertice vertice : vertices){
	    cadena += vertice.elemento + ", ";
	}
	cadena += "}, {";
	int index = 0;
	Lista<Vertice> registros = new Lista<>();
	String[] a = new String[(vertices.getLongitud()*(vertices.getLongitud() - 1))/2];
        for (Vertice vertice : vertices){
	    for (Vertice vecino : vertice.vecinos){
		aristaRepetida(vertice, vecino, a);
	    }
	}
	for (int i = 0; i < a.length; i++){
	    if (a[i] != null)
		cadena += a[i] + ", ";
	}
	cadena += "}";
	return cadena;
    }
    /**
     * Nos dice si la gráfica es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la gráfica es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
	@SuppressWarnings ("unchecked") Grafica<T> grafica = (Grafica<T>)objeto;
        // Aquí va su código.
	if (getElementos() != grafica.getElementos() || getAristas() != grafica.getAristas())
	    return false;

	paraCadaVertice((v) -> setColor(v, Color.NINGUNO));
	paraCadaVertice((v) -> grafica.setColor(v, Color.NINGUNO));
	
	for (Vertice w : vertices){
	    for (Vertice v : grafica.vertices){
		if (w.elemento.equals(v.elemento)){
		    w.color = Color.ROJO;
		    v.color = Color.ROJO;
		}
		    
	    }
	}

        
        for (Vertice vertice : vertices)
	    if (vertice.color != Color.ROJO)
		return false;
	for (Vertice vertice : grafica.vertices)
	    if (vertice.color != Color.ROJO)
		return false;

	Cola<Vertice> cola = new Cola<Vertice>();
	Vertice vertex = vertices.getPrimero();
	vertex.color = Color.NEGRO;
	cola.mete(vertex);
	Cola<Vertice> q = new Cola<Vertice>();
        vertex = grafica.vertices.getPrimero();
	vertex.color = Color.NEGRO;
	q.mete(vertex);
	while (!cola.esVacia() && !q.esVacia()){
	    vertex = cola.saca();
	    Vertice u = vertex;
	    for (Vertice vecino : vertex.vecinos){
		if (vecino.color != Color.NEGRO){
		    vecino.color = Color.NEGRO;
		    cola.mete(vecino);
		}
	    }
	    vertex = q.saca();
	    Vertice w = vertex;
	    for (Vertice vecino : vertex.vecinos) {
		if (vecino.color != Color.NEGRO){
		    vecino.color = Color.NEGRO;
		    q.mete(vecino);
		}
	    }
	    if (u.elemento.equals(w.elemento)){
		if (!cola.equals(q))
		    return false;
	    }
	}
	return true;
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
