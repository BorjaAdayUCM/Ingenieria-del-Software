package conexionSQLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Swing.Utils;

/**
 * Clase DataBaseConnect
 */
public class DataBaseConnect {

	/**
	 * Crea la coneccion del programa a la database.
	 *
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
			String usuario = "BorjaAday";
			String password = "r2woR7cVEj47rcew";
			Connection cnx = DriverManager.getConnection(myDB, usuario, password);
			return cnx;
		} catch (SQLException | ClassNotFoundException e) {
			if (e instanceof SQLException)
				Utils.dialogoError("Error al conectarse con la base de datos.");
			else if (e instanceof ClassNotFoundException)
				Logger.getLogger("").log(Level.SEVERE, null, e);
		}
		return null;
	}

}
