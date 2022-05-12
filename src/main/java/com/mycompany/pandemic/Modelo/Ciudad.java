package com.mycompany.pandemic.Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mycompany.pandemic.Controlador.Partida;
import com.mycompany.pandemic.Modelo.Enfermedad;
import java.math.BigDecimal;

public class Ciudad {
    private int idCiudad;
    private String nombre;
    private int colorCiudad;
    private int coordenadaX;
    private int coordenadaY;
    private ArrayList<String> ciudadesColindantes = new ArrayList<String>();
    private ArrayList<Enfermedad> enfermedadList = new ArrayList<Enfermedad>();;
    
    public Ciudad(String nombre, int colorCiudad, int coordenadaX, int coordenadaY, ArrayList<String> ciudadesColindantes){
        this.enfermedadList = new ArrayList<Enfermedad>();
        this.nombre = nombre;
        this.colorCiudad = colorCiudad;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ciudadesColindantes = ciudadesColindantes;
        
        try {
			cargarEnfermedades();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Ciudad(BigDecimal idCiudad2, String nombreCiudad, BigDecimal colorCiudad, BigDecimal coordenadaX, BigDecimal coordenadaY,
        BigDecimal nInfeccionAzul, BigDecimal nInfeccionRojo, BigDecimal nInfeccionVerde,
        BigDecimal nInfeccionAmarillo) {
    	
        this.nombre = nombreCiudad;
    	this.colorCiudad = colorCiudad.intValue();
    	this.coordenadaX = coordenadaX.intValue();
    	this.coordenadaY = coordenadaY.intValue();
        
        try {
            cargarEnfermedades();
	} catch (IOException e) {
            e.printStackTrace();
	}
        
        this.getEnfermedadList().get(0).setNivelInfeccion(nInfeccionAzul.intValue());
	this.getEnfermedadList().get(1).setNivelInfeccion(nInfeccionRojo.intValue());
	this.getEnfermedadList().get(2).setNivelInfeccion(nInfeccionVerde.intValue());
	this.getEnfermedadList().get(3).setNivelInfeccion(nInfeccionAmarillo.intValue());
		
    }

    
    public void cargarEnfermedades() throws IOException {
    	HashMap<Integer, HashMap<String, String>> listEnfermedades = new HashMap<Integer, HashMap<String, String>>();
    	listEnfermedades = Archivos.LecturaFicheroBin("src\\main\\java\\Assets\\Archivos Bin\\CCP.bin");
   		
        for (HashMap<String, String> enfermedad : listEnfermedades.values()) {
        	Enfermedad enfermedadObj = new Enfermedad(Integer.parseInt(enfermedad.get("idEnfermedad")), enfermedad.get("nombreEnfermedad"), "Icono", enfermedad.get("colorEnfermedad"));
        	enfermedadList.add(enfermedadObj);
        }

    }

    //Metodos
    public ArrayList<Integer> colindantes(int idCiudad){
        return null;
    }
    //Getters y Setters
    public void setIdCiudad(int idCiudad){
        this.idCiudad = idCiudad;
    }

    public int getIdCiudad(){
        return this.idCiudad;
    }

    public void setNombreCiudad(String nombre){
        this.nombre = nombre;
    }

    public String getNombreCiudad(){
        return this.nombre;
    }

    public void setColorCiudad(int colorCiudad){
        this.colorCiudad = colorCiudad;
    }

    public int getColorCiudad(){
        return this.colorCiudad;
    }

    public void setCoordenadaX(int coordenadaX){
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaX(){
        return this.coordenadaX;
    }

    public void setCoordenadaY(int coordenadaY){
        this.coordenadaY = coordenadaY;
    }

    public int getCoordenadaY(){
        return this.coordenadaY;
    }

    public void setCiudadesColindantes(ArrayList<String> ciudadesColindantes){
        this.ciudadesColindantes = ciudadesColindantes;
    }

    public ArrayList<String> getCiudadesColindantes(){
        return this.ciudadesColindantes;
    }
    
    public void setEnfermedadList(ArrayList<Enfermedad> enfermedadList){
        this.enfermedadList = enfermedadList;
    }

    public ArrayList<Enfermedad> getEnfermedadList(){
        return this.enfermedadList;
    }
}
