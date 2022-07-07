package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	private static Conexion conexion;
	private final static String url = "jdbc:mysql:///pelos";
	private static String user = "gustavo";
	private static String password = "mahica70";
	private Connection con = null;
	private ResultSet rs;
	
	public static Conexion getConexion() {
		if(conexion == null) {
			conexion = new Conexion();
		}
		return conexion;
	}
	
	private Conexion() {
		
	}
	
   public void conectar() throws ClassNotFoundException, SQLException {

      // register MySQL thin driver with DriverManager service
      // It is optional for JDBC4.x version
	   if (con.isClosed()) {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection(url, user, password);
	   }

      // variables

      // establish the connection
 
   } 
   
   public void getDefaultConnection() throws ClassNotFoundException, SQLException {
	   
	   // register MySQL thin driver with DriverManager service
	   // It is optional for JDBC4.x version
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   
	   // variables
	   
	   // establish the connection
	   con = DriverManager.getConnection("jdbc:mysql:", user, password);
	   
   } 
   
   public void desconectar() throws SQLException {
	   // close JDBC connection
		   if( con != null)
			   con.close();
	   }

	public void update(String sql) throws SQLException {
		// TODO Auto-generated method stub
			con.createStatement().executeUpdate(sql);
	}
	
	public ResultSet get(String sql) throws SQLException {
		return con.createStatement().executeQuery(sql);
		
	}

	public void conectar(String login) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String[] log = login.split(",");
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, log[0].trim(), log[1].trim());
		
	}

	public Connection getConect() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
