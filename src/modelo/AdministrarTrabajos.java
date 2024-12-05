package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdministrarTrabajos {

    // Atributos del trabajo
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

    // Getters y setters
    // (No incluidos aquí por brevedad, puedes copiarlos de tu versión original)

    // Método para mostrar los trabajos en una tabla
    public void Mostrar(JTable jtbTrabajos) {
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{
            "IdTrabajo", "Titulo", "IdEmpleador", "Descripcion", "Salario", "Estado"
        });

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

    // Método para generar un reporte del trabajo seleccionado
    public void GenerarReporte(int idTrabajo) {
        try (Connection conexion = ClaseConexion.getConexion()) {
            String query = "SELECT * FROM TRABAJO WHERE IdTrabajo = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idTrabajo);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println("Generando reporte para el trabajo:");
                System.out.println("ID: " + rs.getInt("IdTrabajo"));
                System.out.println("Título: " + rs.getString("Titulo"));
                System.out.println("Descripción: " + rs.getString("Descripcion"));
                System.out.println("Salario: " + rs.getDouble("Salario"));
                System.out.println("Estado: " + rs.getString("Estado"));
                // Aquí podrías agregar más lógica para exportar a PDF o generar un reporte más formal.
            } else {
                System.out.println("No se encontró el trabajo con ID: " + idTrabajo);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
