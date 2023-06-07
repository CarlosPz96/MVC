/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Modelo;

import Mvc.Modelo.Clase_Factura;
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
public class SQL_Factura {

    //INNSERTAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_INSERTAR = ("INSERT INTO clase.factura (idfactura,fechafactura,totalfactura,clientefactura) VALUES(?,?,?,?)");

    //SELECCIONAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_SELECCIONAR = "SELECT idfactura,fechafactura,totalfactura,clientefactura FROM clase.factura";

    //ACTUALIZAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ACTUALIZAR = "UPDATE clase.factura SET fechafactura=?,totalfactura=?,clientefactura=? WHERE idfactura=?";

    //ACTUALIZAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ACTUALIZARTOTTAL = "UPDATE clase.factura SET totalfactura=? WHERE idfactura=?";

    //BORRAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ELIMINAR = "DELETE FROM clase.factura WHERE idfactura=?";

    //METODO DE INSERTAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int insertar(Clase_Factura insert) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection con = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {

            con = miconector.ConectarBase();
            stmt = con.prepareStatement(SQL_INSERTAR);

            stmt.setString(1, insert.getIdFactura());
            stmt.setDate(2, insert.getFechaFactura());
            stmt.setDouble(3, insert.getTotal());
            stmt.setString(4, insert.getCedulaCliente());

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
    public List<Clase_Factura> seleccionar() {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clase_Factura factura = null;
        List<Clase_Factura> lista = new ArrayList<>();

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_SELECCIONAR);
            rs = stmt.executeQuery();
            while (rs.next()) {

                //SECCION SQL              
                String id = rs.getString("idfactura");
                Date fecha = rs.getDate("fechafactura");
                Double total = rs.getDouble("totalfactura");
                String cliente = rs.getString("clientefactura");

                factura = new Clase_Factura();

                //MANDAR DATOS
                factura.setIdFactura(id);
                factura.setFechaFactura(fecha);
                factura.setTotal(total);
                factura.setCedulaCliente(cliente);

                lista.add(factura);

            }
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

        return lista;
    }
    //FIN METODO SELECIONAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //METODO DE ACTUALIZAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int actualizar(Clase_Factura actua) {
        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {

            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setDate(1, actua.getFechaFactura());
            stmt.setDouble(2, actua.getTotal());
            stmt.setString(3, actua.getCedulaCliente());
            stmt.setString(4, actua.getIdFactura());

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

    //METODO DE ACTUALIZAR TOTAL/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int actualizarTotal(Clase_Factura actua) {
        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {

            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ACTUALIZARTOTTAL);

            stmt.setDouble(1, actua.getTotal());
            stmt.setString(2, actua.getIdFactura());

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
    public int eliminar(Clase_Factura elimin) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setString(1, elimin.getIdFactura());
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
