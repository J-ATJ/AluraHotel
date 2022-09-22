package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import views.Exito;
public class huesped {
	public static List<Map<String, String>> listar() throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("SELECT id, nombre, apellido, nacimiento, nacionalidad, telefono, idreserva FROM huesped");
		statement.execute();
		ResultSet resultSetHuesped = statement.getResultSet();
		List<Map<String, String>> datosHuesped = new ArrayList<Map<String, String>>();
		while(resultSetHuesped.next()) {
			Map<String, String> fila = new HashMap<String, String>();		
			fila.put("id", String.valueOf(resultSetHuesped.getInt("id")));
			fila.put("nombre", resultSetHuesped.getString("nombre"));	
			fila.put("apellido", resultSetHuesped.getString("apellido"));
			fila.put("nacimiento", resultSetHuesped.getString("nacimiento")); 
			fila.put("nacionalidad", resultSetHuesped.getString("nacionalidad"));
			fila.put("telefono", resultSetHuesped.getString("telefono"));	
			fila.put("idreserva", String.valueOf(resultSetHuesped.getInt("idreserva")));
			datosHuesped.add(fila);
		}
		conn.close();
		return datosHuesped;
	}
	public static Map<String, String> guardar(Map<String, String> nuevoHuesped) throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("INSERT INTO huesped(nombre, apellido, nacimiento, nacionalidad, telefono, idreserva) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, nuevoHuesped.get("nombre"));
		statement.setString(2, nuevoHuesped.get("apellido"));
		statement.setString(3, nuevoHuesped.get("nacimiento"));
		statement.setString(4, nuevoHuesped.get("nacionalidad"));
		statement.setString(5, nuevoHuesped.get("telefono"));
		statement.setInt(6, Integer.parseInt(nuevoHuesped.get("idreserva")));
		statement.execute();
		Exito mensaje = new Exito();
		mensaje.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mensaje.setVisible(true);
		conn.close();
		return nuevoHuesped;	
	}
	public static Map<String, String> modificar(Map<String, String> modificarHuesped) throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("UPDATE huesped SET nombre = ?, apellido = ?, nacimiento = ?, nacionalidad = ?, telefono = ? WHERE id = ?");
		statement.setString(1, modificarHuesped.get("nombre"));
		statement.setString(2, modificarHuesped.get("apellido"));
		statement.setString(3, modificarHuesped.get("nacimiento"));
		statement.setString(4, modificarHuesped.get("nacionalidad"));
		statement.setString(5, modificarHuesped.get("telefono"));
		statement.setString(5, modificarHuesped.get("telefono"));
		statement.setInt(6, Integer.parseInt(modificarHuesped.get("id")));
		statement.execute();	
		JOptionPane.showMessageDialog(null, "Se modificó exitósamente el huésped con id " + Integer.parseInt(modificarHuesped.get("id")), "OK", JOptionPane.INFORMATION_MESSAGE);		
		conn.close();
		return modificarHuesped;
	}
	public static List<Map<String, String>> buscar(String idBuscar) throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("SELECT id, nombre, apellido, nacimiento, nacionalidad, telefono, idreserva FROM huesped WHERE idreserva = ?");
		statement.setInt(1, Integer.parseInt(idBuscar));
		statement.execute();
		ResultSet resultSetBuscar = statement.getResultSet();
		List<Map<String, String>> datosBuscarH = new ArrayList<Map<String, String>>();	
		while(resultSetBuscar.next()) {
			Map<String, String> fila = new HashMap<String, String>();
			fila.put("id", String.valueOf(resultSetBuscar.getInt("id")));
			fila.put("nombre", resultSetBuscar.getString("nombre"));
			fila.put("apellido", resultSetBuscar.getString("apellido"));
			fila.put("nacimiento", resultSetBuscar.getString("nacimiento"));
			fila.put("nacionalidad", resultSetBuscar.getString("nacionalidad"));
			fila.put("telefono", resultSetBuscar.getString("telefono"));
			fila.put("idreserva", String.valueOf(resultSetBuscar.getInt("idreserva")));
			datosBuscarH.add(fila);
		}
		conn.close();
		return datosBuscarH;
	}	
}