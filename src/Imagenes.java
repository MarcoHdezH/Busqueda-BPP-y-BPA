
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Imagenes {

    private JFrame ventana;
    private JPanel contenedor;
    private JLabel etiqueta;

    public Imagenes() {
        ventana = new JFrame("Mostrar grafo");
        contenedor = new JPanel();
    }

    public void mostrarVentana1(int x) {
        String op2 = String.valueOf(x);
        etiqueta = new JLabel(new ImageIcon(op2 + ".png"));
        contenedor.add(etiqueta);
        ventana.getContentPane().add(contenedor);
        ventana.pack();
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setAlwaysOnTop(true);
        ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public void cerrarVentana1(){
        ventana.setVisible(false);
        
    }
}
