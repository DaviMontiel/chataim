package com.david.chataim.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.david.chataim.controller.Controller;
import com.david.chataim.view.login.components.Message.MessageType;

public class DataBase {

	public static Connection con;
	private final String NAMEDB ="chataim";
	private final String USER ="root";
	private final String PASSWD ="root123";
	private final String URL ="jdbc:mysql://localhost:3306/"+NAMEDB;
	private final String DRIVER ="com.mysql.cj.jdbc.Driver";
	
//	public static Connection con;
//	private final String USER ="root";
//	private final String PASSWD ="EIoYXNlWpzmRQ4vWqsal";
//	private final String URL ="jdbc:mysql://containers-us-west-39.railway.app:6254/railway";
//	private final String DRIVER ="com.mysql.cj.jdbc.Driver";
	
	
	public DataBase() {
		new Thread() {
			@Override
			public void run() {
				while (con == null) {
					try {
						Class.forName(DRIVER);
						con = DriverManager.getConnection(URL, USER, PASSWD);
					}//TRY
					catch (Exception e) {
						try { Thread.sleep(500); }//TRY
						catch (InterruptedException e1) {}//CATCH
						System.out.println(true);
					}//CATCH
				}//WHILE
			}
		}.start();
	}//Constructor
	
	public boolean isConnected() {
		return con != null;
	}//FUN
	
	public void close() {
		try {
			if (con != null) {
				con.close();
			}//IF
		}//TRY
		catch (SQLException e) {}//CATCH
	}//FUN
	
	public String getVerificationURL(String lang) {
		try {
			String selectSQL ="SELECT urlEmailVerificationCode"+lang+" FROM app";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectSQL);
			rs.next();
			return rs.getString(1);
		}//TRY
		catch (SQLException e) { Controller.s().showMessage(MessageType.ERROR, "ERROR: Base de datos"); }//CATCH
		
		return null;
	}//FUN
	
	public int saveVerificationCode(int code) {
		try {
			String selectSQL ="SELECT createVerificationCode("+code+");";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectSQL);
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}//CATCH
		
		return -1;
	}//FUN
	
	public boolean createVerificationTimer(int id) {
		try {
			String selectSQL ="CREATE EVENT verification_"+id+"\n"
							+ "ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE\n"
							+ "DO DELETE FROM verification WHERE id ="+id+" ;";
			Statement st = con.createStatement();
			
			st.executeUpdate(selectSQL);
			
			return true;
		}//TRY
		catch (SQLException e) {
			e.printStackTrace();
		}//CATCH
		
		return false;
	}//FUN
	
	
	
	
	
	
	
	
	
	
	
	public static void addRow(String nombre, String apellido1, String apellido2, String domicilio, int telefono) throws SQLException {
		String sql = "INSERT INTO nombreTabla (nombre, apellido1, apellido2, domicilio, telefono) VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		if (domicilio.isEmpty()) {
			domicilio = null;
		}//IF
		
		ps.setString(1, nombre);
		ps.setString(2, apellido1);
		ps.setString(3, apellido2);
		ps.setString(4, domicilio);
		ps.setInt(5, telefono);
		
		ps.executeUpdate();
		ps.close();
	}//ADDROW
	
	public static void delRow(String tableName, String id) throws SQLException {
		String selectSQL ="SELECT COUNT(*) FROM "+tableName+" WHERE id = "+id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectSQL);
		
		rs.next();
		int rowCount = rs.getInt(1);
		
		if (rowCount != 0) {
			String deleteSQL = "DELETE FROM "+tableName+" WHERE id = "+id;
			PreparedStatement ps = con.prepareStatement(deleteSQL);
			
			ps.executeUpdate();
			ps.close();
		} else {
			rs.close();
			st.close();
			
			throw new SQLException();
		}//IF
	}//DELROW
	
	public static void updateRow(String id, String titulo, String anio, String autor, String categoria, String edicion, String idioma, String paginas, String stock, String disponible) throws SQLException {
		Statement statement = con.createStatement();
		int updateCount = statement.executeUpdate("UPDATE nombreTabla SET titulo = \""+titulo+"\", anio = \""+anio+"\", autor = \""+autor+"\", categoria = \""+categoria+"\", edicion = \""+edicion+"\", idioma = \""+idioma+"\", paginas = \""+paginas+"\", stock = \""+stock+"\", disponible = \""+disponible+"\" WHERE id = "+id+";");
		
		if (updateCount == 0) {
			throw new SQLException("LA ROW NO EXISTE");
		}//IF
	}//UPDATEROW
	
	public static Object[][] getRows(String tableName, String id) {
		Object[][] data = null;
		try {
			String sql = "SELECT * FROM "+tableName;
			Statement st = con.createStatement();
			if (!id.isEmpty()) {
				sql += " WHERE id = "+id;
			}//IF
			ResultSet rs = st.executeQuery(sql);
			
			data = ResultSetToArray2D(rs);
			
			rs.close();
			st.close();
		}//TRY
		catch (SQLException xp) {System.out.println(xp.toString());}//CATCH
		
		return data;
	}//GETUSERS
	
	public static Object[][] ResultSetToArray2D(ResultSet rs) {
        Object obj[][]=null;
 
        try {
	        rs.last();
	        
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int numCols = rsmd.getColumnCount();
	        int numFils =rs.getRow();
	        obj=new Object[numFils][numCols];
	        
	        int j = 0;
	        rs.beforeFirst();
	        while (rs.next()) {
	            for (int i=0;i<numCols;i++) {
	                obj[j][i]=rs.getObject(i+1);
	            }//FOR
	            j++;
	        }//WHILE
        }//TRY
        catch (Exception e) {}//CATCH
 
        return obj;
    }//RESULTSETTOARRAY2D
}//CLASS