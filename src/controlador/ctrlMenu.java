package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.frmAdministrarUsuarios;
import vista.frmGestionarEmpresa;
import vista.frmEmpresa;
import vista.frmMenu;
import vista.frmSolicitantes;
import vista.frmTrabajos;
import javax.swing.JPanel;


public class ctrlMenu implements MouseListener {

    frmMenu vista;
    frmGestionarEmpresa frmDashboard;
    frmEmpresa frmEmpresa;
    frmSolicitantes frmSolicitantes;
    frmTrabajos frmTrabajos;
    frmAdministrarUsuarios frmAdministrarUsuarios;

    public ctrlMenu(frmMenu vista, frmGestionarEmpresa frmDashboard, frmAdministrarUsuarios frmAdministrarUsuarios, 
                    frmEmpresa frmEmpresa, frmSolicitantes frmSolicitantes, frmTrabajos frmTrabajos) {
        this.vista = vista;
        this.frmDashboard = frmDashboard;
        this.frmAdministrarUsuarios = frmAdministrarUsuarios;
        this.frmEmpresa = frmEmpresa;
        this.frmSolicitantes = frmSolicitantes;
        this.frmTrabajos = frmTrabajos;

        // Agregar los listeners a los botones
        vista.btnHome.addMouseListener(this);
        vista.btnAdd.addMouseListener(this);
        vista.btnEmpresa.addMouseListener(this);
        vista.btnsolicitantes.addMouseListener(this);
        vista.btnTrabajos.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAdd) {
            // Inicialización perezosa (solo se crea la primera vez)
            if (frmAdministrarUsuarios == null) {
                frmAdministrarUsuarios = new frmAdministrarUsuarios();
                frmAdministrarUsuarios.initfrmADMIN();
            }
            // Cambiar el panel contenido
            cambiarPanel(vista.jPContenedor, frmAdministrarUsuarios);
        }

        if (e.getSource() == vista.btnHome) {
             if (frmDashboard == null) {
                frmDashboard = new frmGestionarEmpresa();
                frmDashboard.initDashboard();
            }
            // Cambiar el panel contenido
            cambiarPanel(vista.jPContenedor, frmDashboard);
            
         
        }

        if (e.getSource() == vista.btnEmpresa) {
            cambiarPanel(vista.jPContenedor, frmEmpresa);
        }

        if (e.getSource() == vista.btnsolicitantes) {
            cambiarPanel(vista.jPContenedor, frmSolicitantes);
        }

        if (e.getSource() == vista.btnTrabajos) {
            cambiarPanel(vista.jPContenedor, frmTrabajos);
        }
    }

    // Método reutilizable para cambiar el panel
    private void cambiarPanel(JPanel contenedor, JPanel nuevoPanel) {
        contenedor.removeAll();  // Elimina todos los componentes actuales
        contenedor.add(nuevoPanel);  // Añade el nuevo panel
        contenedor.revalidate();  // Refresca el contenedor
        contenedor.repaint();  // Repinta el contenedor
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
