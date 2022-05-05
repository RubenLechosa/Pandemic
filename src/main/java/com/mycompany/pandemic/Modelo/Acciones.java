package Modelo;

public abstract class Acciones {
    private String nombre;
    private int coste;
    private String descripcion;
    private String icono;

    public Acciones(String nombre, int coste, String descripcion, String icono){
        this.nombre = nombre;
        this.coste = coste;
        this.descripcion = descripcion;
        this.icono = icono;
    }

    // Getters y Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setCoste(int coste){
        this.coste = coste;
    }
    
    public int getCoste(){
        return this.coste;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public void setIcono(String icono){
        this.icono = icono;
    }
    
    public String getIcono(){
        return this.icono;
    }
}
