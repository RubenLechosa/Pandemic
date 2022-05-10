package com.mycompany.pandemic.Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mycompany.pandemic.Controlador.Partida;

public class Vacunas {

	private int porcentajeVacuna;
	private boolean vacunaInvestigada;
	private String colorVacuna;

	public static ArrayList<Vacunas> TodasVacunas = new ArrayList<Vacunas>();

	public Vacunas(int porcentajeVacuna, boolean vacunaInvestigada, String colorVacuna) {
		this.porcentajeVacuna = porcentajeVacuna;
		this.vacunaInvestigada = vacunaInvestigada;
		this.colorVacuna = colorVacuna;

	}

	public static void crearVacunas() throws IOException {
		HashMap<Integer, HashMap<String, String>> listEnfermedades = new HashMap<Integer, HashMap<String, String>>();
		listEnfermedades = Archivos.LecturaFicheroBin("src\\main\\java\\Assets\\Archivos Bin\\CCP.bin");
                TodasVacunas.clear();
                
		for (HashMap<String, String> enfermedad : listEnfermedades.values()) {
			Vacunas vacuna = new Vacunas(0, true, enfermedad.get("colorEnfermedad"));
			TodasVacunas.add(vacuna);

		}

		//0 azul 1 rojo 2 verde 3 amarillo
	}
	
	public static boolean investigarVacunaColor(int colorId) {
		if (Partida.turno.investigarVacuna() == true) {
			if (Vacunas.TodasVacunas.get(colorId).getPorcentajeVacuna() <= 75) {
				Vacunas.TodasVacunas.get(colorId)
						.setPorcentajeVacuna(Vacunas.TodasVacunas.get(colorId).getPorcentajeVacuna() + 25);
				System.out.println("Se ha investigado vacuna "+Vacunas.TodasVacunas.get(colorId).getColorVacuna()+", esta un "
						+ Vacunas.TodasVacunas.get(colorId).getPorcentajeVacuna() + "% investigada.");
				if (Vacunas.TodasVacunas.get(colorId).getPorcentajeVacuna() == 100) {
					Vacunas.TodasVacunas.get(colorId).setVacunaInvestigada(true);
					System.out.println("Se ha acabado de investigar la vacuna "+Vacunas.TodasVacunas.get(colorId).getColorVacuna());
				}
			}
                        return true;
		} else {
                    return false;
                }
	}

	public void Vacuna0() {

		TodasVacunas.get(0).setVacunaInvestigada(true);
		TodasVacunas.get(0).setPorcentajeVacuna(10);
	}

	public void setPorcentajeVacuna(int porcentajeVacuna) {
		this.porcentajeVacuna = porcentajeVacuna;
	}

	public int getPorcentajeVacuna() {
		return this.porcentajeVacuna;
	}

	public void setVacunaInvestigada(boolean vacunaInvestigada) {
		this.vacunaInvestigada = vacunaInvestigada;
	}

	public boolean getVacunaInvestigada() {
		return this.vacunaInvestigada;
	}

	public void setColorVacuna(String colorVacuna) {
		this.colorVacuna = colorVacuna;
	}

	public String getColorVacuna() {
		return this.colorVacuna;
	}

	// crear arraylist tipo vacuna en partida para guardar todos los objetos de
	// vacuna , en el constructor cada vacuna tiene un porcentaje y un booleano y un
	// string del color de la vacuna

}
