/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

/**
 *
 * @author Estudiante
 */
public class configuraciones extends javax.swing.JPanel {

    /**
     * Creates new form configuraciones
     */
    public configuraciones() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCorreoAdmin = new custom.TextField();
        txtContrasenaAdmin = new custom.PasswordField();
        txtUsuarioAdmin = new custom.TextField();
        txtNombreAdmin = new custom.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbAdmin = new javax.swing.JTable();
        btnEliminarAdmin = new custom.Button();
        btnAgregarAdmin = new custom.Button();
        btnEditarAdmin = new custom.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("Administrar Usuarios");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 16, -1, -1));

        txtCorreoAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtCorreoAdmin.setText("Correo Electronico");
        add(txtCorreoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 500, 60));

        txtContrasenaAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtContrasenaAdmin.setText("Contraseña");
        txtContrasenaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaAdminActionPerformed(evt);
            }
        });
        add(txtContrasenaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 500, 60));

        txtUsuarioAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuarioAdmin.setText("Usuario");
        add(txtUsuarioAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 120, 500, 60));

        txtNombreAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreAdmin.setText("Nombre");
        add(txtNombreAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 500, 60));

        jtbAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbAdmin);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, -3, 470, 510));

        btnEliminarAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarAdmin.setText("Eliminar Administrador");
        add(btnEliminarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 610, 190, 60));

        btnAgregarAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarAdmin.setText("Agregar Administrador");
        add(btnAgregarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, 190, 60));

        btnEditarAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarAdmin.setText("Editar Administrador");
        add(btnEditarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 610, 190, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void txtContrasenaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public custom.Button btnAgregarAdmin;
    public custom.Button btnEditarAdmin;
    public custom.Button btnEliminarAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbAdmin;
    public custom.PasswordField txtContrasenaAdmin;
    public custom.TextField txtCorreoAdmin;
    public custom.TextField txtNombreAdmin;
    public custom.TextField txtUsuarioAdmin;
    // End of variables declaration//GEN-END:variables
}