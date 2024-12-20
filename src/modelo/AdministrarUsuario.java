package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
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
                    "INSERT INTO UsuarioEscritorio (Nombre, Usuario, Contrasena, CorreoElectronico, IdRol) VALUES (?, ?, ?, ?, ?)"
            );
            addAdmin.setString(1, getNombre());
            addAdmin.setString(2, getUsuario());
            addAdmin.setString(3, getContrasena());
            addAdmin.setString(4, getCorreoElectronico());
            addAdmin.setInt(5, 1);

            addAdmin.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en el método Guardar: " + ex.getMessage());
        }
    }

    public void Mostrar(JTable jtbAdmin) {
        // Creamos una variable de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        // Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            "IdAdmin", "Nombre", "Usuario", "CorreoElectronico"
        });

        try {
            // Creamos un Statement
            Statement statement = conexion.createStatement();

            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(
                    "select IdAdmin, Nombre, Usuario, CorreoElectronico from usuarioescritorio where idrol = 1"
            );

              // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getInt("IdAdmin"),
                    rs.getString("Nombre"),
                    rs.getString("Usuario"),
                    rs.getString("CorreoElectronico")
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modeloDeDatos);
        } catch (SQLException e) {
            System.out.println("Error en el método Mostrar: " + e.getMessage());
        }
    }

    // Actualizar usuario en la base de datos
    public void Actualizar(JTable jtbAdmin) {
        // Código de actualización similar al de guardar
        Connection conexion = ClaseConexion.getConexion();

        // Obtener la fila seleccionada
        int filaSeleccionada = jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el ID del administrador de la fila seleccionada
            String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                // Sentencia SQL para actualizar
                String sql = "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, Contrasena = ?, CorreoElectronico = ? WHERE IdAdmin = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                // Asignar los nuevos valores al PreparedStatement
                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getContrasena());
                updateUser.setString(4, getCorreoElectronico());
                updateUser.setString(5, miUUId);  // Aquí se pasa el ID como último parámetro

                // Ejecutar la actualización
                updateUser.executeUpdate();

                System.out.println("Usuario actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el usuario: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }

    // Actualizar usuario en la base de datos
    public void ActualizarSinContrasena(JTable jtbAdmin) {
        // Código de actualización similar al de guardar
        Connection conexion = ClaseConexion.getConexion();

        // Obtener la fila seleccionada
        int filaSeleccionada = jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el ID del administrador de la fila seleccionada
            String miUUId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                // Sentencia SQL para actualizar
                String sql = "UPDATE UsuarioEscritorio SET Nombre = ?, Usuario = ?, CorreoElectronico = ? WHERE IdAdmin = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                // Asignar los nuevos valores al PreparedStatement
                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getCorreoElectronico());
                updateUser.setString(4, miUUId);  // Aquí se pasa el ID como último parámetro

                // Ejecutar la actualización
                updateUser.executeUpdate();

                System.out.println("Usuario actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el usuario: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }

    // Eliminar usuario
    public void Eliminar(JTable jtbAdmin) {
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = jtbAdmin.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        //borramos 
        if (filaSeleccionada != -1) {
            // Obtenemos el IdEmpleador de la fila seleccionada
            String miId = jtbAdmin.getValueAt(filaSeleccionada, 0).toString();

            try {
                String sql = "delete from UsuarioEscritorio where IDadmin = ?";
                PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
                deleteEstudiante.setString(1, miId);
                deleteEstudiante.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error metodo de eliminar" + e);
            }
        } else {
            System.out.println("No se ha seleccionado ningun Usuario.");
        }

    }
    public void buscarUsuario(JTable jtbAdmin, JTextField txtBuscarUsuario) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "select IdAdmin, Nombre, Usuario, CorreoElectronico from usuarioescritorio where idrol = 1 AND Nombre LIKE ? "
            );
            ps.setString(1, txtBuscarUsuario.getText() + "%"); // Agregar el '%' aquí
            ResultSet rs = ps.executeQuery();

             // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modelo.addRow(new Object[]{
                    rs.getInt("IdAdmin"),
                    rs.getString("Nombre"),
                    rs.getString("Usuario"),
                    rs.getString("CorreoElectronico")
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en buscar Usuario: " + e);
        }
    }
    

    // Cargar los datos del usuario seleccionado desde la tabla
    public void cargarDatosTabla(frmAdministrarUsuarios vista) {
        int filaSeleccionada = vista.jtbAdmin.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener los valores de las columnas correspondientes
            String nombre = vista.jtbAdmin.getValueAt(filaSeleccionada, 1).toString(); // Columna Nombre
            String usuario = vista.jtbAdmin.getValueAt(filaSeleccionada, 2).toString(); // Columna Usuario
            String correo = vista.jtbAdmin.getValueAt(filaSeleccionada, 3).toString(); // Columna CorreoElectronico

            // Asignar los valores a los campos de texto en la vista
            vista.txtNombreAdmin.setText(nombre);
            vista.txtUsuarioAdmin.setText(usuario);
            vista.txtCorreoAdmin.setText(correo);
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }

    }
}
