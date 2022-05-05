package com.mycompany.pandemic.Modelo;

import java.io.IOException;

import javax.swing.JLabel;
import com.mycompany.pandemic.Modelo.Turno;
import com.mycompany.pandemic.Controlador.Partida;

public class Tienda extends Turno {
    
	private int dinero = 0;
	private int dineroPorTurno = 0;

	public Tienda() {
		this.dinero = 100;
		this.dineroPorTurno = 5;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public int getDinero() {
		return this.dinero;
	}

	public void setDineroTurno(int dineroTurno) {
		this.dineroPorTurno = dineroTurno;
	}

	public int getDineroTurno() {
		return this.dineroPorTurno;
	}

	public void sumardineroturno() {
		setDinero(dinero + dineroPorTurno);
		System.out.println("Dinero: " + getDinero());
	}

	public boolean sumaraccion() {
		int costesumaraccion = 20;
		if (getDinero() >= costesumaraccion && Partida.turno.getPuntosTotales() < 4) {
			Partida.turno.setPuntosTotales(Partida.turno.getPuntosTotales() + 1);
			setDinero(dinero - costesumaraccion);
			System.out.println("Ahora tienes " + Partida.turno.getPuntosTotales() + " acciones.");
			System.out.println("Te queda " + getDinero() + " de dinero");
                        return true;
		}else {
                        return false;
		}
	}

	public boolean sumareconomia() {
		int costesumareconomia = 40;
		if (getDinero() >= costesumareconomia) {
			setDineroTurno(dineroPorTurno + 5);
			setDinero(dinero - costesumareconomia);
			System.out.println("Te queda " + getDinero() + " de dinero");
                        return true;
		} else {
                        return false;
		}

	}
	
	
	public void mejorarlaboratorio() {
		int costemejorarlaboratorio = 5;
		if (getDinero() >= costemejorarlaboratorio) {
			setDinero(dinero - costemejorarlaboratorio);
			System.out.println("Te queda " + getDinero() + " de dinero");
		} else {
			System.out.println("No tienes suficiente dinero para comprar esto.");
		}

	}
	
	
	
}
