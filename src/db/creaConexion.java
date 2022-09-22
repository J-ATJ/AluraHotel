package db;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class creaConexion {
	private DataSource datasource;
	public creaConexion() {
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("passwordhere");
		pooledDataSource.setMaxPoolSize(10);
		this.datasource = pooledDataSource;
	}
	public Connection conectar() throws SQLException {
		return this.datasource.getConnection();
	}
}