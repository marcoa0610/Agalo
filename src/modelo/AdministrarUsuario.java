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

    public String getIDadmin() {
        return IDadmin;
    }

    public void setIDadmin(String IDadmin) {
        this.IDadmin = IDadmin;
    }
    private String IDadmin;
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
        modeloDeDatos.setColumnIdentifiers(new Object[]{"IdAdmin","Nombre", "Usuario", "CorreoElectronico"});
        try (Connection conexion = ClaseConexion.getConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM UsuarioEscritorio")) {
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{
                    rs.getInt("IdAdmin"),
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
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "UPDATE UsuarioEscritorio set nombre= ?, usuario = ?, contrasena = ?, correoelectronico = ?  where IDadmin = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getContrasena());
                updateUser.setString(4, getCorreoElectronico());
                updateUser.executeUpdate(miUUId);

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    // Eliminar usuario
    public void Eliminar(JTable jtbAdmin) {
       Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = jtbAdmin.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from UsuarioEscritorio where IDadmin = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
       
        
    }

    // Cargar los datos del usuario seleccionado desde la tabla
    public void cargarDatosTabla(frmAdministrarUsuarios vista) {
        // Cargar datos de la tabla seleccionada
        int filaSeleccionada = vista.jtbAdmin.getSelectedRow();
        if(filaSeleccionada != -1){
        String nombre = vista.jtbAdmin.getValueAt(filaSeleccionada, 0).toString();
        String usuario = vista.jtbAdmin.getValueAt(filaSeleccionada, 1).toString();
        String correo = vista.jtbAdmin.getValueAt(filaSeleccionada, 2).toString();
        
        vista.txtNombreAdmin.setText(nombre);
        vista.txtUsuarioAdmin.setText(usuario);
        vista.txtCorreoAdmin.setText(correo);
        }
        
    }
}