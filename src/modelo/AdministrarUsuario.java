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
    private String Nombre;
    private String Usuario;
    private String Contrasena;
    private String CorreoElectronico;

    // Getters y setters
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

    // Guardar en la base de datos
    public void Guardar() {
        try (Connection conexion = ClaseConexion.getConexion()) {
            PreparedStatement addAdmin = conexion.prepareStatement(
                "INSERT INTO UsuarioEscritorio (Nombre, Usuario, Contrasena, CorreoElectronico) VALUES (?, ?, ?, ?)"
            );
            addAdmin.setString(1, getNombre());
            addAdmin.setString(2, getUsuario());
            addAdmin.setString(3, getContrasena());
            addAdmin.setString(4, getCorreoElectronico());
            addAdmin.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en el método Guardar: " + ex.getMessage());
        }
    }

    // Mostrar datos en la tabla
    public void Mostrar(JTable jtbAdmin) {
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"Nombre", "Usuario", "CorreoElectronico"});
        try (Connection conexion = ClaseConexion.getConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM UsuarioEscritorio")) {
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("Nombre"),
                    rs.getString("Usuario"),
                    rs.getString("CorreoElectronico")
                });
            }
            jtbAdmin.setModel(modeloDeDatos);
        } catch (SQLException e) {
            System.out.println("Error en el método Mostrar: " + e.getMessage());
        }
    }

    // Actualizar usuario en la base de datos
    public void Actualizar(JTable jtbAdmin) {
        // Código de actualización similar al de guardar
    }

    // Eliminar usuario
    public void Eliminar(JTable jtbAdmin) {
        // Código para eliminar un usuario seleccionado en la tabla
    }

    // Cargar los datos del usuario seleccionado desde la tabla
    public void cargarDatosTabla(frmAdministrarUsuarios vista) {
        // Cargar datos de la tabla seleccionada
    }
}
