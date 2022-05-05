package com.mycompany.pandemic.Controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mycompany.pandemic.Modelo.Archivos;
import com.mycompany.pandemic.Modelo.Ciudad;
import com.mycompany.pandemic.Modelo.Enfermedad;
import com.mycompany.pandemic.Modelo.Tienda;
import com.mycompany.pandemic.Modelo.Turno;
import com.mycompany.pandemic.Modelo.Vacunas;

public class Partida {
    private int idJugador;
    private int enfermedadesActivas;
    private int dificultad;
    private boolean resultado;
    
    // Variables para dificultad
    public static int ciudadesInfectadasInicio = 0;
    public static int cuidadesInfectadasRonda = 0;
    public static int enfermedadesActivasDerrota = 0;
    public static int brotesActivosDerrota = 0;
    public static int numVecesInvestigar = 0;
    
    public static ArrayList<Ciudad> ciudadList = new ArrayList<Ciudad>();
    public static ArrayList<Enfermedad> listaEnfermedades= new ArrayList<Enfermedad>();
    public static Turno turno;
    public static Tienda tienda;
    
    public Partida(int idJugador, int dificultad) throws IOException{
        this.idJugador = idJugador;
        this.enfermedadesActivas = 0;
        this.resultado = false;
        this.dificultad = dificultad;
        
        tienda = new Tienda();
        turno = new Turno();
        ciudadList = new ArrayList<Ciudad>();
        listaEnfermedades= new ArrayList<Enfermedad>();
        
        this.leerParametrosDificultad();
        this.colocarCiudades();
        turno.todasciudades = Partida.ciudadList;
        turno.primerTurno();
        Vacunas.crearVacunas();
    }	

    //Metodos
    public void guardarPartida(){
        System.out.println("Se ha guardado la partida.");
    }

    public void cargarPartida(){
        System.out.println("Se ha cargado la partida.");
    }

    public boolean haPerdido(){
        if(turno.getBrotesTotales() > getBrotesActivosDerrota()){
            System.out.println("El jugador ha perdido porque ha superado los "+getBrotesActivosDerrota()+" brotes totales.");
            return true;
        }
        
        for (Integer colorActivo : turno.contarColoresActivos().values()) {
			if(colorActivo >= getEnfermedadesActivas()) {
				System.out.println("El jugador ha perdido porque ha superado los colores de enfermedades.");
	            return true;
			}
		}

        return false;
    }
    
    public boolean haGanado(){
    	int contador = 0;
		for (Vacunas vacuna : Vacunas.TodasVacunas) {
			if(vacuna.getVacunaInvestigada()) {
				contador++;
			}
		}
		
		if(contador == 4) {
			return true;
		}
		
        return false;
    }

    public void infectarCiudad(Ciudad ciudad){
        System.out.println("La ciudad " + ciudad.getNombreCiudad() + " ha sido infectada.");
    }

    public void infectarCiudadesAleatorias(){
        System.out.println("Se ha infectado una nueva ciudad.");
    }
    
    public void colocarCiudades() throws IOException {
    	HashMap<String, HashMap<String, String>> listaCiudades = new HashMap<String, HashMap<String, String>>();
    	
    	listaCiudades = Archivos.leerTxt("src\\main\\java\\Assets\\ArchivosTxt");
        
    	
    	for (HashMap<String, String> ciudad : listaCiudades.values()) {
    		ArrayList<String> colindantes = new ArrayList<String>();
    		String[] colindantesTmp = ciudad.get("Colindantes").split(",");
    		
    		for (String city : colindantesTmp) {
				colindantes.add(city);
			}	
    		
    		Ciudad ciudadObj = new Ciudad(ciudad.get("Name"), Integer.parseInt(ciudad.get("Color")), Integer.parseInt(ciudad.get("X")), Integer.parseInt(ciudad.get("Y")), colindantes);
    		ciudadList.add(ciudadObj);
    	}

    }
    
    public void leerParametrosDificultad() {
    	ArrayList<String> parametros = Archivos.readXML("src\\main\\java\\Assets\\ArchivosDificultad\\parametros"+dificultad+".xml");
    	ciudadesInfectadasInicio = Integer.parseInt(parametros.get(0));
    	cuidadesInfectadasRonda = Integer.parseInt(parametros.get(1));
    	enfermedadesActivasDerrota = Integer.parseInt(parametros.get(2));
    	brotesActivosDerrota = Integer.parseInt(parametros.get(3));
    	numVecesInvestigar = Integer.parseInt(parametros.get(4));
    }

    // Getters y Setters
    public void setIdJugador(int idJugador){
        this.idJugador = idJugador;
    }

    public int getIdJugador(){
        return this.idJugador;
    }

    public void setEnfermedadesActivas(int enfermedadesActivasDerrota){
        this.enfermedadesActivasDerrota = enfermedadesActivasDerrota;
    }

    public int getEnfermedadesActivas(){
        return this.enfermedadesActivasDerrota;
    }

    public void setDificultad(int dificultad){
        this.dificultad = dificultad;
    }

    public int getDificultad(){
        return this.dificultad;
    }

    public void setResultado(boolean resultado){
        this.resultado = resultado;
    }

    public boolean getResultado(){
        return this.resultado;
    }
    
    public void setCiudadesInfecInicio(int ciudadesInfectadasInicio){
        ciudadesInfectadasInicio = ciudadesInfectadasInicio;
    }

    public int getCiudadesInfecInicio(){
        return ciudadesInfectadasInicio;
    }
    
    public void setCiudadesInfecRonda(int cuidadesInfectadasRonda){
        cuidadesInfectadasRonda = cuidadesInfectadasRonda;
    }

    public int getCiudadesInfecRonda(){
        return cuidadesInfectadasRonda;
    }
    
    public void setEnfermedadesActivasDerrota(int enfermedadesActivasDerrota){
        enfermedadesActivasDerrota = enfermedadesActivasDerrota;
    }

    public int getEnfermedadesActivasDerrota(){
        return enfermedadesActivasDerrota;
    }
    
    public void setBrotesActivosDerrota(int brotesActivosDerrota){
        brotesActivosDerrota = brotesActivosDerrota;
    }

    public int getBrotesActivosDerrota(){
        return brotesActivosDerrota;
    }
    
    public static void setCiudades(ArrayList<Ciudad> ciudadesList) {  	
    	ciudadList = ciudadesList;
    }
    
    public static ArrayList<Ciudad> getCiudades() {  	
    	return ciudadList;
    }
    
    public static void setEnfermedades(ArrayList<Enfermedad> enfermedadList) {  	
    	listaEnfermedades = enfermedadList;
    }
    
    public static ArrayList<Enfermedad> getEnfermedades() {  	
    	return listaEnfermedades;
    }
}
