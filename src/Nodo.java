public class Nodo {
    public String Nombre;
    public Nodo Siguiente;
    public Nodo Adyacente;
    public Nodo lista;

    public Nodo(){
        Nombre=null;
        Adyacente=null;
        Siguiente=null;
    }
    
    public Nodo(String nom){
        Nombre=nom;
    }
    
    public Nodo(String nom, Nodo nodo){
        Nombre=nom;
        Siguiente=nodo;
    }
    
    public Nodo(String nom, Nodo nodo, int b){
        Nombre=nom;
        Adyacente = nodo;
    }
    
}
