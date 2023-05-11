package com.david.chataim.model;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import com.david.chataim.controller.ImageController;


public class DataBase {

//	public static Connection con;
//	private final String NAMEDB ="chataim";
//	private final String USER ="root";
//	private final String PASSWD ="root123";
//	private final String URL ="jdbc:mysql://localhost:3306/"+NAMEDB;
//	private final String DRIVER ="com.mysql.cj.jdbc.Driver";
	
	public static Connection con;
	private final String USER ="root";
	private final String PASSWD ="EIoYXNlWpzmRQ4vWqsal";
	private final String URL ="jdbc:mysql://containers-us-west-39.railway.app:6254/railway";
	private final String DRIVER ="com.mysql.cj.jdbc.Driver";
	
	public static enum APP {
		URL_EMAIL_VERIFICATION_CODE, URL, URL_TERMS_AND_CODITIONS};
	
	
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
	
	public int existAccount(String email, String passwd) {
		try {
			String selectSQL ="SELECT id_contact FROM credential WHERE email = \""+email+"\"";
			if (passwd != null) {
				selectSQL += " AND passwd = \""+passwd+"\"";
			}//IF
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectSQL);
			
			if (rs.next()) {
				return rs.getInt(1);
			}//IF
		} catch (SQLException e) { e.printStackTrace(); }//CATCH
		
		return -1;
	}//IF
	
	// ADD NEW CONTACT TO THE LIST
	public Contact getContact(int id) {
		try {
			String selectSQL ="SELECT * FROM contact WHERE id = "+id;
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectSQL);
			
			if (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setOriginalName(rs.getString(2));
				contact.setDescription(rs.getString(3));
				contact.setImage(getImage(rs.getBinaryStream(4)));
				contact.setLast_connection(rs.getTimestamp(6));
				contact.setConnected(rs.getBoolean(7));
				return contact;
			}//IF
		} catch (SQLException e) { e.printStackTrace(); }//CATCH
		
		return null;
	}//FUN
	
	public String getFromApp(final APP data, final String lang) {
		try {
			String selectSQL ="SELECT ";
			switch (data) {
				case URL_EMAIL_VERIFICATION_CODE:
					selectSQL +="urlEmailVerificationCode";
					break;
					
				case URL_TERMS_AND_CODITIONS:
					selectSQL +="urlTermsAndConditions";
					break;
					
				default: return null;
			}//SWITCH
			
			selectSQL += lang+" FROM app";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(selectSQL);
			rs.next();
			
			return rs.getString(1);
		}//TRY
		catch (SQLException e) { System.out.println(e.getMessage()); }//CATCH
		
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
	
	public void createVerificationTimer(int id) {
		try {
			String selectSQL ="CREATE EVENT verification_"+id+"\n"
							+ "ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE\n"
							+ "DO DELETE FROM verification WHERE id ="+id+" ;";
			Statement st = con.createStatement();
			
			st.executeUpdate(selectSQL);
		}//TRY
		catch (SQLException e) {
			e.printStackTrace();
		}//CATCH
	}//FUN
	
	public int getVerificationCode(int id) {
		try {
			String selectSQL ="SELECT code FROM verification";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectSQL);
			rs.next();
			
			return rs.getInt(1);
		}//TRY
		catch (SQLException e) {}//CATCH
		
		return -1;
	}//FUN
	
	// CREATE CONTACT ON REGISTER
	public int createContact(Contact contact, Register register) {
		try {
			String selectSQL ="SELECT createContact(?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(selectSQL);
			
			ps.setString(1, contact.getOriginalName());
			ps.setString(2, contact.getDescription());

			if (contact.getImage() != ImageController.getDefaultImageUser()) {
				ps.setBinaryStream(3, ImageController.convertToInputStream(contact.getImage(), "png"));
			} else {
				ps.setNull(3, java.sql.Types.BLOB);
			}//IF

			ps.setBoolean(4, contact.isAnonymous());
			ps.setString(5, register.getEmail());
			ps.setString(6, register.getPasswd());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return rs.getInt(1);
		}//TRY
		catch (SQLException e) {}//CATCH
		
		return -1;
	}//FUN
	
	public int getChat(int idPropietary, int idNewContact) {
		try {
			String selectSQL ="SELECT createChat("+idPropietary+", "+idNewContact+");";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectSQL);
			
			if (rs.next()) {
				return rs.getInt(1);
			}//IF
		} catch (SQLException e) { e.printStackTrace(); }//CATCH
		
		return -1;
	}//FUN
	
	// ADD CONTACT FOR CONTACT WITH CHAT
	public int createNewChat(int idPropietary, int idNewContact) {
		try {
			String selectSQL ="SELECT createChat(?,?);";
			PreparedStatement ps = con.prepareStatement(selectSQL);
			
			ps.setInt(1, idPropietary);
			ps.setInt(2, idNewContact);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return rs.getInt(1);
		}//TRY
		catch (SQLException e) { e.printStackTrace(); }//CATCH
		
		return -1;
	}//FUN
	
	public LinkedHashMap<Integer, Contact> getContacts(int idPropietary) {
		LinkedHashMap<Integer, Contact> contacts = null;
		try {
			String sql = "SELECT contact.*, added_contact.id_chat FROM contact"
					+ " INNER JOIN added_contact"
					+ " ON contact.id = added_contact.id_contact_contact"
					+ " WHERE added_contact.id_contact_propietary = "+idPropietary+";";
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery(sql);
			
			// GET ROW COUNT
			int rowCount = getRowCount(rs);
		    
		    if (rowCount != 0) {
		    	contacts = new LinkedHashMap<Integer, Contact>();
		    	
				// SAVE ANY CONTACTs
		    	while (rs.next()) {
		    		// CREATE CONTACT
					Contact contact = new Contact();
					contact.setId(rs.getInt(1));
					contact.setOriginalName(rs.getString(2));
					contact.setDescription(rs.getString(3));
					contact.setImage(getImage(rs.getBinaryStream(4)));
					contact.setLast_connection(rs.getTimestamp(6));
					contact.setConnected(rs.getBoolean(7));
					contact.setChat(rs.getInt(8));
					
					// ADD TO THE LIST
					contacts.put(rs.getInt(1), contact);
		    	}//WHILE
		    }//IF
			
			rs.close();
			st.close();
		}//TRY
		catch (SQLException xp) {}//CATCH
		
		return contacts;
	}//FUN
	
	public boolean sendMessage(int chat, ChatMessage message, int to, boolean addToQueue) {
		try {
			String selectSQL ="SELECT sendMessageText(?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(selectSQL);
			
			ps.setString(1, message.getText());
			ps.setTimestamp(2, message.getSend());
			ps.setInt(3, chat);
			ps.setInt(4, message.getContact().getId());
			ps.setInt(5, to);
			ps.setBoolean(6, addToQueue);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return rs.getBoolean(1);
		}//TRY
		catch (SQLException e) {e.printStackTrace();}//CATCH
		
		return false;
	}//FUN
	
	public void sendFile() {
		
	}//FUN
	
	public void sendAsscii() {
		
	}//FUN
	
	public ChatMessage[] getAllMessagesOf(Contact contact, Contact currentContact) {
		ChatMessage[] messages = null;
		try {
			String sql = "SELECT id, content, IF(file IS NULL, false, true), send, id_contact, IF (id_ascii IS NULL, false, true) FROM message WHERE id_chat = "+contact.getChat()+";";
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery(sql);
			
			// GET ROW COUNT
			int rowCount = getRowCount(rs);
		    
		    if (rowCount != 0) {
		    	messages = new ChatMessage[rowCount];
		    	
				// SAVE MESSAGES
		    	for (int f=0; f<rowCount; f++) {
		    		rs.next();
		    		
		    		// WHO IS THE PROPIETARY
		    		Contact auxContact = null;
		    		if (rs.getInt(5) == currentContact.getId()) {
		    			auxContact = currentContact;
		    		} else {
		    			auxContact = contact;
		    		}//IF
		    		
		    		// CREATE MESSAGE
		    		ChatMessage message = new ChatMessage(
		    				rs.getInt(1),
		    				rs.getString(2),
		    				rs.getBoolean(3),
		    				rs.getTimestamp(4),
		    				rs.getBoolean(6)
		    				);
					message.setContact(auxContact);
		    		
					// ADD TO THE LIST
		    		messages[f] = message;
		    	}//WHILE
		    }//IF
			
			rs.close();
			st.close();
			
			return messages;
		}//TRY
		catch (SQLException xp) { System.out.println(xp.toString()); }//CATCH
		
		return null;
	}//FUN
	
	public ChatMessage[] getMessagesOf(int idContact) {
		ChatMessage[] messages = null;
		try {
			String sql = "SELECT id, content, IF(file IS NULL, false, true), send, id_contact, IF (id_ascii IS NULL, false, true), id_chat"
					+ " FROM message"
					+ " WHERE EXISTS("
					+ "SELECT * FROM messaging_queue WHERE message.id = messaging_queue.id_message AND messaging_queue.id_contact = "+idContact
					+ ");";
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery(sql);
			
			// GET ROW COUNT
			int rowCount = getRowCount(rs);
		    
		    if (rowCount != 0) {
		    	messages = new ChatMessage[rowCount];
		    	
				// SAVE MESSAGES
		    	for (int f=0; f<rowCount; f++) {
		    		rs.next();
		    		
		    		// CREATE MESSAGE
		    		ChatMessage message = new ChatMessage(
		    				rs.getInt(1),
		    				rs.getString(2),
		    				rs.getBoolean(3),
		    				rs.getTimestamp(4),
		    				rs.getBoolean(6)
		    				);
		    		Contact contact = new Contact();
		    		contact.setId(rs.getInt(5));
		    		contact.setChat(rs.getInt(7));
		    		message.setContact(contact);
					
					// ADD TO THE LIST
		    		messages[f] = message;
		    	}//WHILE
		    }//IF
			
			rs.close();
			st.close();
			
			return messages;
		}//TRY
		catch (SQLException xp) {}//CATCH
		
		return null;
	}//FUN
	
	private int getRowCount(ResultSet rs) throws SQLException {
		rs.last();
		int rowCount = rs.getRow();
	    rs.beforeFirst();
	    
	    return rowCount;
	}//FUN
	
	public void removeMessagesQueue(ChatMessage[] messages) {
		String deleteSQL = "DELETE FROM messaging_queue WHERE id_message IN(";
		
		// FIRST MESSAGE
		deleteSQL += messages[0].getId();
		
		// ADD THE REST
		for (int f=1; f<messages.length; f++) {
			deleteSQL += "," + messages[f].getId();
		}//FOR
		deleteSQL += ");";
		
		try {
			PreparedStatement ps = con.prepareStatement(deleteSQL);
			
			ps.executeUpdate();
			ps.close();
		}//TRY
		catch (SQLException e) { e.printStackTrace(); }//CATCH
	}//FUN

	// GET IMAGE OF BLOB, OR DEFAULT
	private Image getImage(InputStream blob) {
		if (blob == null) {
			return ImageController.getDefaultImageUser();
		} else {
			return ImageController.convertToImage(blob);				
		}//IF
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