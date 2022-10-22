
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String op = "0", temp;
        int op2 = -1;
        String v;
        Grafo grafo;
        Imagenes mostrar = new Imagenes();
        Imagenes mostrar2 = new Imagenes();
        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("Menu de opciones:");
            System.out.println("1) Busqueda Primero en Profundidad (bpp).");
            System.out.println("2) Busqueda Primero en Anchura (bpa).");
            System.out.println("3) Salir.");
            op = sc.nextLine();
            mostrar.cerrarVentana1();
            mostrar2.cerrarVentana1();
            switch (op) {
                case "1":
                    do {
                        System.out.println("Seleccione el grafo con el que desea trabajar (1-12):");
                        temp = sc.nextLine();
                        if (isNumeric(temp)) {
                            op2 = Integer.parseInt(temp);
                            if ((op2 > 0 && op2 < 13) == false) {
                                System.out.println("Grafo Invalida.");
                            }
                        } else {
                            System.out.println("El valor que ingreso es invalido.");
                        }
                    } while ((op2 > 0 && op2 < 13) == false);
                    System.out.println("Selecciono el grafo " + op2);
                    grafo = new Grafo(); //INSTANCIAMOS DEL GRAFO QUE VAMOS A USAR
                    grafo.Import(op2);
                    grafo.MostrarLista();
                    mostrar = new Imagenes();
                    mostrar.mostrarVentana1(op2);
                    do {
                        System.out.println("Seleccione la vertice donde desea empezar:");
                        v = sc.nextLine();
                        if (grafo.Buscador(v) != -1) {
                            Nodo aux = grafo.getInicio();
                            for (int i = 0; i < grafo.Buscador(v); i++) {
                                aux = aux.Siguiente;
                            }
                            grafo.arbol[grafo.getverticesarbol()] = aux.Nombre + ", ";
                            grafo.bpp(grafo.Buscador(v));
                            System.out.println("Orden de Visitado: ");
                            grafo.MostrarLista();
                            grafo.mostrarOrdenVisitado();
                            System.out.println();
                            System.out.println("Arbol:");
                            grafo.mostrarArbol();
                        } else {
                            System.out.println("La vertice que ingreso es invalida o no existe en el grafo.");
                        }
                    } while (grafo.Buscador(v) == -1);
                    grafo = null;
                    break;
                case "2":
                    do {
                        System.out.println("Seleccione el grafo con el que desea trabajar (1-12):");
                        temp = sc.nextLine();
                        if (isNumeric(temp)) {
                            op2 = Integer.parseInt(temp);
                            if ((op2 > 0 && op2 < 13) == false) {
                                System.out.println("Grafo Invalida.");
                            }
                        } else {
                            System.out.println("El valor que ingreso es invalido.");
                        }
                    } while ((op2 > 0 && op2 < 13) == false);
                    System.out.println("Selecciono el grafo " + op2);
                    grafo = new Grafo(); //INSTANCIAMOS DEL GRAFO QUE VAMOS A USAR
                    grafo.Import(op2);
                    grafo.MostrarLista();
                    mostrar2 = new Imagenes();
                    mostrar2.mostrarVentana1(op2);
                    do {
                        System.out.println("Seleccione la vertice donde desea empezar:");
                        v = sc.nextLine();
                        if (grafo.Buscador(v) != -1) {
                            Nodo aux = grafo.getInicio();
                            for (int i = 0; i < grafo.Buscador(v); i++) {
                                aux = aux.Siguiente;
                            }
                            grafo.arbol[grafo.getverticesarbol()] = aux.Nombre + ", ";
                            grafo.bpa(grafo.Buscador(v));
                            System.out.println("Orden Vistado: ");
                            grafo.MostrarLista();
                            grafo.mostrarOrdenVisitado();
                            System.out.println();
                            System.out.println("Arbol: ");
                            grafo.mostrarArbol();
                            System.out.println();
                        } else {
                            System.out.println("La vertice que ingreso es invalida o no existe en el grafo.");
                        }
                    } while (grafo.Buscador(v) == -1);
                    grafo = null;
                    break;
                case "3":
                    System.out.println("Adios.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (!op.equals("3"));
    }
    
//METODO PARA VALIDAR ENTERO
    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
