package Modelo;
import java.util.ArrayList;

public class Jugador {
    private int idJugador;
    private String usuario;
    private int victorias;
    private int derrotas;

    public Jugador(String usuario){
        this.usuario = usuario;
    }

    public ArrayList<String> posicionRanking(){
        System.out.println("1- Alex Madrigal");
        System.out.println("2- Ruben Lechosa");
        System.out.println("3- Eric Escabias");
        System.out.println("4- Leo Wiesner");
        return null;
    }

    //Getters y Setters
    public void setIdJugador(int idJugador){
        this.idJugador = idJugador;
    }

    public int getIdJugador(){
        return this.idJugador;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setVictorias(int victorias){
        this.victorias = victorias;
    }

    public int getVictorias(){
        return this.victorias;
    }

    public void setDerrotas(int derrotas){
        this.derrotas = derrotas;
    }

    public int getDerrotas(){
        return this.derrotas;
    }
}
