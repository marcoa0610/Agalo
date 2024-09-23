package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.AdministrarEmpresas; // Asegúrate de tener este modelo
import vista.Dashboard; // Asegúrate de importar la vista correcta

public class ctrlAdministrarEmpresas implements MouseListener, KeyListener {

    // 1- Mandar a llamar a las otras capas (modelo y vista)
    private AdministrarEmpresas modelo;
    private Dashboard vista;

    // 2- Crear el constructor
    public ctrlAdministrarEmpresas(AdministrarEmpresas modelo, Dashboard vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Agregar listeners a los botones
        vista.btnAceptarSolicitud.addMouseListener(this);
        vista.btnRechazarSolicitud.addMouseListener(this);
        vista.txtBuscarEmpresa.addKeyListener(this);

        // Cargar los datos al inicializar
        modelo.MostrarEmpresas(vista.jtSolicitudEmpresa); // Asegúrate de que este método esté en tu modelo
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAceptarSolicitud) {
            int filaSeleccionada = vista.jtSolicitudEmpresa.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Asumiendo que el ID de la empresa está en la primera columna (índice 0)
                modelo.actualizarEstadoActivo(vista.jtSolicitudEmpresa);
                modelo.MostrarEmpresas(vista.jtSolicitudEmpresa); // Actualizar la tabla después de la modificación
            }
        } else if (e.getSource() == vista.btnRechazarSolicitud) {
            modelo.rechazarEmpresa(vista.jtSolicitudEmpresa);
            modelo.MostrarEmpresas(vista.jtSolicitudEmpresa); // Actualizar la tabla después de la eliminación
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
        if (e.getSource() == vista.txtBuscarEmpresa) {
            modelo.buscarEmpresa(vista.jtSolicitudEmpresa, vista.txtBuscarEmpresa);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
