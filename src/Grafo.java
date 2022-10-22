
import java.io.*;

public class Grafo {

    private Nodo InicioLista, FinLista;
    private Nodo InicioLista_Adyacente, FinLista_Adyacente;
    private int vertices, orden = 1, vertices_arbol = 0;
    private boolean[] marca = new boolean[vertices];
    private int[] OrdenVisitado = new int[vertices];
    public String[] arbol = new String[vertices+1];
    
//CONSTRUCTOR
    public Grafo() {
        InicioLista = null;
        FinLista = null;
    }

//METODOS PARA INSERTAR NODOS EN LA LISTA, YA SEA EN LA PRINCIPAL COMO EN LA DE ADYACENTES    
    public void Insertar(String nom) {
        if (!ListaVacia()) {
            FinLista.Siguiente = new Nodo(nom, null);
            FinLista = FinLista.Siguiente;
        } else {
            InicioLista = FinLista = new Nodo(nom, null);
        }
    }

    public void Insertar_Adyacente(String nom) {
        int b = 1;
        if (!ListaVacia_Adyacente()) {
            FinLista_Adyacente.Adyacente = new Nodo(nom, null, b);
            FinLista_Adyacente = FinLista_Adyacente.Adyacente;
        } else {
            InicioLista_Adyacente = FinLista_Adyacente = new Nodo(nom, null, b);
        }
    }

//METODOS PARA VERTIFICAR SI ALGUNA LISTA ESTA VACIA
    public boolean ListaVacia() {
        return InicioLista == null;
    }

    public boolean ListaVacia_Adyacente() {
        return InicioLista_Adyacente == null;
    }

 //METODO PARA BUSCAR UN NODO EN LA LISTA LIGADA
    public int Buscador(String dato) {
        Nodo Recorrer = InicioLista;
        boolean esta = false;
        int x = 0, pos = 0;
        while (Recorrer != null && esta != true) {
            if (dato.equals(Recorrer.Nombre)) {
                esta = true;
                pos = x;
                break;
            } else {
                Recorrer = Recorrer.Siguiente;
                x++;
            }
        }
        if (esta == false) {
            return -1;
        } else {
            return pos;
        }
    }

//METODOS PARA MOSTRAR LOS GRAFOS SEGUN LA QUE SE REQUIERA
    public void MostrarLista() {
        Nodo Recorrer = InicioLista;
        if (!ListaVacia()) {
            while (Recorrer != null) {
                System.out.print("[" + Recorrer.Nombre + "]->");
                Recorrer = Recorrer.Siguiente;
            }
            System.out.println();
        } else {
            System.out.println("La Lista esta vacia");
        }
    }

    public void MostrarLista_Adyacente(Nodo Recorrer) {
        if (!ListaVacia()) {
            while (Recorrer != null) {
                System.out.print("[" + Recorrer.Nombre + "]↓");
                Nodo Recorrer2 = Recorrer.lista;
                MostrarLista_Adyacente_mini(Recorrer2);
                Recorrer = Recorrer.Siguiente;
            }
            System.out.println();
        } else {
            System.out.println("La Lista esta vacia");
        }
    }

    public void MostrarLista_Adyacente_mini(Nodo Recorrer2) {
        if (!ListaVacia()) {
            while (Recorrer2 != null) {
                System.out.print("[" + Recorrer2.Nombre + "]->");
                Recorrer2 = Recorrer2.Adyacente;
            }
            System.out.println();
        } else {
            System.out.println("La Lista esta vacia");
        }
    }

//METODO PARA MOSTRAR EL ORDEN DE VISITADOS    
    public void mostrarOrdenVisitado() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("| " + OrdenVisitado[i] + " ");
        }
        System.out.print("|");
    }

//METODO PARA BORRAR MARCAS DE VISITADO
    public void BorraMarcas() {
        System.out.println("Vertices: " + vertices);
        for (int i = 0; i < vertices; i++) {
            marca[vertices] = false;
        }
    }

//METODO PARA MOSTRAR EL BOSQUE DE EXPANSION
    public void mostrarArbol() {
        for (int i = 0; i < vertices_arbol; i++) {
            if (arbol[i] == "") {
                break;
            } else {
                System.out.println(arbol[i]);
            }
        }
    }

//METODOS GET PARA EL MAIN
    public int getverticesarbol() {
        return vertices_arbol;
    }

    public Nodo getInicio() {
        return InicioLista;
    }

//METODOS PRINCIPALES DEL PROGRAMA
    public void bpp(int pos) {
        marca[pos] = true;
        OrdenVisitado[pos] = orden;
        orden++;
        Nodo aux = InicioLista;
        for (int i = 0; i < pos; i++) {
            aux = aux.Siguiente;
        }
        for (aux = aux.lista; aux != null; aux = aux.Adyacente) {
            if (marca[Buscador(aux.Nombre)] == false) {
                arbol[vertices_arbol] = arbol[vertices_arbol] + aux.Nombre + ", ";
                bpp(Buscador(aux.Nombre));
                vertices_arbol++;
            }
        }

    }

    public void BusquedaPrimeroEnProfundidad() {
        Nodo v = InicioLista;
        BorraMarcas();
        for (int i = 0; i < vertices; i++) {
            if (marca[i] == false) {
                bpp(i);
            }
        }
    }

    public void bpa(int pos) {
        Cola c = new Cola();
        Nodo x, y, v = InicioLista;
        marca[pos] = true;
        for (int i = 0; i < pos; i++) {
            v = v.Siguiente;
        }
        int b = 0;
        c.insertar(v.Nombre, v.lista, b);
        while (!c.estaVacia()) {
            x = c.Inicio;
            v = InicioLista;
            pos = Buscador(x.Nombre);
            for (int i = 0; i < pos; i++) {
                v = v.Siguiente;
            }
            if (OrdenVisitado[Buscador(x.Nombre)] == 0) {
                OrdenVisitado[Buscador(x.Nombre)] = orden;
                orden++;
            }
            c.eliminar();
            for (y = v.lista; y != null; y = y.Adyacente) {
                if (marca[Buscador(y.Nombre)] == false) {
                    marca[Buscador(y.Nombre)] = true;
                    arbol[vertices_arbol] = arbol[vertices_arbol] + y.Nombre + ", ";
                    c.insertar(y.Nombre, y.lista, b);
                }
            }
            vertices_arbol++;
        }
    }

    public void BusquedaPrimeroEnAnchura() {
        BorraMarcas();
        for (int i = 0; i < vertices; i++) {
            if (marca[i] == false) {
                bpa(i);
            }
        }
    }

//METODO PARA IMPORTAR LAS VERTICES A TRAVES DE LOS ARCHIVOS
    public void Import(int op) {
        String op2 = String.valueOf(op);
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(op2 + ".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            String[] part = new String[2];
            vertices = 0;
            while ((linea = br.readLine()) != null) {
                vertices++;
                part = linea.split(":");
                Insertar(part[0]);
                if (linea.charAt(linea.length() - 1) == ' ') {
                    part[1] = "";
                }
                int column = linea.split(",").length;
                String[] part2 = new String[column];
                if (column != 0) {
                    part2 = part[1].split(",");
                    for (int i = 0; i < column; i++) {
                        Insertar_Adyacente(part2[i]);
                    }
                } else {
                    System.out.println("Error");
                }
                part = null;
                part2 = null;
                FinLista.lista = InicioLista_Adyacente;
                InicioLista_Adyacente = null;
            }
//            this.vertices = vertices;
            marca = new boolean[vertices];
            OrdenVisitado = new int[vertices];
            arbol = new String[vertices+1];
            orden = 1;
            for (int i = 0; i < vertices; i++) {
                arbol[i] = "";
            }
            for (int i = 0; i < vertices; i++) {
                marca[i] = false;
            }

//            MostrarLista();
            filtro();
//            MostrarLista_Adyacente(InicioLista);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

//METODO PARA FILTRAR LAS VERTICES QUE NO TIENEN ADYACENTES
    public void filtro() {
        for (Nodo aux = InicioLista; aux != null; aux = aux.Siguiente) {
            if (aux.lista.Nombre.equals("")) {
                aux.lista = null;
            }
        }
    }
}
