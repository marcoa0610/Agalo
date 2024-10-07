
package controlador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
           
           // Encriptar la contraseña antes de guardarla
            String contrasenaEncriptada = encriptarContrasena(vista.txtContrasenaAdmin.getText());
            if (contrasenaEncriptada == null) {
                JOptionPane.showMessageDialog(vista, "Error al encriptar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
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

            if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
 
            modelo.setNombre(nombre);
            modelo.setUsuario(usuario);
            modelo.setCorreoElectronico(correo);
            modelo.setContrasena(contrasenaEncriptada);
 
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
        
    /**
     * Método para encriptar la contraseña utilizando SHA-256.
     *
     * @param contrasena la contraseña a encriptar.
     * @return la contraseña encriptada en formato hexadecimal.
     */
     private String encriptarContrasena(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(vista, "Error en la encriptación de la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }
}

