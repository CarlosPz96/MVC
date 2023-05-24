/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Persona;

import Mvc.conexion.Conexion_Base_Datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author CARLOS
 */
public class SQL_Persona {

    //INNSERTAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_INSERTAR = ("INSERT INTO clase.persona (idpersona,nombres,apellidos,fechanacimiento,telefono,sexo,sueldo,cupo,foto,correo) VALUES(?,?,?,?,?,?,?,?,?,?)");

    //SELECCIONAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_SELECCIONAR = "SELECT idpersona,nombres,apellidos,fechanacimiento,telefono,sexo,sueldo,cupo,foto,correo FROM clase.persona";

    //ACTUALIZAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ACTUALIZAR = "UPDATE clase.persona SET nombres=?,apellidos=?,fechanacimiento=?,telefono=?,sexo=?,sueldo=?,cupo=?,foto=?,correo=? WHERE idpersona=?";

    //BORRAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ELIMINAR = "DELETE FROM clase.persona WHERE idpersona=?";

    //METODO DE INSERTAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int insertar(Clase_Persona insert) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection con = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {

            con = miconector.ConectarBase();
            stmt = con.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, insert.getIdPersona());
            stmt.setString(2, insert.getNombresPersona());
            stmt.setString(3, insert.getApellidosPersona());
            stmt.setDate(4, (java.sql.Date) insert.getFechaNacimiento());
            stmt.setString(5, insert.getTelefonoPersona());
            stmt.setString(6, insert.getSexoPersona());
            stmt.setDouble(7, insert.getSueldoPersona());
            stmt.setInt(8, insert.getCupoPersona());
            stmt.setString(9, insert.getFotoPersona());
            stmt.setString(10, insert.getCorreoPersona());

            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                miconector.close(stmt);
                miconector.close(con);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return registros;
        
    }
    //FIN METODO INSERTAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //METODO DE SELECCIONAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Clase_Persona> seleccionar() {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clase_Persona persona = null;
        List<Clase_Persona> lista = new ArrayList<>();

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_SELECCIONAR);
            rs = stmt.executeQuery();
            while (rs.next()) {

                //SECCION SQL              
                String cedula = rs.getString("idpersona");
                String nombre = rs.getString("nombres");
                String Apellido = rs.getString("apellidos");
                Date fecha_nacimiento = rs.getDate("fechanacimiento");
                String telefono = rs.getString("telefono");
                String sexo = rs.getString("sexo");
                double sueldo = rs.getDouble("sueldo");
                int cupo = rs.getInt("cupo");
                String foto = rs.getString("foto");
                String correo = rs.getString("correo");

                persona = new Clase_Persona();

                //MANDAR DATOS
                persona.setIdPersona(cedula);
                persona.setNombresPersona(nombre);
                persona.setApellidosPersona(Apellido);
                persona.setFechaNacimiento(fecha_nacimiento);
                persona.setTelefonoPersona(telefono);
                persona.setSexoPersona(sexo);
                persona.setSueldoPersona(sueldo);
                persona.setCupoPersona(cupo);
                persona.setFotoPersona(foto);
                persona.setCorreoPersona(correo);

                lista.add(persona);
            
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                //miconector.close(rs);
                miconector.close(stmt);
                miconector.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return lista;
    }
    //FIN METODO SELECIONAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //METODO DE ACTUALIZAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int actualizar(Clase_Persona actua) {
        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);
            
            stmt.setString(1, actua.getNombresPersona());
            stmt.setString(2, actua.getApellidosPersona());
            stmt.setDate(3, (java.sql.Date) actua.getFechaNacimiento());
            stmt.setString(4, actua.getTelefonoPersona());
            stmt.setString(5, actua.getSexoPersona());
            stmt.setDouble(6, actua.getSueldoPersona());
            stmt.setInt(7, actua.getCupoPersona());
            stmt.setString(8, actua.getFotoPersona());
            stmt.setString(9, actua.getCorreoPersona());
            stmt.setString(10, actua.getIdPersona());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                miconector.close(stmt);
                miconector.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    //FIN METODO DE ACTUALIZAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //METODO DE ELIMINAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int eliminar(Clase_Persona elimin) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setString(1, elimin.getIdPersona());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                miconector.close(stmt);
                miconector.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    //FIN METODO ELIMINAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
