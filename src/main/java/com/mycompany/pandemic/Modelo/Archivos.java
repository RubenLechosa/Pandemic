package com.mycompany.pandemic.Modelo;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public abstract class Archivos {
    
    public static HashMap<String, HashMap<String, String>> leerTxt(String archivo) throws IOException{
        File dir = new File(archivo);
        File[] files = dir.listFiles();
        HashMap<String, HashMap<String, String>> textoFinal = new HashMap<String, HashMap<String, String>>();
        // Fetching all the (
        for (File file : files) {
            if(file.isFile()) {
                BufferedReader inputStream = null;
                String line;

                try {
                    inputStream = new BufferedReader(new FileReader(file));

                    while ((line = inputStream.readLine()) != null) {
							String[] texto = line.split(";");
							HashMap<String, String> parametros = new HashMap<String,String>();
							parametros.put("Name", texto[0]);
							parametros.put("Color", texto[1]);
							parametros.put("X", texto[2].split(",")[0]);
							parametros.put("Y", texto[2].split(",")[1]);
							parametros.put("Colindantes", texto[3]);
							
							textoFinal.put(texto[0], parametros);

                    }             

                } catch(IOException e) {
                	System.out.println(e);
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                
            }
        }
        
		return textoFinal;
    }
    
    public static ArrayList<String> readXML(String xml) {
        String role1 = null;
        String role2 = null;
        String role3 = null;
        String role4 = null;
        String role5 = null;
        ArrayList<String> rolev;
        
        rolev = new ArrayList<String>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.reset();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();

            role1 = getTextValue(role1, doc, "numCiudadesInfectadasInicio");
            if (role1 != null) {
                if (!role1.isEmpty())
                    rolev.add(role1);
            }
            role2 = getTextValue(role2, doc, "numCuidadesInfectadasRonda");
            if (role2 != null) {
                if (!role2.isEmpty())
                    rolev.add(role2);
            }
            role3 = getTextValue(role3, doc, "numEnfermedadesActivasDerrota");
            if (role3 != null) {
                if (!role3.isEmpty())
                    rolev.add(role3);
            }
            role4 = getTextValue(role4, doc, "numBrotesDerrota");
            if ( role4 != null) {
                if (!role4.isEmpty())
                    rolev.add(role4);
            }
            
            role5 = getTextValue(role5, doc, "numVecesInvestigar");
            if ( role5 != null) {
                if (!role5.isEmpty())
                    rolev.add(role5);
            }

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        return rolev;
    }
    
    private static String getTextValue(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
    
    public static HashMap<Integer, HashMap<String, String>> LecturaFicheroBin(String rutaFichero) throws IOException {
    	// codigoEnfermedad (int), nombreEnfermedad(String) y colorEnfermedad (String)
    	//   Alfa Azul    Beta Rojo    Gama Verde    Delta Amarillo
		String texto = "";
		HashMap<Integer,HashMap<String,String>> mapaEnfermedades = new HashMap <Integer,HashMap<String,String >>();
		
		try {
			DataInputStream LeeFicheroBin = new DataInputStream(new FileInputStream(rutaFichero));
			try {
				LeeFicheroBin.readUTF();
			} catch (Exception e) {
				System.out.println(e);
			}
			for (int i = 0; i < 4; i++) {
				HashMap<String, String> parametrosEnfermedades = new HashMap<String,String>();
				int id = LeeFicheroBin.readInt();
				
				mapaEnfermedades.put(id, parametrosEnfermedades);
				parametrosEnfermedades.put("idEnfermedad", Integer.toString(id));
				parametrosEnfermedades.put("nombreEnfermedad",(LeeFicheroBin.readUTF()));
				parametrosEnfermedades.put("colorEnfermedad",(LeeFicheroBin.readUTF()));
				
			}
		} catch (EOFException e) {
			System.out.println("Fin del fichero");
		} catch (IOException e) {
			System.out.println("Error E/S");
		}
		return mapaEnfermedades;
	}
    
}
