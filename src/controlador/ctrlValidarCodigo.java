/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import modelo.UsuarioEscritorio;
import vista.frmCambiarContrasena;
import vista.frmIngresoCorreo;
import vista.frmValidarCodigo;

public class ctrlValidarCodigo implements ActionListener {
    private frmValidarCodigo vistaValidacion;
   
    

    public ctrlValidarCodigo(frmValidarCodigo vista) {
        this.vistaValidacion = vista;
        this.vistaValidacion.btnValidar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaValidacion.btnValidar) {
            if (Integer.parseInt(this.vistaValidacion.txtCodigo.getText()) == ctrlIngresoCorreo.numeroAleatorio) {
                frmCambiarContrasena.initFrmCambiarContrasena();
                this.vistaValidacion.dispose();
            }
        }
    }
}