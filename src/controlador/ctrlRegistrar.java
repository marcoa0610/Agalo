package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioEscritorio;
import vista.frmRegistro;
import vista.frmLogin;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class ctrlRegistrar implements ActionListener {

    private UsuarioEscritorio modelo;
    private frmRegistro vista;

    public ctrlRegistrar(UsuarioEscritorio modelo, frmRegistro vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnLogear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar) {
            System.out.println("Botón registrar clicado");

            // Validar todas las entradas
            if (!validarEntradas()) {
                return;
            }

            // Verificar que los términos y condiciones estén seleccionados
            if (!vista.jCondiciones.isSelected()) {
                JOptionPane.showMessageDialog(vista, "Debe aceptar los términos y condiciones para registrarse.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Setear los valores del modelo con las entradas del usuario
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setUsuario(vista.txtUsuario.getText());
            modelo.setCorreo(vista.txtCorreoElectronico.getText());
            modelo.setEdad_Escritorio(vista.txtEdadRegistro.getText());
            modelo.setCiudad_Escritorio(vista.txtCiudadRegistro.getText());
            modelo.setPais_Escritorio(vista.txtPaisRegistro.getText());

            // Encriptar la contraseña
            String contrasenaEncriptada = encriptarContrasena(vista.txtContra.getText());
            if (contrasenaEncriptada == null) {
                JOptionPane.showMessageDialog(vista, "Error al encriptar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modelo.setContrasena(contrasenaEncriptada);

            try {
                // Intentar guardar el usuario en la base de datos
                modelo.GuardarUsuario();
                JOptionPane.showMessageDialog(vista, "Usuario registrado exitosamente.");
                
                // Abrir la ventana de login tras el registro exitoso
                frmLogin vistaLogin = new frmLogin();
                vistaLogin.setVisible(true);
                vista.dispose();
            } catch (Exception ex) {
                // Mostrar cualquier error que ocurra durante la inserción
                JOptionPane.showMessageDialog(vista, "Error al registrar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == vista.btnLogear) {
            // Cerrar la ventana actual y abrir la de login
            frmLogin vistaLogin = new frmLogin();
            vistaLogin.setVisible(true);
            vista.dispose();
        }
    }

    /**
     * Validación de las entradas del formulario
     */
    private boolean validarEntradas() {
        String nombre = vista.txtNombre.getText();
        String usuario = vista.txtUsuario.getText();
        String correo = vista.txtCorreoElectronico.getText();
        String edad = vista.txtEdadRegistro.getText();
        String ciudad = vista.txtCiudadRegistro.getText();
        String pais = vista.txtPaisRegistro.getText();
        String contrasena = vista.txtContra.getText();

        // Validar que todos los campos estén llenos
        if (nombre.isEmpty() || usuario.isEmpty() || correo.isEmpty() || edad.isEmpty() || ciudad.isEmpty() || pais.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar formato del correo electrónico
        if (!Pattern.matches("[^@]+@[^\\.]+\\..+", correo)) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar un correo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar si el correo ya existe en la base de datos
        if (modelo.verificarCorreoExistente(correo)) {
            JOptionPane.showMessageDialog(vista, "El correo ya está registrado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar que la edad sea un número y mayor de 18 años
        try {
            int edadInt = Integer.parseInt(edad);
            if (edadInt < 18) {
                JOptionPane.showMessageDialog(vista, "Debe ser mayor de 18 años para registrarse.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar una edad válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar que la contraseña tenga al menos 8 caracteres
        if (contrasena.length() < 8) {
            JOptionPane.showMessageDialog(vista, "La contraseña debe tener al menos 8 caracteres.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Método para encriptar la contraseña usando SHA-256
     */
    private String encriptarContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
