
package controlador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
                System.out.println("El nombre no puede ser 'Nombre' o vacío.");
                return;
            }
 
            if (usuario.equals("Usuario") || usuario.isEmpty()) {
                System.out.println("El usuario no puede ser 'Usuario' o vacío.");
                return;
            }
 
            if (contrasena.equals("Contraseña") || contrasena.length() < 6) { // Asegúrate de que el tamaño sea suficiente
                System.out.println("La contraseña no puede ser 'Contraseña' y debe tener al menos 6 caracteres.");
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
            String nombre = vista.txtNombreAdmin.getText();
            String usuario = vista.txtUsuarioAdmin.getText();
            String correo = vista.txtCorreoAdmin.getText();
            String contrasena = vista.txtContrasenaAdmin.getText(); // Asegúrate de que tengas este campo
 
            // Validaciones
            if (nombre.equals("Nombre") || nombre.isEmpty()) {
                System.out.println("El nombre no puede ser 'Nombre' o vacío.");
                return;
            }
 
            if (usuario.equals("Usuario") || usuario.isEmpty()) {
                System.out.println("El usuario no puede ser 'Usuario' o vacío.");
                return;
            }
 
            if (contrasena.equals("Contraseña") || contrasena.length() < 6) { // Asegúrate de que el tamaño sea suficiente
                System.out.println("La contraseña no puede ser 'Contraseña' y debe tener al menos 6 caracteres.");
                return;
            }
 
            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.setContrasena(contrasena); // Asegúrate de que tengas este método en tu modelo
 
            modelo.Actualizar(vista.jtbAdmin);
            modelo.Mostrar(vista.jtbAdmin);
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