/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Contr
 */
public class AdministrarSolicitantes {

    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public String getUuidSolicitante() {
        return uuidSolicitante;
    }

    public void setUuidSolicitante(String uuidSolicitante) {
        this.uuidSolicitante = uuidSolicitante;
    }

    private String correoSolicitante;
    private String uuidSolicitante;

    public void restringirSolicitante(JTable tabla) {
        // Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        // Obtenemos qué fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtenemos el id de la fila seleccionada
            String idSolicitante = tabla.getValueAt(filaSeleccionada, 0).toString(); // El IdSolicitante está en la primera columna
            try {
                // Ejecutamos la Query
                PreparedStatement updateSolicitante = conexion.prepareStatement("UPDATE solicitante SET EstadoCuenta = 'Restringido' WHERE IdSolicitante = ?");
                updateSolicitante.setString(1, idSolicitante);
                updateSolicitante.executeUpdate();
            } catch (Exception e) {
                System.out.println("Este es el error en el método de restringir: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ningún solicitante.");
        }
    }

    public void buscarSolicitante(JTable jtbAdmin, JTextField txtBuscarSolicitante) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT s.IdSolicitante as Id, s.Nombre as NombreSolicitante, s.CorreoElectronico as Correo, "
                    + "s.Telefono as Teléfono, s.Direccion as Dirección, d.Nombre AS NombreDepartamento "
                    + "FROM Solicitante s "
                    + "INNER JOIN DEPARTAMENTO d ON s.IdDepartamento = d.IdDepartamento "
                    + "WHERE s.EstadoCuenta = 'Activo' AND s.Nombre LIKE ? " // Especifica la tabla para el nombre
            );
            ps.setString(1, txtBuscarSolicitante.getText() + "%"); // Agregar el '%' aquí
            ResultSet rs = ps.executeQuery();

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modelo.addRow(new Object[]{
                    rs.getString("Id"),
                    rs.getString("NombreSolicitante"), // Usar el alias para evitar ambigüedad
                    rs.getString("Correo"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("NombreDepartamento") // Usar el alias para evitar ambigüedad
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en buscar Solicitante: " + e);
        }
    }

    public void MostrarSolicitantes(JTable jtSolicitudSolicitante) {
        // Creamos una variable de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        // Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            "Id", "Nombre", "Correo Electrónico", "Teléfono", "Dirección", "Departamento"
        });

        try {
            // Creamos un Statement
            Statement statement = conexion.createStatement();
            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(
                    "SELECT s.IdSolicitante as Id, s.Nombre as NombreSolicitante, s.CorreoElectronico as Correo, "
                    + "s.Telefono as Teléfono, s.Direccion as Dirección, d.Nombre AS NombreDepartamento "
                    + "FROM Solicitante s "
                    + "INNER JOIN DEPARTAMENTO d ON s.IdDepartamento = d.IdDepartamento "
                    + "WHERE s.EstadoCuenta = 'Activo'" // Agregamos la condición aquí
            );

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("Id"),
                    rs.getString("NombreSolicitante"), // Usar el alias para evitar ambigüedad
                    rs.getString("Correo"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("NombreDepartamento") // Usar el alias para evitar ambigüedad
                });
            }
            // Asignamos el nuevo modelo lleno a la tabla
            jtSolicitudSolicitante.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, método mostrar " + e);
        }
    }
}
