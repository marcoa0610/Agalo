package controlador;

import java.awt.event.*;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.EnviarCorreo;
import modelo.UsuarioEscritorio;
import vista.frmIngresoCorreo;
import vista.frmValidarCodigo;

public class ctrlIngresoCorreo implements MouseListener {
    private UsuarioEscritorio modelo;
    private frmIngresoCorreo vista;
    static Random random = new Random();
    static int numeroAleatorio = 1000 + random.nextInt(9000);
    static String correoEnviado;

    public ctrlIngresoCorreo(UsuarioEscritorio modelo, frmIngresoCorreo vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnRecuperarContrasena.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnRecuperarContrasena) {
            correoEnviado = vista.txtRecuperarContrasena.getText();

            // Validar el formato del correo
            if (!esCorreoValido(correoEnviado)) {
                JOptionPane.showMessageDialog(vista, "Por favor ingrese un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que el correo no esté vacío
            if (!validarEntradas(correoEnviado)) {
                return;
            }

            // Verificar si el correo existe en la base de datos
            if (!modelo.verificarCorreoExistente(correoEnviado)) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico no está registrado en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Genera un número aleatorio de 4 dígitos (1000 a 9999)
            String subject = "Recuperación de contraseña";
            String content = "Este es tu código de recuperación: " + numeroAleatorio;
            System.out.println("El código es: " + numeroAleatorio);

            // Envía el correo con el código de recuperación
            EnviarCorreo.enviarCorreo(correoEnviado, subject, content);

            // Cambiar a la ventana de validación del código
            frmValidarCodigo.initFrmValidarCodigo();
            vista.dispose();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(vista, "Se ha enviado un código de recuperación a tu correo.");
        }
    }

    /**
     * Método para validar los campos de entrada del formulario.
     * @return true si todas las validaciones pasan, false en caso contrario.
     */
    private boolean validarEntradas(String correo) {
        StringBuilder errores = new StringBuilder();

        if (correo.trim().isEmpty()) {
            errores.append("El campo 'Correo electrónico' no puede estar vacío.\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(vista, errores.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Método para validar el formato de un correo electrónico.
     * @param correo El correo a validar.
     * @return true si el correo es válido, false en caso contrario.
     */
    private boolean esCorreoValido(String correo) {
        // Expresión regular para validar el correo electrónico
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, correo);
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
