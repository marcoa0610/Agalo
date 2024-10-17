package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioEscritorio {

    private String Nombre;
    private String Usuario;
    private String Correo;
    private String Contrasena;
    private String Edad_Escritorio;
    private String Ciudad_Escritorio;
    private String Pais_Escritorio;
    private int IdRol;

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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getEdad_Escritorio() {
        return Edad_Escritorio;
    }

    public void setEdad_Escritorio(String Edad_Escritorio) {
        this.Edad_Escritorio = Edad_Escritorio;
    }

    public String getCiudad_Escritorio() {
        return Ciudad_Escritorio;
    }

    public void setCiudad_Escritorio(String Ciudad_Escritorio) {
        this.Ciudad_Escritorio = Ciudad_Escritorio;
    }

    public String getPais_Escritorio() {
        return Pais_Escritorio;
    }

    public void setPais_Escritorio(String Pais_Escritorio) {
        this.Pais_Escritorio = Pais_Escritorio;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    // Guardar usuario en la base de datos
    public void GuardarUsuario() throws SQLException {
        Connection conexion = ClaseConexion.getConexion();
        if (conexion == null) {
            throw new SQLException("No se pudo establecer conexión con la base de datos.");
        }
        PreparedStatement addUsuarioEscritorio = null;
        PreparedStatement checkCorreo = null;
        ResultSet rs = null;

        try {
            String sqlCheck = "SELECT COUNT(*) FROM UsuarioEscritorio WHERE CorreoElectronico = ?";
            checkCorreo = conexion.prepareStatement(sqlCheck);
            checkCorreo.setString(1, getCorreo());
            rs = checkCorreo.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                throw new SQLException("El correo ya está registrado.");
            } else {
                String sql = "INSERT INTO UsuarioEscritorio (Nombre, Usuario, CorreoElectronico, Contrasena, Pais_Escritorio, Edad_Escritorio, Ciudad_Escritorio, idrol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                addUsuarioEscritorio = conexion.prepareStatement(sql);
                addUsuarioEscritorio.setString(1, getNombre());
                addUsuarioEscritorio.setString(2, getUsuario());
                addUsuarioEscritorio.setString(3, getCorreo());
                addUsuarioEscritorio.setString(4, getContrasena());
                addUsuarioEscritorio.setString(5, getPais_Escritorio());
                addUsuarioEscritorio.setString(6, getEdad_Escritorio());
                addUsuarioEscritorio.setString(7, getCiudad_Escritorio());
                addUsuarioEscritorio.setInt(8, 1); // Rol por defecto
                addUsuarioEscritorio.executeUpdate();
                System.out.println("Usuario guardado correctamente.");
            }

        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    // Obtener rol del usuario por correo y contraseña
    public int obtenerRol(String correo, String contrasena) {
        String query = "SELECT idRol FROM UsuarioEscritorio WHERE CorreoElectronico = ? AND Contrasena = ?";
        try (Connection conexion = ClaseConexion.getConexion();
             PreparedStatement pst = conexion.prepareStatement(query)) {
            pst.setString(1, correo);
            pst.setString(2, contrasena);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idRol");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    // Método para iniciar sesión
    public boolean iniciarSesion() {
        Connection conexion = ClaseConexion.getConexion();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean resultado = false;

        try {
            String sql = "SELECT * FROM UsuarioEscritorio WHERE CorreoElectronico = ? AND Contrasena = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, getCorreo());
            statement.setString(2, getContrasena());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.IdRol = resultSet.getInt("IdRol");
                resultado = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error en el método iniciarSesion: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return resultado;
    }

    // Actualizar contraseña
    public void actualizar_contra(String correo, String contrasena) {
        Connection con = null;
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            con = ClaseConexion.getConexion();
            String sqlSelect = "SELECT COUNT(*) FROM UsuarioEscritorio WHERE correoelectronico = ?";
            query = con.prepareStatement(sqlSelect);
            query.setString(1, correo);

            rs = query.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                String sqlUpdate = "UPDATE UsuarioEscritorio SET contrasena = ? WHERE correoelectronico = ?";
                query = con.prepareStatement(sqlUpdate);
                query.setString(1, contrasena);
                query.setString(2, correo);

                int filasActualizadas = query.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Contraseña actualizada correctamente.");
                } else {
                    System.out.println("Error al actualizar la contraseña.");
                }
            } else {
                System.out.println("No se encontró el usuario con ese correo.");
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Verificar si el correo ya existe
    public boolean verificarCorreoExistente(String correo) {
        try {
            Connection con = ClaseConexion.getConexion();
            String sql = "SELECT COUNT(*) FROM UsuarioEscritorio WHERE correoelectronico = ?";
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, correo);

            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el correo: " + e.getMessage());
        }
        return false;
    }
}
