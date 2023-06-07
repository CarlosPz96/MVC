/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Modelo;

import Mvc.Modelo.Clase_Detalle_Factura;
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
public class SQL_Detalle_Factura {
    //INNSERTAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_INSERTAR = ("INSERT INTO clase.detallefactura (iddetalle,idfactura,idproducto,cantidaddetalle,preciodetalle,totaldetalle) VALUES(?,?,?,?,?,?)");

    //SELECCIONAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_SELECCIONAR = "SELECT iddetalle,idfactura,idproducto,cantidaddetalle,preciodetalle,totaldetalle FROM clase.detallefactura";

    //ACTUALIZAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ACTUALIZAR = "UPDATE clase.detallefactura SET idfactura=?,idproducto=?,cantidaddetalle=?,preciodetalle=?,totaldetalle=? WHERE iddetalle=?";

    //BORRAR PERSONA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String SQL_ELIMINAR = "DELETE FROM clase.detallefactura WHERE idfactura=?";

    //METODO DE INSERTAR/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int insertar(Clase_Detalle_Factura insert) {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection con = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {

            con = miconector.ConectarBase();
            stmt = con.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, insert.getIdDetalle());
            stmt.setString(2, insert.getIdFactura());
            stmt.setInt(3, insert.getIdProducto());
            stmt.setInt(4, insert.getCantidadDetalle());
            stmt.setDouble(5, insert.getPrecioDetalle());
            stmt.setDouble(6, insert.getTotalDetalle());

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
    public List<Clase_Detalle_Factura> seleccionar() {

        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clase_Detalle_Factura detalle = null;
        List<Clase_Detalle_Factura> lista = new ArrayList<>();

        try {
            conn = miconector.ConectarBase();
            stmt = conn.prepareStatement(SQL_SELECCIONAR);
            rs = stmt.executeQuery();
            while (rs.next()) {

                //SECCION SQL              
                int id = rs.getInt("iddetallle");
                String idFactura = rs.getString("idfactura");
                int idProducto = rs.getInt("idproducto");
                int cantidad = rs.getInt("cantidaddetalle");
                Double precio = rs.getDouble("preciodetalle");
                Double total = rs.getDouble("totaldetalle");

                detalle = new Clase_Detalle_Factura();

                //MANDAR DATOS
                detalle.setIdDetalle(id);
                detalle.setIdFactura(idFactura);
                detalle.setIdProducto(idProducto);
                detalle.setCantidadDetalle(cantidad);
                detalle.setPrecioDetalle(precio);
                detalle.setTotalDetalle(total);

                lista.add(detalle);

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
