/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WinOs
 */
public class PersonaDao {
    Conectar con = null;

    public PersonaDao() {
        this.con = new Conectar("jdbc:mysql://localhost/persona", "root", "");
    }

    public void crearPersona(Persona persona) {

        if (obtenerPersonaPorCedula(persona.getCedula()) != null) {
            System.out.println("Ya existe una persona con la misma cédula. No se puede crear.");
            return;
        }

        try (Connection condb = con.getConexion(); PreparedStatement ps = condb.prepareStatement("INSERT INTO persona (nombre, cedula, sexo, estatura, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)")) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getCedula());
            ps.setString(3, persona.getSexo());
            ps.setDouble(4, persona.getEstatura());
            ps.setDate(5, new java.sql.Date(persona.getFechaNacimiento().getTime()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Persona obtenerPersonaPorCedula(String cedula) {
        try (Connection condb = con.getConexion(); PreparedStatement ps = condb.prepareStatement("SELECT * FROM persona WHERE cedula = ?")) {

            ps.setString(1, cedula);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return new Persona(
                            resultSet.getString("nombre"),
                            resultSet.getString("cedula"),
                            resultSet.getString("sexo"),
                            resultSet.getDouble("estatura"),
                            resultSet.getDate("fecha_nacimiento")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarPersona(Persona persona) {

        Persona personaExistente = obtenerPersonaPorCedula(persona.getCedula());
        if (personaExistente == null) {
            System.out.println("No se encontró ninguna persona con esa cédula.");
            return;
        }

        try (Connection condb = con.getConexion(); PreparedStatement ps = condb.prepareStatement("UPDATE persona SET nombre = ?, sexo = ?, estatura = ?, fecha_nacimiento = ? WHERE cedula = ?")) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getSexo());
            ps.setDouble(3, persona.getEstatura());
            ps.setDate(4, new java.sql.Date(persona.getFechaNacimiento().getTime()));
            ps.setString(5, persona.getCedula());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarPersona(String cedula) {
        Persona personaExistente = obtenerPersonaPorCedula(cedula);
        if (personaExistente == null) {
            System.out.println("No se encontró ninguna persona con esa cédula.");
            return false;
        }

        try (Connection condb = con.getConexion(); PreparedStatement ps = condb.prepareStatement("DELETE FROM persona WHERE cedula = ?")) {
            ps.setString(1, cedula);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Persona> obtenerTodasLasPersonas() {
        List<Persona> personas = new ArrayList<>();

        try (Connection condb = con.getConexion(); PreparedStatement ps = condb.prepareStatement("SELECT * FROM persona"); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String cedula = resultSet.getString("cedula");
                String sexo = resultSet.getString("sexo");
                double estatura = resultSet.getDouble("estatura");
                java.util.Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                personas.add(new Persona(nombre, cedula, sexo, estatura, fechaNacimiento));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }
}
