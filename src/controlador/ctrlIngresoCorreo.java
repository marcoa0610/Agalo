package controlador;
import java.awt.event.*;
import java.util.Random;
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

            // Genera un número aleatorio de 4 dígitos (1000 a 9999)

            correoEnviado = vista.txtRecuperarContrasena.getText();
            
            String subject = "Recuperación de contraseña";
            String content = "Este es tu código de recuperación: " + numeroAleatorio;
            System.out.println("El codigo es " + numeroAleatorio);

            // Envía el correo con el código de recuperación
            EnviarCorreo.enviarCorreo(correoEnviado, subject, content);
            
            frmValidarCodigo.initFrmValidarCodigo();
            vista.dispose();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(vista, "Se ha enviado un código de recuperación a tu correo.");
        }
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