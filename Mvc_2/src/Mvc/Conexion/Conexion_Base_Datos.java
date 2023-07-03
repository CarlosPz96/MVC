/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Conexion_Base_Datos {

    Connection con;
    Statement st;
    String url = "jdbc:mysql://localhost:3306/clase?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    String driver = "com.mysql.cj.jdbc.Driver";

    public Connection ConectarBase() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            System.out.println("No se conecto ala base de datos");
            System.out.println(e.toString());
        }
        return con;
    }

    public void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public void close(Statement smtm) throws SQLException {
        smtm.close();
    }

    public void close(PreparedStatement smtm) throws SQLException {
        smtm.close();
    }

    public void close(Connection con) throws SQLException {
        con.close();
    }

    public Connection getCon() {
        return con;
    }
}
