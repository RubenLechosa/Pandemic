package com.mycompany.pandemic.Modelo;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.mycompany.pandemic.Controlador.Partida;

public  class Enfermedad {
    private int idEnfermedad;
    private String nombre;
    private int nivelInfeccion;
    private String icono;
    private String color;
    
    public Enfermedad(int idEnfermedad,String nombre, String icono, String color){
        this.idEnfermedad= idEnfermedad;
    	this.nombre = nombre;
        this.icono = icono;
        this.color = color;
        this.nivelInfeccion = 0;
    }

   ///Metodos    
    public int verEstadoVacuna(){
        System.out.println("Llevas un 0% de investigacion de la vacuna");
        return 0;
    }

    //Getters y Setters
    public void setIdEnfermedad(int idEnfermedad){
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdEnfermedad(){
    	Partida.listaEnfermedades.get(idEnfermedad);
        return this.idEnfermedad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public void setIcono(String icono){
        this.icono = icono;
    }

    public String getIcono(){
        return this.icono;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
    
    public void setNivelInfeccion(int nivelInfeccion){
        this.nivelInfeccion = nivelInfeccion;
    }

    public int getNivelInfeccion(){
        return this.nivelInfeccion;
   }
}