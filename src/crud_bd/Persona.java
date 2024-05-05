/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_bd;

import java.util.Date;

/**
 *
 * @author WinOs
 */
public class Persona {
    private String nombre;
    private String cedula;
    private String sexo;
    private double estatura;
    private Date fechaNacimiento;

    public Persona(String nombre, String cedula, String sexo, double estatura, Date fechaNacimiento) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.sexo = sexo;
        this.estatura = estatura;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{"
                + "nombre='" + nombre + '\''
                + ", cedula='" + cedula + '\''
                + ", sexo='" + sexo + '\''
                + ", estatura=" + estatura
                + ", fechaNacimiento=" + fechaNacimiento
                + '}';
    }
}
