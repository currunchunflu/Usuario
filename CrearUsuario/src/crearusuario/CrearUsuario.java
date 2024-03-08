package crearusuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CrearUsuario {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/EvidenciaProyecto";
        String user = "root";
        String password = "123456789";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            registrarUsuario(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registrarUsuario(Connection connection) throws SQLException {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del usuario:");
        String correoElectronico = JOptionPane.showInputDialog("Ingrese el correo electrónico del usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");

        // Proporciona un valor predeterminado para la columna Rol
        String rol = "Cliente";

        // Proporciona un valor predeterminado para la columna Informacion_ID_Informacion
        int informacionID = 1; // Cambia esto según tus necesidades

        String insertQuery = "INSERT INTO usuarios (Nombre, Apellido, correoElectronico, Contraseña, Rol, Informacion_ID_Informacion) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, correoElectronico);
            preparedStatement.setString(4, contraseña);
            preparedStatement.setString(5, rol);
            preparedStatement.setInt(6, informacionID);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el usuario.");
            }
        }
    }
}
