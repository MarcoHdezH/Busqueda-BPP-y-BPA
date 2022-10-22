    
public class Cola{

    Nodo Inicio;
    Nodo Final;

    //Constructor
    public Cola() {
        Inicio = null;
        Final = null;
    }

    public void insertar(String Nombre, Nodo lista, int b) {
        Nodo temp;
        Nodo nuevo = new Nodo(Nombre, lista, b);

        if (Inicio != null) {
            for (temp = Inicio; temp.Adyacente != null; temp = temp.Adyacente);
            temp.Adyacente = nuevo;
        } else {
            Inicio = nuevo;
        }
    }

    public String eliminar() {
        String Nombre = Inicio.Nombre;
        Inicio = Inicio.Adyacente;
        return Nombre;
    }

    public boolean estaVacia() {
        return (Inicio == null);
    }
    
    public Nodo consultarInicio(){
        return Inicio;
    }
}
