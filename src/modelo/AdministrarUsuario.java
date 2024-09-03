package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.configuraciones;

public class AdministrarUsuario {
    //Parametros
    private String IdAdmin;
    private String Nombre;
    private String Usuario;
    private String Contrasena;
    private String CorreoElectronico;  
    
    
    //Getters Y Setters

    public String getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(String IdAdmin) {
        this.IdAdmin = IdAdmin;
    }

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
    
    //3- Metodos 
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addAdmin = conexion.prepareStatement("INSERT INTO UsuarioEscritorio(Nombre, Usuario, Contrasena, CorreoElectronico) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addAdmin.setString(1, getNombre());
            addAdmin.setString(2, getUsuario());
            addAdmin.setString(3, getContrasena());
            addAdmin.setString(4, getContrasena());
 
            //Ejecutar la consulta
            addAdmin.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
        
    }
    public void Mostrar(JTable jtbAdmin){
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"Nombre", "Usuario", "Correo"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbVisitas");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{
                    rs.getString("Nombre"), 
                    rs.getString("Usuario"), 
                    rs.getString("CorreoElectronico")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            jtbAdmin.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteAdmin = conexion.prepareStatement("delete from UsuarioEscritorio where IdAdmin = ?");
            deleteAdmin.setString(1, miId);
            deleteAdmin.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     public void cargarDatosTabla(configuraciones vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbAdmin.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String NombreDeTB = vista.jtbAdmin.getValueAt(filaSeleccionada, 1).toString();
            String UsuarioDeTB = vista.jtbAdmin.getValueAt(filaSeleccionada, 2).toString();
            String CorreoElectronicoDeTB = vista.jtbAdmin.getValueAt(filaSeleccionada, 3).toString();

            // Establece los valores en los campos de texto
            vista.txtUsuarioAdmin.setText(NombreDeTB);
            vista.txtNombreAdmin.setText(UsuarioDeTB);
            vista.txtCorreoAdmin.setText(CorreoElectronicoDeTB);
        }
     }
        public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update UsuarioEscritorio set Nombre= ?, Usuario = ?, CorreoElectronico = ? where IdAdmin = ?");

                updateUser.setString(1, getNombre());
                updateUser.setString(2, getUsuario());
                updateUser.setString(3, getCorreoElectronico());
                updateUser.setString(4, miID);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
        }
        public void limpiar(configuraciones vista) {
        vista.txtUsuarioAdmin.setText("");
        vista.txtNombreAdmin.setText("");
        vista.txtCorreoAdmin.setText("");
    }
    }
    
    

