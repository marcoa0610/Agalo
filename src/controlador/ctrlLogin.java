package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.UsuarioEscritorio;
import vista.frmLogin;
import vista.frmRegistro;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import vista.frmIngresoCorreo;
import vista.frmMenu;

/**
 *
 * @author lagal
 */
public class ctrlLogin implements ActionListener, MouseListener {

    private UsuarioEscritorio modelo;
    private frmLogin vista;
    public static String CorreoUsuario;
 
    
    public ctrlLogin(UsuarioEscritorio modelo, frmLogin vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.btnIngresar.addActionListener(this); // Usar ActionListener en lugar de MouseListener
        this.vista.btnRegister.addActionListener(this); // Añadir ActionListener para el botón de registro
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnIngresar) {
            String correo = vista.txtCorreo.getText();
            CorreoUsuario = correo;
            String contrasena = vista.txtContrasena.getText();
            
            // Validar entradas
            if (!validarEntradas(correo, contrasena)) {
                return;
            }

            // Encriptar la contraseña antes de comparar
            String contrasenaEncriptada = encriptarContrasena(contrasena);
            
            modelo.setCorreo(correo);
            modelo.setContrasena(contrasenaEncriptada);
            
            // Intentar iniciar sesión
            boolean comprobar = modelo.iniciarSesion();

            if (comprobar) {
                JOptionPane.showMessageDialog(vista, "¡Bienvenido, usuario encontrado!");
                frmMenu.initfrmMenu();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == vista.btnRegister) {
            
            frmRegistro.initFrmRegistro();
            vista.dispose(); 
        }
    }

    /**
     * Método para validar los campos de entrada del formulario.
     * @return true si todas las validaciones pasan, false en caso contrario.
     */
    private boolean validarEntradas(String usuario, String contrasena) {
        StringBuilder errores = new StringBuilder();

        if (usuario.trim().isEmpty()) {
            errores.append("El campo 'Usuario' no puede estar vacío.\n");
        }

        if (contrasena.trim().isEmpty()) {
            errores.append("El campo 'Contraseña' no puede estar vacío.\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(vista, errores.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Método para encriptar la contraseña utilizando SHA-256.
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
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(vista, "Error en la encriptación de la contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
     if (e.getSource() == vista.jOlvidarContrasena) {
          frmIngresoCorreo.initFrmIngresoCorreo(); 
          vista.dispose();
        
        
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
    
}