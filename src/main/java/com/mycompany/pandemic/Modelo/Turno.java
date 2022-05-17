package com.mycompany.pandemic.Modelo;

import java.util.Random;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;

import com.mycompany.pandemic.Controlador.Partida;
import java.math.BigDecimal;

public class Turno extends JLabel {
	private int numTurno = 1;
	private int puntosTotales = 0;
	
	protected int brotesTotales;
	protected ArrayList<Integer> ciudadesInfectadas = new ArrayList<Integer>();
	protected ArrayList<Enfermedad> todasEnfermedades = new ArrayList<Enfermedad>();
	public static ArrayList<Ciudad> todasciudades = new ArrayList<Ciudad>();
	protected ArrayList<String> ciudadesInfectadasTurno = new ArrayList<String>();

	public Turno() {
		this.numTurno = 1;
		this.puntosTotales = 4;
		this.brotesTotales = 0;
		this.ciudadesInfectadas = new ArrayList<Integer>();
                this.ciudadesInfectadasTurno = new ArrayList<String>();
		
	}
        
        public Turno(BigDecimal numturno2, BigDecimal brotestotales2, BigDecimal acciones) {
		this.numTurno = numturno2.intValue();
		this.brotesTotales = brotestotales2.intValue();
                this.puntosTotales = acciones.intValue();
                this.ciudadesInfectadas = new ArrayList<Integer>();
                this.ciudadesInfectadasTurno = new ArrayList<String>();
	}


	// Metodos
	public void primerTurno() {
		int numero = 0;
		Random aleatorio = new Random();
		
		for (int j = 0; j < Partida.ciudadesInfectadasInicio; j++) {
			do {
                           
				numero = aleatorio.nextInt(todasciudades.size());
			}while(ciudadesInfectadasTurno.contains(todasciudades.get(numero).getNombreCiudad()));
		
			ArrayList<Enfermedad> enfermedadesCiudad = todasciudades.get(numero).getEnfermedadList();
			enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).setNivelInfeccion(1);

			//System.out.println(todasciudades.get(numero).getNombreCiudad());
			ciudadesInfectadasTurno.add(todasciudades.get(numero).getNombreCiudad());
		}
	}
	
	public void pasarTurno() {
		this.setNumTurno(getNumTurno() + 1);
		this.setPuntosTotales(4);
		int numero = Partida.cuidadesInfectadasRonda;
		
		if(this.getNumTurno() == 3) {
			numero = Partida.ciudadesInfectadasInicio;
		}
		
		if(this.getNumTurno() != 1) {
			for (int i = numero; i > 0; i--) {
                            if(ciudadesInfectadasTurno.size() >= i){
                                System.out.println(ciudadesInfectadasTurno.remove(i));
                            }
			}
		}
		
	}
	
	public void infectarCiudadesTurno() {
		todasciudades = Partida.getCiudades();
		int numero = 0;
		Random aleatorio = new Random();
                
		Partida.Add_ConsoleLog("Ciudades Infectadas:");
                Partida.Add_ConsoleLog("Ciudades Infectadas:");
		for (int j = 0; j < Partida.cuidadesInfectadasRonda; j++) {
			do {
				numero = aleatorio.nextInt(todasciudades.size());
			}while(ciudadesInfectadasTurno.contains(todasciudades.get(numero).getNombreCiudad()));
			
			ArrayList<Enfermedad> enfermedadesCiudad = todasciudades.get(numero).getEnfermedadList();
			
			if(enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).getNivelInfeccion() >= 1) {
				
				if(enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).getNivelInfeccion() >= 3) {
					brotesTotales++;
					for (String colindante : todasciudades.get(numero).getCiudadesColindantes()) {
						for (Ciudad cityColindante : todasciudades) {
							if(cityColindante.getNombreCiudad().equals(colindante)) {
								
								ArrayList<Enfermedad> enfermedadesColindante = cityColindante.getEnfermedadList();
								Enfermedad enfermedadInfectar = enfermedadesColindante.get(todasciudades.get(numero).getColorCiudad());
														
								if(enfermedadInfectar.getNivelInfeccion() >= 2) {
									enfermedadInfectar.setNivelInfeccion(3);
									
								} else {
									enfermedadInfectar.setNivelInfeccion(enfermedadInfectar.getNivelInfeccion() + 1);
								}
								
								cityColindante.setEnfermedadList(enfermedadesColindante);
							}
							
							
						}
						
					}
					
					
					enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).setNivelInfeccion(3);
				} else {
					
					enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).setNivelInfeccion(enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).getNivelInfeccion() + 1);
				}
			} else {
				enfermedadesCiudad.get(todasciudades.get(numero).getColorCiudad()).setNivelInfeccion(1);
			}
			
                        Partida.Add_ConsoleLog(todasciudades.get(numero).getNombreCiudad());
			this.ciudadesInfectadasTurno.add(todasciudades.get(numero).getNombreCiudad());
		}
	}

	public boolean curar(Enfermedad enfermedad, String color) {
		if (this.getPuntosTotales() < 1) {
			System.out.println("No tienes suficientes acciones para curar ciudad, te quedan " + this.getPuntosTotales()
					+ " acciones este turno.");
			return false;
		} else {
                        if(enfermedad.getColor().equals(color) && enfermedad.getNivelInfeccion() > 0){
                            for (Vacunas vacuna : Vacunas.TodasVacunas) {
				if(vacuna.getVacunaInvestigada() && vacuna.getColorVacuna().equals(color)) {
					enfermedad.setNivelInfeccion(0);
					setPuntosTotales(this.getPuntosTotales() - 1);
					
					System.out.println(
							"La ciudad ha sido curada con exito!, te quedan " + this.getPuntosTotales() + " acciones este turno.");
					return true;
				}
                            }
                            setPuntosTotales(this.getPuntosTotales() - 1);
                            enfermedad.setNivelInfeccion(enfermedad.getNivelInfeccion() - 1);

                            System.out.println(
                                            "La ciudad ha sido curada con exito!, te quedan " + this.getPuntosTotales() + " acciones este turno.");
                            return true;
                        }
                        
                        return false;
			
		}
	}

	public boolean investigarVacuna() {
		if (this.getPuntosTotales() < 4) {
			System.out.println("No tienes suficientes acciones para investigar vacuna, te quedan " + this.getPuntosTotales()
					+ " acciones este turno.");
			return false;
		} else {
			System.out.println("Has investigado vacuna, te quedan " + this.getPuntosTotales() + " acciones este turno.");
			return true;
		}
	}
	
    public HashMap<String, Integer> contarColoresActivos(){
    	HashMap<String, Integer> coloresActivos = new HashMap<String, Integer>();
    	
        for (Ciudad city : todasciudades) {
			for (Enfermedad enfermedad : city.getEnfermedadList()) {
				if(coloresActivos.containsKey(enfermedad.getColor()) && enfermedad.getNivelInfeccion() > 0) {
					coloresActivos.put(enfermedad.getColor(), coloresActivos.get(enfermedad.getColor()) + enfermedad.getNivelInfeccion());
					
				} else if(!coloresActivos.containsKey(enfermedad.getColor()) && enfermedad.getNivelInfeccion() > 0) {
					coloresActivos.put(enfermedad.getColor(), enfermedad.getNivelInfeccion());
				}
			}
		}
        
        return coloresActivos;
    }

	// Getters y Setters
	public void setNumTurno(int numTurno) {
		this.numTurno = numTurno;
	}

	public int getNumTurno() {
		return this.numTurno;
	}

	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public int getPuntosTotales() {
		return this.puntosTotales;
	}

	public void setCiudadesInfectadas(ArrayList<Integer> ciudadesInfectadas) {
		this.ciudadesInfectadas = ciudadesInfectadas;
	}

	public ArrayList<Integer> getCiudadesInfectadas() {
		return this.ciudadesInfectadas;
	}
	
    public void setBrotesTotales(int brotesTotales){
        this.brotesTotales = brotesTotales;
    }

    public int getBrotesTotales(){
        return this.brotesTotales;
    }

}
