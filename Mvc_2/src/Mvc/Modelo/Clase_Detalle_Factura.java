/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Modelo;

/**
 *
 * @author Carlo
 */
public class Clase_Detalle_Factura {

    private int idDetalle;
    private String idFactura;
    private int idProducto;
    private int cantidadDetalle;
    private Double precioDetalle;
    private Double totalDetalle;

    public Clase_Detalle_Factura() {
    }

    public Clase_Detalle_Factura(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Clase_Detalle_Factura(int idDetalle, String idFactura, int idProducto, int cantidadDetalle, Double precioDetalle, Double totalDetalle) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidadDetalle = cantidadDetalle;
        this.precioDetalle = precioDetalle;
        this.totalDetalle = totalDetalle;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(int cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public Double getPrecioDetalle() {
        return precioDetalle;
    }

    public void setPrecioDetalle(Double precioDetalle) {
        this.precioDetalle = precioDetalle;
    }

    public Double getTotalDetalle() {
        return totalDetalle;
    }

    public void setTotalDetalle(Double totalDetalle) {
        this.totalDetalle = totalDetalle;
    }

}
