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
import java.net.URL;
import javax.xml.XMLConstants;
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
    
    public static ArrayList<String> readXML(int dificultad) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<String> rolev = new ArrayList<String>();
      try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new InputSource(new URL("http://localhost/dificultad" + dificultad).openStream()));

          doc.getDocumentElement().normalize();


          // get <staff>
          NodeList list = doc.getElementsByTagName("parametros");
          
          for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);
              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // get staff's attribute
                  String id = element.getAttribute("id");
                  
                  // get text
                  rolev.add(element.getElementsByTagName("numCiudadesInfectadasInicio").item(0).getTextContent());
                  rolev.add(element.getElementsByTagName("numCuidadesInfectadasRonda").item(0).getTextContent());
                  rolev.add(element.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).getTextContent());
                  rolev.add(element.getElementsByTagName("numBrotesDerrota").item(0).getTextContent());
                  rolev.add(element.getElementsByTagName("numVecesInvestigar").item(0).getTextContent());
              }
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
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
