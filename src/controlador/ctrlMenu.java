package controlador;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.*;

public class ctrlMenu implements MouseListener {

   frmMenu vista;
   frmAdministrarUsuarios frmAdministrarUsuarios;
   frmDashboard frmDashboard;
   frmEmpresa frmEmpresa; 
   frmSolicitantes frmSolicitantes; 
   frmTrabajos frmTrabajos;
   
    

    // Constructor con todas las vistas
    public ctrlMenu(frmMenu vista, frmAdministrarUsuarios frmAdministrarUsuarios, frmDashboard frmDashboard, frmEmpresa frmEmpresa, frmSolicitantes frmSolicitantes, frmTrabajos frmTrabajos) {
       this.vista = vista; 
       this.frmAdministrarUsuarios = frmAdministrarUsuarios;
       this.frmDashboard = frmDashboard; 
       this.frmEmpresa = frmEmpresa; 
       this.frmSolicitantes = frmSolicitantes; 
       this.frmTrabajos = frmTrabajos;
       
       vista.btnAdministrador.addMouseListener(this);
       vista.btn
       
       
       
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      
        
        
        
        
        
        
        
        
        
        
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
