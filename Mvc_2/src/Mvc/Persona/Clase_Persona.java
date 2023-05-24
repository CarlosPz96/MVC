/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Persona;

import java.util.Date;

/**
 *
 * @author Carlo
 */
public class Clase_Persona {

    private String idPersona;
    private String nombresPersona;
    private String apellidosPersona;
    private java.sql.Date fechaNacimiento;
    private String telefonoPersona;
    private String sexoPersona;
    private double sueldoPersona;
    private int cupoPersona;
    private String fotoPersona;
    private String correoPersona;
    
    
    public Clase_Persona() {
    }

    public Clase_Persona(String idPersona) {
        this.idPersona = idPersona;
    }
    
    

    public Clase_Persona(String idPersona, String nombresPersona, String apellidosPersona, java.sql.Date fechaNacimiento, String telefonoPersona, String sexoPersona, double sueldoPersona, int cupoPersona, String fotoPersona, String correoPersona) {
        this.idPersona = idPersona;
        this.nombresPersona = nombresPersona;
        this.apellidosPersona = apellidosPersona;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoPersona = telefonoPersona;
        this.sexoPersona = sexoPersona;
        this.sueldoPersona = sueldoPersona;
        this.cupoPersona = cupoPersona;
        this.fotoPersona = fotoPersona;
        this.correoPersona = correoPersona;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombresPersona() {
        return nombresPersona;
    }

    public void setNombresPersona(String nombresPersona) {
        this.nombresPersona = nombresPersona;
    }

    public String getApellidosPersona() {
        return apellidosPersona;
    }

    public void setApellidosPersona(String apellidosPersona) {
        this.apellidosPersona = apellidosPersona;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public String getSexoPersona() {
        return sexoPersona;
    }

    public void setSexoPersona(String sexoPersona) {
        this.sexoPersona = sexoPersona;
    }

    public double getSueldoPersona() {
        return sueldoPersona;
    }

    public void setSueldoPersona(double sueldoPersona) {
        this.sueldoPersona = sueldoPersona;
    }

    public int getCupoPersona() {
        return cupoPersona;
    }

    public void setCupoPersona(int cupoPersona) {
        this.cupoPersona = cupoPersona;
    }

    public String getFotoPersona() {
        return fotoPersona;
    }

    public void setFotoPersona(String fotoPersona) {
        this.fotoPersona = fotoPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }
   
}
