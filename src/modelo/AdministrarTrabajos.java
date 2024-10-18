package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdministrarTrabajos {

    private int IdTrabajo;
    private String Titulo;
    private String IdEmpleador;
    private int IdAreaDeTrabajo;
    private String Descripcion;
    private String Altitud;
    private String Latitud;
    private String Direccion;
    private int IdDepartamento;
    private String Experiencia;
    private String Requerimientos;
    private String Estado;
    private double Salario;
    private String Beneficios;
    private String FechaDePublicacion;

    // Getters y setters para cada campo
    public int getIdTrabajo() {
        return IdTrabajo;
    }

    public void setIdTrabajo(int IdTrabajo) {
        this.IdTrabajo = IdTrabajo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getIdEmpleador() {
        return IdEmpleador;
    }

    public void setIdEmpleador(String IdEmpleador) {
        this.IdEmpleador = IdEmpleador;
    }

    public int getIdAreaDeTrabajo() {
        return IdAreaDeTrabajo;
    }

    public void setIdAreaDeTrabajo(int IdAreaDeTrabajo) {
        this.IdAreaDeTrabajo = IdAreaDeTrabajo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getAltitud() {
        return Altitud;
    }

    public void setAltitud(String Altitud) {
        this.Altitud = Altitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String Latitud) {
        this.Latitud = Latitud;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int IdDepartamento) {
        this.IdDepartamento = IdDepartamento;
    }

    public String getExperiencia() {
        return Experiencia;
    }

    public void setExperiencia(String Experiencia) {
        this.Experiencia = Experiencia;
    }

    public String getRequerimientos() {
        return Requerimientos;
    }

    public void setRequerimientos(String Requerimientos) {
        this.Requerimientos = Requerimientos;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double Salario) {
        this.Salario = Salario;
    }

    public String getBeneficios() {
        return Beneficios;
    }

    public void setBeneficios(String Beneficios) {
        this.Beneficios = Beneficios;
    }

    public String getFechaDePublicacion() {
        return FechaDePublicacion;
    }

    public void setFechaDePublicacion(String FechaDePublicacion) {
        this.FechaDePublicacion = FechaDePublicacion;
    }

    // Método para guardar el trabajo
    public void Guardar() {
        try (Connection conexion = ClaseConexion.getConexion()) {
            PreparedStatement addTrabajo = conexion.prepareStatement(
                "INSERT INTO TRABAJO (Titulo, IdEmpleador, IdAreaDeTrabajo, Descripcion, Altitud, Latitud, Direccion, IdDepartamento, Experiencia, Requerimientos, Estado, Salario, Beneficios, FechaDePublicacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            addTrabajo.setString(1, getTitulo());
            addTrabajo.setString(2, getIdEmpleador());
            addTrabajo.setInt(3, getIdAreaDeTrabajo());
            addTrabajo.setString(4, getDescripcion());
            addTrabajo.setString(5, getAltitud());
            addTrabajo.setString(6, getLatitud());
            addTrabajo.setString(7, getDireccion());
            addTrabajo.setInt(8, getIdDepartamento());
            addTrabajo.setString(9, getExperiencia());
            addTrabajo.setString(10, getRequerimientos());
            addTrabajo.setString(11, getEstado());
            addTrabajo.setDouble(12, getSalario());
            addTrabajo.setString(13, getBeneficios());
            addTrabajo.setString(14, getFechaDePublicacion());

            addTrabajo.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en el método Guardar: " + ex.getMessage());
        }
    }

    // Método para mostrar trabajos en una tabla
    public void Mostrar(JTable jtbTrabajos) {
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"IdTrabajo", "Titulo", "IdEmpleador", "Descripcion", "Salario", "Estado"});
        try (Connection conexion = ClaseConexion.getConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM TRABAJO")) {
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{
                    rs.getInt("IdTrabajo"),
                    rs.getString("Titulo"),
                    rs.getString("IdEmpleador"),
                    rs.getString("Descripcion"),
                    rs.getDouble("Salario"),
                    rs.getString("Estado")
                });
            }
            jtbTrabajos.setModel(modeloDeDatos);
        } catch (SQLException e) {
            System.out.println("Error en el método Mostrar: " + e.getMessage());
        }
    }

    // Método para actualizar un trabajo
    public void Actualizar(JTable jtbTrabajos) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = jtbTrabajos.getSelectedRow();

        if (filaSeleccionada != -1) {
            int idTrabajo = Integer.parseInt(jtbTrabajos.getValueAt(filaSeleccionada, 0).toString());

            try {
                String sql = "UPDATE TRABAJO SET Titulo = ?, Descripcion = ?, Salario = ?, Estado = ? WHERE IdTrabajo = ?";
                PreparedStatement updateTrabajo = conexion.prepareStatement(sql);

                updateTrabajo.setString(1, getTitulo());
                updateTrabajo.setString(2, getDescripcion());
                updateTrabajo.setDouble(3, getSalario());
                updateTrabajo.setString(4, getEstado());
                updateTrabajo.setInt(5, idTrabajo);

                updateTrabajo.executeUpdate();
                System.out.println("Trabajo actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el trabajo: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }

    // Método para eliminar un trabajo
    public void Eliminar(JTable jtbTrabajos) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = jtbTrabajos.getSelectedRow();

        if (filaSeleccionada != -1) {
            int idTrabajo = Integer.parseInt(jtbTrabajos.getValueAt(filaSeleccionada, 0).toString());

            try {
                String sql = "DELETE FROM TRABAJO WHERE IdTrabajo = ?";
                PreparedStatement deleteTrabajo = conexion.prepareStatement(sql);
                deleteTrabajo.setInt(1, idTrabajo);
                deleteTrabajo.executeUpdate();
                System.out.println("Trabajo eliminado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar el trabajo: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
}
