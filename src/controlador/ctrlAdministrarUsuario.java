
package controlador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.AdministrarUsuario;
import vista.frmAdministrarUsuarios;
 
public class ctrlAdministrarUsuario implements MouseListener, KeyListener {
 
    // 1- Mandar a llamar a las otras capas (modelo y vista)
    private AdministrarUsuario modelo;
    private frmAdministrarUsuarios vista;
 
    // 2- Crear el constructor
    public ctrlAdministrarUsuario(AdministrarUsuario modelo, frmAdministrarUsuarios vista) {
        this.modelo = modelo;
        this.vista = vista;
 
        vista.btnAgregarAdmin.addMouseListener(this);
        vista.btnEditarAdmin.addMouseListener(this);
        vista.btnEliminarAdmin.addMouseListener(this);
        vista.jtbAdmin.addMouseListener(this); // Listener para la tabla
        modelo.Mostrar(vista.jtbAdmin);
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregarAdmin) {
            String nombre = vista.txtNombreAdmin.getText();
            String usuario = vista.txtUsuarioAdmin.getText();
            String correo = vista.txtCorreoAdmin.getText();
            String contrasena = vista.txtContrasenaAdmin.getText(); // Asegúrate de que tengas este campo
 
            // Validaciones
            if (nombre.equals("Nombre") || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El nombre no puede ser 'Nombre' o estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (usuario.equals("Usuario") || usuario.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El usuario no puede ser 'Usuario' o estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (contrasena.equals("Contraseña") || contrasena.length() < 6) { // Asegúrate de que el tamaño sea suficiente
                JOptionPane.showMessageDialog(vista, "La contraseña debe tener al menos 6 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
 
            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.setContrasena(contrasena); // Asegúrate de que tengas este método en tu modelo
 
            modelo.Guardar();
            modelo.Mostrar(vista.jtbAdmin);
        }
 
        if (e.getSource() == vista.btnEliminarAdmin) {
            modelo.Eliminar(vista.jtbAdmin);
            modelo.Mostrar(vista.jtbAdmin);
        }
 
        if (e.getSource() == vista.jtbAdmin) {
            modelo.cargarDatosTabla(vista);
        }
 
        if (e.getSource() == vista.btnEditarAdmin) {
            // Obtener datos de los campos de texto
            String nombre = vista.txtNombreAdmin.getText();
            String usuario = vista.txtUsuarioAdmin.getText();
            String correo = vista.txtCorreoAdmin.getText();
            String contrasena = vista.txtContrasenaAdmin.getText();

            // Validaciones
            if (nombre.isEmpty() || nombre.equals("Nombre")) {
                System.out.println("El nombre no puede estar vacío o ser 'Nombre'.");
                return;
            }

            if (usuario.isEmpty() || usuario.equals("Usuario")) {
                System.out.println("El usuario no puede estar vacío o ser 'Usuario'.");
                return;
            }

            if (contrasena.isEmpty() || contrasena.length() < 6) {
                System.out.println("La contraseña no puede estar vacía y debe tener al menos 6 caracteres.");
                return;
            }

            // Asignar valores al modelo
            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.setContrasena(contrasena);

            // Actualizar los datos del administrador seleccionado
            modelo.Actualizar(vista.jtbAdmin);
            modelo.Mostrar(vista.jtbAdmin); // Actualizar la tabla
        }

        if (e.getSource() == vista.jtbAdmin) {
            // Cargar datos del administrador seleccionado en los campos de texto
            modelo.cargarDatosTabla(vista);
        }
    }
 
    @Override
    public void mousePressed(MouseEvent e) {
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {
    }
 
    @Override
    public void mouseEntered(MouseEvent e) {
    }
 
    @Override
    public void mouseExited(MouseEvent e) {
    }
 
    @Override
    public void keyTyped(KeyEvent e) {
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
    }
}