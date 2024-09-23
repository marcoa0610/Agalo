package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.AdministrarUsuario;
import vista.frmAdministrarUsuarios;

public class ctrlAdministrarUsuario implements MouseListener, KeyListener {
    
     //1- Mandar a llamar a las otras capas (modelo y vista)
    private AdministrarUsuario modelo;
    private frmAdministrarUsuarios vista;
    
    //2- Crear el constructor
    public ctrlAdministrarUsuario(AdministrarUsuario modelo, frmAdministrarUsuarios vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregarAdmin.addMouseListener(this);   
        vista.btnEditarAdmin.addMouseListener(this);   
        vista.btnEliminarAdmin.addMouseListener(this);   
        modelo.Mostrar(vista.jtbAdmin);
        vista.btnEliminarAdmin.addMouseListener(this);
        vista.btnEditarAdmin.addMouseListener(this);
        vista.btnAgregarAdmin.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(e.getSource() == vista.btnAgregarAdmin){
            modelo.setNombre(vista.txtNombreAdmin.getText());
            modelo.setUsuario(vista.txtUsuarioAdmin.getText());
            modelo.setCorreoElectronico(vista.txtCorreoAdmin.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbAdmin);
        }
        
        if(e.getSource() == vista.btnEliminarAdmin){
            modelo.Eliminar(vista.jtbAdmin);
            modelo.Mostrar(vista.jtbAdmin);
        }
        
        if(e.getSource() == vista.jtbAdmin){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnEditarAdmin){
             modelo.setNombre(vista.txtNombreAdmin.getText());
            modelo.setUsuario(vista.txtUsuarioAdmin.getText());
            modelo.setCorreoElectronico(vista.txtCorreoAdmin.getText());
            
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
