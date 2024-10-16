/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Contr
 */
public class AdministrarEmpleadores {

    private String correoEmpleador;
    private String uuidEmpleador;

    public String getCorreoEmpleador() {
        return correoEmpleador;
    }

    public void setCorreoEmpleador(String correoEmpleador) {
        this.correoEmpleador = correoEmpleador;
    }

    public String getUuidEmpleador() {
        return uuidEmpleador;
    }

    public void setUuidEmpleador(String uuidEmpleador) {
        this.uuidEmpleador = uuidEmpleador;
    }

    public void restringirEmpleador(JTable tabla) {
        // Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        // Obtenemos qué fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtenemos el id de la fila seleccionada
            String IdEmpleador = tabla.getValueAt(filaSeleccionada, 0).toString(); // El IdSolicitante está en la primera columna
            try {
                // Ejecutamos la Query
                PreparedStatement updateEmpleador = conexion.prepareStatement("UPDATE Empleador SET Estado = 'Restringido' WHERE IdEmpleador = ?");
                updateEmpleador.setString(1, IdEmpleador);
                updateEmpleador.executeUpdate();
            } catch (Exception e) {
                System.out.println("Este es el error en el método de restringir: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ningún Empleador.");
        }
    }

    public void buscarEmpleador(JTable jtbAdmin, JTextField txtBuscarEmpleador) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = (DefaultTableModel) jtbAdmin.getModel(); // Reutiliza el modelo existente

        // Limpia el modelo antes de llenarlo con los nuevos resultados
        modelo.setRowCount(0); // Limpia las filas existentes

        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT e.IdEmpleador as Id, e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, "
                    + "e.CorreoElectronico as Correo, e.NumeroTelefono as Teléfono, e.Direccion as Dirección, "
                    + "d.Nombre AS Departamento "
                    + "FROM Empleador e "
                    + "INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento "
                    + "WHERE e.Estado = 'Activo' AND e.NombreRepresentante LIKE ?"
            ); // Asegúrate de que el nombre de la columna sea correcto
            ps.setString(1, txtBuscarEmpleador.getText() + "%"); // Agregar el '%' aquí
            ResultSet rs = ps.executeQuery();

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modelo.addRow(new Object[]{
                    rs.getString("Id"),
                    rs.getString("Empresa"),
                    rs.getString("Representante"),
                    rs.getString("Correo"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("Departamento")
                });
            }

            // Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en buscar Empleador: " + e);
        }
    }

    public void MostrarEmpleadores(JTable jtSolicitudEmpleador) {

        // Creamos una variable de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        // Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{
            "Id", "Empresa", "Representante", "Correo Electrónico", "Teléfono", "Dirección", "Departamento"
        });

        try {
            // Creamos un Statement
            Statement statement = conexion.createStatement();
            // Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(
                    "SELECT e.IdEmpleador as Id,e.NombreEmpresa as Empresa, e.NombreRepresentante as Representante, e.CorreoElectronico as Correo,e.NumeroTelefono as Teléfono, e.Direccion as Dirección, d.Nombre AS Departamento FROM Empleador e INNER JOIN DEPARTAMENTO d ON e.IdDepartamento = d.IdDepartamento WHERE e.Estado = 'Activo'"
            );

            // Recorremos el ResultSet
            while (rs.next()) {
                // Llenamos el modelo por cada vez que recorremos el ResultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("Id"),
                    rs.getString("Empresa"),
                    rs.getString("Representante"),
                    rs.getString("Correo"),
                    rs.getString("Teléfono"),
                    rs.getString("Dirección"),
                    rs.getString("Departamento")
                });
            }
            // Asignamos el nuevo modelo lleno a la tabla
            jtSolicitudEmpleador.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, método mostrar " + e);
        }
    }

}
