package inicioSesion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class InicioSesion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/EvidenciaProyecto";
        String user = "root";
        String password = "123456789";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String usuarioLogin = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
            String contraseñaLogin = JOptionPane.showInputDialog("Ingrese la contraseña:");

            if (iniciarSesion(connection, usuarioLogin, contraseñaLogin)) {
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para iniciar sesión
    public static boolean iniciarSesion(Connection connection, String usuario, String contraseña) throws SQLException {
        String sqlConsulta = "SELECT * FROM usuarios WHERE Nombre = ? AND Contraseña = ?";
        try (PreparedStatement statementConsulta = connection.prepareStatement(sqlConsulta)) {
            statementConsulta.setString(1, usuario);
            statementConsulta.setString(2, contraseña);

            ResultSet resultSet = statementConsulta.executeQuery();

            return resultSet.next();
        }
    }
}
