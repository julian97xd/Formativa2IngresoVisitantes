package com.example.formativa2;

public class Contacto {
    int id;
    String  NombreVisitante;
    int Cedula;
    String Apto;
    String TipoVisitante;
    String Efecha;
    int Ehora;

    public Contacto() {

    }

    public Contacto(String nombreVisitante, int cedula, String apto, String tipoVisitante, String efecha, int ehora) {
        NombreVisitante = nombreVisitante;
        Cedula = cedula;
        Apto = apto;
        TipoVisitante = tipoVisitante;
        Efecha = efecha;
        Ehora = ehora;

    }
    public Contacto(int id, String nombreVisitante, int cedula , String apto, String tipoVisitante,String efecha, int ehora) {
        this.id = id;
        NombreVisitante = nombreVisitante;
        Cedula  = cedula;
        Apto = apto;
        TipoVisitante = tipoVisitante;
        Efecha = efecha;
        Ehora = ehora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreVisitante() {
        return NombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        NombreVisitante = nombreVisitante;
    }

    public int getCedula() {
        return Cedula;
    }

    public void setCedula( int cedula) {
        Cedula = cedula;
    }

    public String getApto() {
        return Apto;
    }

    public void setApto(String apto) {
        Apto = apto;
    }

    public String getTipoVisitante() {
        return TipoVisitante;
    }

    public void setTipoVisitante(String tipoVisitante) {
        TipoVisitante = tipoVisitante;
    }

    public String getEfecha() {
        return Efecha;
    }

    public void setEfecha(String efecha) {
        Efecha = efecha;
    }

    public int getEhora() {
        return Ehora;
    }

    public void setEhora(int ehora) {
        Ehora = ehora;
    }
}
