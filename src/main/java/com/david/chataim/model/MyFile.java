package com.david.chataim.model;

import java.io.File;
import java.io.FileWriter;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class MyFile {
	
//	private static final String MY_CREDENTIALS_FILE ="com/david/chataim/model/Config.xml";
	private static final String CREDENTIALS_FILE ="Config.xml";
	private static final String TEMP_INSTANCE =".chataim_temp";
	

	public static boolean existsTemp() {
		return new File(TEMP_INSTANCE).exists();
	}//BOOL
	
	public static void createTemp() {
		try {
			new File(TEMP_INSTANCE).createNewFile();
			String os = System.getProperty("os.name").toLowerCase();

	        if (os.contains("win")) {
	        	Runtime.getRuntime().exec("attrib +H "+TEMP_INSTANCE);
	        }//IF
		}//TRY
		catch (Exception e) {}//CATCH
	}//V
	
	public static void deleteTemp() {
		try {
			new File(TEMP_INSTANCE).delete();
		}//TRY
		catch (Exception e) {}//CATCH
	}//V
	
	public static String[] getConfig() {
		createFile();
		return getValues();
	}//LIST
	
	public static void clearCredentials() {
		rmFile();
		createFile();
	}//V
	
	public static void setCredentials(String email, String passwd) {
	    updateXmlFile(CREDENTIALS_FILE, doc -> {
	        doc.getElementsByTagName("email").item(0).setTextContent(email);
	        doc.getElementsByTagName("passwd").item(0).setTextContent(passwd);
	    });
	}//V

	public static void setTheme(String theme) {
	    updateXmlFile(CREDENTIALS_FILE, doc -> {
	        doc.getElementsByTagName("theme").item(0).setTextContent(theme);
	    });
	}//V

	private static void updateXmlFile(String filePath, Consumer<Document> updateAction) {
	    File xmlFile = new File(filePath);
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    try {
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(xmlFile);
	        updateAction.accept(document);

	        // SAVE
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult result = new StreamResult(xmlFile);
	        transformer.transform(source, result);
	    } catch (Exception e) {}
	}//V

	
	private static void createFile() {
        File userFile = new File(CREDENTIALS_FILE);
        if (!userFile.exists()) {
            try {
            	FileWriter writer = new FileWriter(userFile);
            	writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
            			+ "<config>\r\n"
            			+ "	<theme>light</theme>\r\n"
            			+ "	<user>\r\n"
            			+ "		<email></email>\r\n"
            			+ "		<passwd></passwd>\r\n"
            			+ "	</user>\r\n"
            			+ "</config>");
            	writer.flush();
            	writer.close();
//            	JOptionPane.showConfirmDialog(null, ori);
			}//TRY
            catch (Exception e) {System.out.println(e.getMessage());}
        }//IF
	}//V
	
	private static void rmFile() {
		new File(CREDENTIALS_FILE).delete();
	}//V
	
	private static String[] getValues() {
		try {
			File inputFile = new File(CREDENTIALS_FILE);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			String theme = doc.getElementsByTagName("theme").item(0).getTextContent();
			String email = doc.getElementsByTagName("email").item(0).getTextContent();
	        String passwd = doc.getElementsByTagName("passwd").item(0).getTextContent();
	        
	        return new String[] {theme, email, passwd};
		}//TRY
		catch (Exception e) { return new String[] {"", ""}; }
	}//LIST
}//CLASS