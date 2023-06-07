/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Modelo;

import Mvc.Modelo.Clase_Productos;
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
public class SQL_Producto {

    //INNSERTAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_INSERTAR = ("INSERT INTO clase.producto (idproducto,nombreproducto,precioproducto,cantidadproducto,fotoproducto,descripcionproducto) VALUES(?,?,?,?,?,?)");

    //SELECCIONAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_SELECCIONAR = "SELECT idproducto,nombreproducto,precioproducto,cantidadproducto,fotoproducto,descripcionproducto FROM clase.producto";

    //ACTUALIZAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ACTUALIZAR = "UPDATE clase.producto SET nombreproducto=?,precioproducto=?,cantidadproducto=?,fotoproducto=?,descripcionproducto=? WHERE idproducto=?";

    //ACTUALIZAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ACTUALIZARSTOCK = "UPDATE clase.producto SET cantidadproducto=? WHERE idproducto=?";

    //BORRAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ELIMINAR = "DELETE FROM clase.producto WHERE idproducto=?";

    //METODO DE INSERTAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int insertar(Clase_Productos insert) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection con = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {

            con = miconector.ConectarBase();
            stmt = con.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, insert.getIdProducto());
            stmt.setString(2, insert.getNombreProducto());
            stmt.setDouble(3, insert.getPrecioProducto());
            stmt.setInt(4, insert.getCantidadProducto());
            stmt.setString(5, insert.getFotoProducto());
            stmt.setString(6, insert.getDescripcionProducto());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //System.out.println("cedula ya registrada");
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
    public List<Clase_Productos> seleccionar() {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clase_Productos producto = null;
        List<Clase_Productos> lista = new ArrayList<>();

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_SELECCIONAR);
            rs = stmt.executeQuery();
            while (rs.next()) {

                //SECCION SQL              
                int id = rs.getInt("idproducto");
                String nombre = rs.getString("nombreproducto");
                Double precio = rs.getDouble("precioproducto");
                int cantidad = rs.getInt("cantidadproducto");
                String foto = rs.getString("fotoproducto");
                String descripcion = rs.getString("descripcionproducto");

                producto = new Clase_Productos();

                //MANDAR DATOS
                producto.setIdProducto(id);
                producto.setNombreProducto(nombre);
                producto.setPrecioProducto(precio);
                producto.setCantidadProducto(cantidad);
                producto.setFotoProducto(foto);
                producto.setDescripcionProducto(descripcion);

                lista.add(producto);

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
    public int actualizar(Clase_Productos actua) {
        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {

            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);

            stmt.setString(1, actua.getNombreProducto());
            stmt.setDouble(2, actua.getPrecioProducto());
            stmt.setInt(3, actua.getCantidadProducto());
            stmt.setString(4, actua.getFotoProducto());
            stmt.setString(5, actua.getDescripcionProducto());
            stmt.setInt(6, actua.getIdProducto());

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
    
    public int actualizarSotck(Clase_Productos actua) {
        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {

            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ACTUALIZARSTOCK);
            
            stmt.setInt(1, actua.getCantidadProducto());
            stmt.setInt(2, actua.getIdProducto());

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
    public int eliminar(Clase_Productos elimin) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setInt(1, elimin.getIdProducto());
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
