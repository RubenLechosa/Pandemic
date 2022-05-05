package com.mycompany.pandemic.Controlador;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
public class Bd {

	private static final String USER = "PND_PRIVEE";
	private static final String PWD = "AREL123";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
	
	 
	private static Scanner smenu = new Scanner(System.in);
	public static void main(String[] args) {

		
		Connection connection = makeConnection();
		//insertWithStatement(connection);
		//selectWithStatement(connection);
		//register(connection, "Alex", "Mandrigal", "alex@duki.com", "mandingo");
		//ranking(connection);
		sumarPuntos(connection, 1, 5);
	}
	
public static Connection makeConnection() {
		
		System.out.println("Connecting database...");
		
		Connection con = null;
		//intentamos la conexion a la base de datos
		try  {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL,USER,PWD);
			
			System.out.println("Database connected!");
			
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database! ", e);
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//devolvemos el valor de la conexion
		return con;		
	}

	
	public static void closeConnection(Connection con) {
		//cierra la conexi�n
		try {
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error closing connection!!" + e);
			
		}
	}
	
	public static void insertWithStatement(Connection con) {
		String sql = "INSERT INTO users (user_name, user_surname, user_email, user_password) VALUES ('Alex', 'Madrigal', 'alex@ilerna.cat', 'mandingo')";
		
		try {
			Statement statement = (Statement) con.createStatement();
			statement.execute(sql);
			statement.close();
			
		} catch (SQLException e) {
			System.out.println("The Insert had problems!! " + e);
			
		} 
	}
	
	public static void selectWithStatement(Connection con) {
		
		String sql = "SELECT * FROM users";
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			
		    ResultSet rs = st.executeQuery(sql);
		       
		    while (rs.next())
		    {
		    	int id = rs.getInt("user_id");
		        String nombre = rs.getString("user_name");
		        int puntuacion = rs.getInt("user_points");

		        System.out.println("Id: " + id + " Nombre: " + nombre + " Puntos Totales: " + puntuacion);
		    }
		      
		    st.close();
		    
		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);
			
		}
	}
	
	public static boolean login(String email, String password) {
		Connection con = makeConnection();
		String sql = "SELECT * FROM users WHERE user_email = '"+ email +"' AND user_password = '"+ password + "'";

		Statement st = null;
		
		try {
			st = con.createStatement();
			
		    ResultSet rs = st.executeQuery(sql);
		    Boolean search = true;
		    
		    while (search)
		    {
		    	if(rs.next()) {
			    	int id = rs.getInt("user_id");
			        String nombre = rs.getString("user_name");
			        int puntuacion = rs.getInt("user_points");

			        System.out.println("Id: " + id + " Nombre: " + nombre + " Puntos Totales: " + puntuacion);
		    		search = false;
					return true;
		    	} else {
		    		System.out.println("Usuario incorrecto");  
		    		search = false;
		    	}

		    }
		    
		    st.close();
;
		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);
			
		}
                
		return false;
                
	}
	
	public static boolean register(String name, String surname, String email, String password) {
            Connection con = makeConnection();	
            String sql = "INSERT INTO users (user_name, user_surname, user_email, user_password) VALUES ('"+name+"', '"+surname+"', '"+email+"', '"+password+"')";
		
		try {
			Statement statement = (Statement) con.createStatement();
			statement.execute(sql);
			statement.close();
			
			login(email, password);
			System.out.println("Registrado y Logeado con exito.");
			return true;
		} catch (SQLException e) {
			System.out.println("The Insert had problems!! " + e);
			
		}

		return false;
	}
	
		public static HashMap<Integer, HashMap<String, String>> ranking() {
		Connection con = makeConnection();
		String sql = "SELECT * FROM users ORDER BY user_points DESC";

		Statement st = null;
		HashMap<Integer, HashMap<String, String>> ranking = new HashMap<Integer, HashMap<String, String>>();

		try {
			st = con.createStatement();
			
		    ResultSet rs = st.executeQuery(sql);
		    
                    int i = 0;
		    while (rs.next()) {
			    int id = rs.getInt("user_id");
			    String nombre = rs.getString("user_name");
			    int puntuacion = rs.getInt("user_points");

			    HashMap<String, String> datos = new HashMap<String, String>();
			    datos.put("Nombre", nombre);
			    datos.put("Puntos", String.valueOf(puntuacion)); 
			                     System.out.println(id);
			    ranking.put(i, datos);
                            i++;
		    }
		      
		    st.close();
		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);
			
		}	
                    System.out.println(ranking);
		return ranking;
	}

	public static void sumarPuntos(Connection con, int user_id, int puntosSumar) {
		
		String sql = "SELECT * FROM users WHERE user_id = "+user_id+"";

		Statement st = null;
		
		try {
			st = con.createStatement();
			
		    ResultSet rs = st.executeQuery(sql);
		    
		    while (rs.next()) {
		    	st = con.createStatement();
				sql = "UPDATE users SET user_points = '"+(rs.getInt("user_points") + puntosSumar)+"' WHERE user_id ='"+user_id+"'";
				ResultSet updateResult = st.executeQuery(sql);
		    }

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);
			
		}	
		
	}



}

