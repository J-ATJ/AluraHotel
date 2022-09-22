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
import javax.swing.JOptionPane;
public class reserva {
	public static List<Map<String, String>> listar() throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("SELECT id, fechaentrada, fechasalida, valor, formadepago FROM reserva");
		statement.execute();
		ResultSet resultSetReserva = statement.getResultSet();
		List<Map<String, String>> datosReserva = new ArrayList<Map<String, String>>();
		while(resultSetReserva.next()) {
			Map<String, String> fila = new HashMap<String, String>();
			fila.put("id", String.valueOf(resultSetReserva.getInt("id")));
			fila.put("fechaentrada", resultSetReserva.getString("fechaentrada"));
			fila.put("fechasalida", resultSetReserva.getString("fechasalida"));
			fila.put("valor", String.valueOf(resultSetReserva.getInt("valor")));
			fila.put("formadepago", resultSetReserva.getString("formadepago"));
			datosReserva.add(fila);
		}
		conn.close();
		return datosReserva;
	}
	public static Map<String, String> guardar(Map<String, String> nuevaReserva) throws SQLException{
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("INSERT INTO reserva (fechaentrada, fechasalida, valor, formadepago) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, nuevaReserva.get("fechaentrada"));
		statement.setString(2, nuevaReserva.get("fechasalida"));
		statement.setInt(3, Integer.parseInt(nuevaReserva.get("valor")));
		statement.setString(4, nuevaReserva.get("formadepago"));
		statement.execute();
		ResultSet resultSetInsert = statement.getGeneratedKeys();
		while(resultSetInsert.next()) {	
			JOptionPane.showMessageDialog(null, String.format("NÚMERO DE RESERVA: %d", resultSetInsert.getInt(1), "IMPORTANTE", JOptionPane.CLOSED_OPTION));
		}
		conn.close();
		return nuevaReserva;
	}
	public static String eliminar(String id) throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("DELETE FROM reserva WHERE id = ?");
		statement.setInt(1, Integer.parseInt(id));
		statement.execute();
		int updateCount1 = statement.getUpdateCount();
		JOptionPane.showMessageDialog(null, "Se eliminó correctamente"+updateCount1+" elemento de reserva");
		conn.close();
		return id;	
	}
	public static Map<String, String> modificar(Map<String, String> modificarReserva) throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("UPDATE reserva SET fechaentrada = ?, fechasalida = ?, valor = ?, formadepago = ? WHERE id = ?");
		statement.setString(1, modificarReserva.get("fechaentrada"));
		statement.setString(2, modificarReserva.get("fechasalida"));
		statement.setInt(3, Integer.parseInt(modificarReserva.get("valor")));
		statement.setString(4, modificarReserva.get("formadepago"));
		statement.setInt(5, Integer.parseInt(modificarReserva.get("id")));
		statement.execute();
		JOptionPane.showMessageDialog(null, "Se modificó exitósamente la reserva con id " + Integer.parseInt(modificarReserva.get("id")), "OK", JOptionPane.INFORMATION_MESSAGE);
		conn.close();
		return modificarReserva;
	}
	public static List<Map<String, String>> buscar(String idBuscar) throws SQLException {
		Connection conn = new creaConexion().conectar();
		PreparedStatement statement = conn.prepareStatement("SELECT id, fechaentrada, fechasalida, valor, formadepago FROM reserva WHERE id = ?");
		statement.setInt(1, Integer.parseInt(idBuscar));
		statement.execute();
		ResultSet resultSetBuscar = statement.getResultSet();
		List<Map<String, String>> datosBuscar = new ArrayList<Map<String, String>>();
		while(resultSetBuscar.next()) {
			Map<String, String> fila = new HashMap<String, String>();
			fila.put("id", String.valueOf(resultSetBuscar.getInt("id")));
			fila.put("fechaentrada", resultSetBuscar.getString("fechaentrada"));
			fila.put("fechasalida", resultSetBuscar.getString("fechasalida"));
			fila.put("valor", String.valueOf(resultSetBuscar.getInt("valor")));
			fila.put("formadepago", resultSetBuscar.getString("formadepago"));
			datosBuscar.add(fila);
		}
		conn.close();
		return datosBuscar;
	}
}