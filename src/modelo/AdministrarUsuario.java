package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmAdministrarUsuarios;

public class AdministrarUsuario {
    // Parámetros
    private String Nombre;
    private String Usuario;
    private String Contrasena;
    private String CorreoElectronico;  
    
    // Getters y Setters
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }
    
    // Métodos 
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            // Query corregida: eliminación del valor adicional en los parámetros
            PreparedStatement addAdmin = conexion.prepareStatement(
                "INSERT INTO UsuarioEscritorio (Nombre, Usuario, Contrasena, CorreoElectronico) VALUES (?, ?, ?, ?)"
            );
            addAdmin.setString(1, getNombre());
            addAdmin.setString(2, getUsuario());
            addAdmin.setString(3, getContrasena());
            addAdmin.setString(4, getCorreoElectronico());

            addAdmin.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("Error en el método Guardar: " + ex);
        }
    }

    public void Mostrar(JTable jtbAdmin) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"Nombre", "Usuario", "CorreoElectronico"});
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM UsuarioEscritorio"); // Query corregida
            
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("Nombre"), 
                    rs.getString("Usuario"), 
                    rs.getString("CorreoElectronico")
                });
            }
            jtbAdmin.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Error en el método Mostrar: " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                PreparedStatement deleteAdmin = conexion.prepareStatement("DELETE FROM UsuarioEscritorio WHERE IdAdmin = ?");
                deleteAdmin.setString(1, miId);
                deleteAdmin.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error en el método Eliminar: " + e);
            }
        } else {
            System.out.println("Debe seleccionar una fila para eliminar.");
        }
    }

    public void cargarDatosTabla(frmAdministrarUsuarios vista) {
        int filaSeleccionada = vista.jtbAdmin.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = vista.jtbAdmin.getValueAt(filaSeleccionada, 0).toString();
            String usuario = vista.jtbAdmin.getValueAt(filaSeleccionada, 1).toString();
            String correo = vista.jtbAdmin.getValueAt(filaSeleccionada, 2).toString();

            vista.txtNombreAdmin.setText(nombre);
            vista.txtUsuarioAdmin.setText(usuario);
            vista.txtCorreoAdmin.setText(correo);
        }
    }

    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            String miID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                // Se agregó la cláusula WHERE
                PreparedStatement updateUser = conexion.prepareStatement(
                    "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, CorreoElectronico = ? WHERE IdAdmin = ?"
                );
                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getCorreoElectronico());
                updateUser.setString(4, miID);

                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error en el método Actualizar: " + e);
            }
        } else {
            System.out.println("Debe seleccionar una fila para actualizar.");
        }
    }

    public void limpiar(frmAdministrarUsuarios vista) {
        vista.txtNombreAdmin.setText("");
        vista.txtUsuarioAdmin.setText("");
        vista.txtCorreoAdmin.setText("");
    }
}
