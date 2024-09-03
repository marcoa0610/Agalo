/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import modelo.UsuarioEscritorio;
import vista.frmCambiarContrasena;
import vista.frmLogin;

public class ctrlCambiarContrasena implements ActionListener {
    private UsuarioEscritorio modelo;
    private frmCambiarContrasena vista;

    public ctrlCambiarContrasena(UsuarioEscritorio modelo, frmCambiarContrasena vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnCambiarContrasena.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCambiarContrasena) {
            String nuevaContrasena = vista.txtNuevaContrasena.getText();
            String confirmarContrasena = vista.txtConfirmarContrasena.getText();

            if (!nuevaContrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                String contrasena = encriptarContrasena(nuevaContrasena);
                modelo.actualizar_contra(ctrlIngresoCorreo.correoEnviado, contrasena);
                JOptionPane.showMessageDialog(vista, "Se cambio la contrasena correctamente"); 
                frmLogin login = new frmLogin();
                login.initFrmLogin();
                vista.dispose();
            }else{
                JOptionPane.showMessageDialog(vista, "La contrasena no coincide");
            }

            // Encriptar la nueva contraseña antes de guardar
            /*String nuevaContrasenaEncriptada = encriptarContrasena(nuevaContrasena);
            modelo.setContrasena(nuevaContrasenaEncriptada);

            // Guardar la nueva contraseña en la base de datos
            modelo.GuardarUsuario();
            JOptionPane.showMessageDialog(vista, "Contraseña cambiada con éxito.");*/
            // Cierra el formulario
        }
    }

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
}