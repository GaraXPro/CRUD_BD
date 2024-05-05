/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WinOs
 */
public class Conectar {
    
        private String cadena;
    private String usuario;
    private String password;

    public Conectar(String cadena, String usuario, String password) {
        this.cadena = cadena;
        this.password = password;
        this.usuario = usuario;
    }

    public Connection getConexion() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(this.cadena, this.usuario, this.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
    
}
