package com.mycompany.pandemic.Controlador;

import com.mycompany.pandemic.Modelo.Ciudad;
import com.mycompany.pandemic.Modelo.Tienda;
import com.mycompany.pandemic.Modelo.Turno;
import com.mycompany.pandemic.Modelo.Vacunas;
import com.mycompany.pandemic.PanelJugar;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Bd {

	private static final String USER = "PND_PRIVEE";
	private static final String PWD = "AREL123";
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
	
	 
	private static Scanner smenu = new Scanner(System.in);
	public static void main(String[] args) {

		
		Connection connection = makeConnection();
		//insertWithStatement(connection);
		//selectWithStatement(connection);
		//register(connection, "Alex", "Mandrigal", "alex@duki.com", "mandingo");
		//ranking(connection);
		
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
			    	/// BD
                                int puntos = rs.getInt("user_points");
                                int user_id = rs.getInt("user_id");
                                Partida.idJugador = user_id;
                                Partida.setPlayerPoints(puntos);
                                System.out.println(Partida.idJugador);
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

	public static void sumarPuntos(int user_id, int puntosSumar) {
		Connection con = makeConnection();
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

        public static void guardarPartida(int dificultad, int id_user) {
		Connection con = makeConnection();
		borrarPartidas(id_user);
                
		String TIENDA = "tienda(" +Partida.tienda.getDinero() + ","+ Partida.tienda.getDineroTurno() + ")";
		String TURNO = "turno(" + Partida.turno.getNumTurno() + "," + Partida.turno.getBrotesTotales()+ ", "+Partida.turno.getPuntosTotales()+")";
		String CIUDAD = "CIUDADES(";
		
		int i = 0;
		for (Ciudad ciudad : Partida.ciudadList) {
			if(i != 0) {
				CIUDAD += ",";
			}
			
			CIUDAD += "CIUDAD(" + ciudad.getIdCiudad() + "," + ciudad.getColorCiudad() + "," +  ciudad.getCoordenadaX() + "," + ciudad.getCoordenadaY() + ","+ ciudad.getEnfermedadList().get(0).getNivelInfeccion() + ","+ ciudad.getEnfermedadList().get(1).getNivelInfeccion()+ ","+ ciudad.getEnfermedadList().get(2).getNivelInfeccion()+ ","+ ciudad.getEnfermedadList().get(3).getNivelInfeccion()+",'"+ciudad.getNombreCiudad()+"')";
			
			i++;
		}
		
		CIUDAD += ")";
		
		String VACUNAS = "ARRAYVACUNAS(";
		int j = 0;
		for (Vacunas vacuna : Vacunas.TodasVacunas) {
			if(j != 0) {
				VACUNAS += ",";
			}
			
			int invesitagada = 0;
			
			if(vacuna.getVacunaInvestigada()) {
				invesitagada = 1;
			}
			
			VACUNAS += "VACUNAS("+ vacuna.getPorcentajeVacuna() + ", "+invesitagada+" ,"+ "'"+vacuna.getColorVacuna()+"'" + ")";
			
			j++;
		}
		
		VACUNAS += ")";
		
		String SQL = "INSERT INTO PARTIDA (dificultad, user_id, tiendas, turnos, lista_ciudades, vacunas) VALUES ("+dificultad+" ,"+id_user+","+TIENDA+","+TURNO+","+CIUDAD+","+VACUNAS+")";

		Statement st = null;

		try {
			st = con.createStatement();

			st.execute(SQL);
			st.close();

		} catch (SQLException e) {
			System.out.println("The INSERT had problems!! " + e);

		}	
	}
        
        public static void borrarPartidas(int id_user) {
                Connection con = makeConnection();
                String SQL = "DELETE FROM PARTIDA WHERE USER_ID = '" + id_user + "'";

		Statement st = null;

		try {
			st = con.createStatement();
                        
                        st.execute(SQL);
                        st.close();
                        
		} catch (SQLException e) {
			System.out.println("The DELETE had problems!! " + e);

		} 
        }
		
	public static boolean cargarPartida(int user_id) {
		Connection con = makeConnection();
		String SQL = "SELECT p.* FROM Partida p WHERE p.user_id = "+user_id+"";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(SQL);
		
			while (rs.next()) {
				int dificultad = rs.getInt("dificultad");
				Struct Tiendas = (Struct) rs.getObject("Tiendas");
				BigDecimal dinero = (BigDecimal) Tiendas.getAttributes()[0];
				BigDecimal dineroPorTurno = (BigDecimal) Tiendas.getAttributes()[1];
				Tienda tienda1 = new Tienda(dinero,dineroPorTurno);
			

				Struct Turnos = (Struct) rs.getObject("Turnos");
				BigDecimal numturno = (BigDecimal) Turnos.getAttributes()[0];
				BigDecimal brotestotales = (BigDecimal) Turnos.getAttributes()[1];
                                BigDecimal nAcciones = (BigDecimal) Turnos.getAttributes()[2];
				Turno turno1 = new Turno(numturno,brotestotales, nAcciones);
                                
                                System.out.println("Cargamos partida");
				PanelJugar.partida.turno = turno1;
                                PanelJugar.partida.tienda = tienda1;
				PanelJugar.partida.dificultad = dificultad;
                                Partida.partidaId = rs.getInt("id_partida");
                                
				Array array = rs.getArray("lista_ciudades");
				Object[] objArray = (Object[]) array.getArray();
				
				ArrayList<Ciudad> ArrayCiudades = new ArrayList<>();
				
				for(int i = 0; i < objArray.length; i++) {
					Struct ciudad = (Struct) objArray[i];
                                        
					BigDecimal IDCIUDAD = (BigDecimal) ciudad.getAttributes()[0];
					BigDecimal COLORCIUDAD = (BigDecimal) ciudad.getAttributes()[1];
					BigDecimal COORDENADAX = (BigDecimal) ciudad.getAttributes()[2];
					BigDecimal COORDENDADAY = (BigDecimal) ciudad.getAttributes()[3];
					BigDecimal NIVELINFECCIONAZUL = (BigDecimal) ciudad.getAttributes()[4];
					BigDecimal NIVELINFECCIONROJO = (BigDecimal) ciudad.getAttributes()[5];
					BigDecimal NIVELINFECCIONVERDE = (BigDecimal) ciudad.getAttributes()[6];
					BigDecimal NIVELINFECCIONAMARILLO = (BigDecimal) ciudad.getAttributes()[7];
					String NOMBRECIUDAD = (String) ciudad.getAttributes()[8];
                                        
					Ciudad ciudad1 = new Ciudad(IDCIUDAD,NOMBRECIUDAD,COLORCIUDAD,COORDENADAX,COORDENDADAY,NIVELINFECCIONAZUL,NIVELINFECCIONROJO,NIVELINFECCIONVERDE,NIVELINFECCIONAMARILLO);
					ArrayCiudades.add(ciudad1);
				}
				
				PanelJugar.partida.setCiudades(ArrayCiudades);

				Array array2 = rs.getArray("Vacunas");
				Object[] objArray2 = (Object[]) array2.getArray();
				
				ArrayList<Vacunas> ArrayVacunas = new ArrayList<>();
				
				for(int i = 0; i < objArray2.length; i++) {
					Struct vacuna = (Struct) objArray2[i];
					BigDecimal PorcentajeVacuna = (BigDecimal) vacuna.getAttributes()[0];
					BigDecimal VacunaInvestigada = (BigDecimal) vacuna.getAttributes()[1];
					String colorVacuna = (String) vacuna.getAttributes()[2];
					
					
					Vacunas vacunas2 = new Vacunas(PorcentajeVacuna,VacunaInvestigada,colorVacuna);
					ArrayVacunas.add(vacunas2);
				}
				
				Vacunas.TodasVacunas = ArrayVacunas;
                                return true;
			}

			st.close();

		} catch (SQLException e) {
                       return false; 
		}
                
                return false;
	}
        
        public static boolean tienePartidas(int user_id) {
                System.out.println(user_id);
		Connection con = makeConnection();
		String SQL = "SELECT p.* FROM Partida p WHERE p.user_id = "+user_id+"";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(SQL);
		
			while (rs.next()) {
                                return true;
			}

			st.close();

		} catch (SQLException e) {
                       return false; 
		}
                
                return false;
	}


}

