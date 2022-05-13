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
    public static int idJugador;
    public static int partidaId;
    private int enfermedadesActivas;
    public static int dificultad;
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
    public static int puntos;
    public static int jugador_username = 0;
    public static int jugador_puntos = 0;
    
    public static ArrayList<String> console_log = new ArrayList<String>();
    
    public Partida(int idJugador, int dificultad) throws IOException{
        this.enfermedadesActivas = 0;
        this.resultado = false;
        this.dificultad = dificultad;
        this.puntos = 0;
        
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
    
        public Partida(int idJugador) throws IOException{
        this.enfermedadesActivas = 0;
        this.resultado = false;

        this.leerParametrosDificultad();
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
            this.setPuntos(this.getPuntos()+5);
            Bd.borrarPartidas(idJugador);
            return true;
        }
        
        for (Integer colorActivo : turno.contarColoresActivos().values()) {
			if(colorActivo >= getEnfermedadesActivas()) {
				System.out.println("El jugador ha perdido porque ha superado los colores de enfermedades.");
                                Bd.borrarPartidas(idJugador);
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
                    
                    if(this.getDificultad() == 0){
                        this.setPuntos(this.getPuntos()+20);
                    }else if(this.getDificultad() == 1){
                        this.setPuntos(this.getPuntos()+30);
                    }else{
                        this.setPuntos(this.getPuntos()+40);
                    }
                    
                    Bd.sumarPuntos(idJugador, this.getPuntos());
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
    
    public static void Add_ConsoleLog(String log){
        console_log.add(log);
        
        while(console_log.size() >  Partida.cuidadesInfectadasRonda + 2){
            console_log.remove(0);
        }
    }

    // Getters y Setters
    public ArrayList<String> getConsoleLog(){
        return console_log;
    }
    
    public static void setPlayerId(int jogador_user){
        jugador_username = jogador_user;
    }

    public static int getPlayerId(){
        return jugador_username;
    }

    public static  void setPlayerPoints(int jogador_puntos){
        jugador_puntos = jogador_puntos;
    }

    public static  int getPlayerPoints(){
        return jugador_puntos;
    }

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
    
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }

    public int getPuntos(){
        return puntos;
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
