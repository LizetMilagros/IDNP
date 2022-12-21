package com.example.x.TiposPlasticos;

public class ModeloTipoPlastico {
    private String titulo;
    private String descripcion;
    private int imagen;

    public ModeloTipoPlastico(String title, String descrip, int image) {
        this.titulo = title;
        this.descripcion = descrip;
        this.imagen = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
