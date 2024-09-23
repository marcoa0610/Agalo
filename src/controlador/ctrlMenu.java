package controlador;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.*;

public class ctrlMenu implements MouseListener {

    private frmMenu vista;
    private CardLayout cardLayout;

    // Constructor con todas las vistas
    public ctrlMenu(frmMenu vista) {
        this.vista = vista;
        this.cardLayout = (CardLayout) vista.jPCpntenedor.getLayout(); // Asegúrate de usar CardLayout

        // Añadir MouseListeners a los botones
        vista.btnHome.addMouseListener(this);
        vista.btnAdministrador.addMouseListener(this);
        vista.btnEmpresa.addMouseListener(this);
        vista.btnSolicitante.addMouseListener(this);
        vista.btnTrabajos.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Navegación entre diferentes vistas usando CardLayout
        if (e.getSource() == vista.btnHome) {
            cardLayout.show(vista.jPCpntenedor, "Dashboard");
        } else if (e.getSource() == vista.btnAdministrador) {
            cardLayout.show(vista.jPCpntenedor, "AdministrarUsuarios");
        } else if (e.getSource() == vista.btnEmpresa) {
            cardLayout.show(vista.jPCpntenedor, "Empresa");
        } else if (e.getSource() == vista.btnSolicitante) {
            cardLayout.show(vista.jPCpntenedor, "Solicitantes");
        } else if (e.getSource() == vista.btnTrabajos) {
            cardLayout.show(vista.jPCpntenedor, "Trabajos");
        }
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
