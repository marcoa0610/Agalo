
package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


public class configuraciones extends javax.swing.JPanel {

    
    public configuraciones() {
        
        
        initComponents();
        
        UIManager.getSystemLookAndFeelClassName();
        
        jtbAdmin.setBackground(java.awt.Color.WHITE); // Cambia el fondo de las celdas de la tabla
        jtbAdmin.setFillsViewportHeight(true); // Asegura que el fondo cubra todo el área
        jtbAdmin.getParent().setBackground(java.awt.Color.WHITE);
        
         jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Establecer borde vacío
        
        // Personalizar el encabezado de la tabla
        jtbAdmin.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
        jtbAdmin.getTableHeader().setBackground(java.awt.Color.WHITE);
        jtbAdmin.setRowHeight(45);
        
        
        // Configurar las líneas horizontales de la tabla
        jtbAdmin.setGridColor(new java.awt.Color(230, 230, 230)); // Líneas gris claro
        jtbAdmin.setShowHorizontalLines(true); // Mostrar líneas horizontales
        jtbAdmin.setShowVerticalLines(false); // Ocultar líneas verticales si lo deseas
        
         DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(java.awt.Color.WHITE); // Fondo blanco
        headerRenderer.setForeground(new Color(0, 0, 0)); // Texto en color negro
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        
        for (int i = 0; i < jtbAdmin.getColumnModel().getColumnCount(); i++) {
            jtbAdmin.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        jtbAdmin.setRowHeight(25);
        jtbAdmin.setGridColor(new Color(230, 230, 230));
 
    }
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCorreoAdmin = new custom.TextField();
        txtContrasenaAdmin = new custom.PasswordField();
        txtUsuarioAdmin = new custom.TextField();
        txtNombreAdmin = new custom.TextField();
        btnAgregarAdmin = new custom.Button();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbAdmin = new javax.swing.JTable();
        textField1 = new custom.TextField();
        btnEditarAdmin = new custom.Button();
        btnEliminarAdmin = new custom.Button();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Administrar Usuarios");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        txtCorreoAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtCorreoAdmin.setText("Correo Electronico");
        add(txtCorreoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 650, 50));

        txtContrasenaAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtContrasenaAdmin.setText("Contraseña");
        txtContrasenaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaAdminActionPerformed(evt);
            }
        });
        add(txtContrasenaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 450, 50));

        txtUsuarioAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuarioAdmin.setText("Usuario");
        add(txtUsuarioAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 360, 50));

        txtNombreAdmin.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreAdmin.setText("Nombre");
        txtNombreAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreAdminActionPerformed(evt);
            }
        });
        add(txtNombreAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 290, 50));

        btnAgregarAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarAdmin.setText("Agregar Administrador");
        add(btnAgregarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 190, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtbAdmin.setAutoCreateRowSorter(true);
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
        jtbAdmin.setGridColor(new java.awt.Color(255, 255, 255));
        jtbAdmin.setIntercellSpacing(new java.awt.Dimension(12, 12));
        jtbAdmin.setShowGrid(false);
        jtbAdmin.setShowHorizontalLines(true);
        jtbAdmin.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtbAdmin);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1010, 410));

        textField1.setBackground(new java.awt.Color(255, 252, 250));
        jPanel1.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 520, 50));

        btnEditarAdmin.setBorder(null);
        btnEditarAdmin.setForeground(new java.awt.Color(0, 0, 0));
        btnEditarAdmin.setText("Editar Administrador");
        jPanel1.add(btnEditarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 160, 50));

        btnEliminarAdmin.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarAdmin.setText("Eliminar Administrador");
        jPanel1.add(btnEliminarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 150, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 1060, 490));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img/configs.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtContrasenaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaAdminActionPerformed

    private void txtNombreAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public custom.Button btnAgregarAdmin;
    public custom.Button btnEditarAdmin;
    public custom.Button btnEliminarAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbAdmin;
    private custom.TextField textField1;
    public custom.PasswordField txtContrasenaAdmin;
    public custom.TextField txtCorreoAdmin;
    public custom.TextField txtNombreAdmin;
    public custom.TextField txtUsuarioAdmin;
    // End of variables declaration//GEN-END:variables
}
