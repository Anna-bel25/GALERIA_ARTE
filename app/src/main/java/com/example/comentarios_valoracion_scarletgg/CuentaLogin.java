package com.example.comentarios_valoracion_scarletgg;

public class CuentaLogin {

    private int id;
    private String usuario;
    private String contraseña;

    // Constructor
    public CuentaLogin() {
    }

    public CuentaLogin(int id, String usuario, String contraseña) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
