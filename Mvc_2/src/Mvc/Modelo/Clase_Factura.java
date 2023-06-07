/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Modelo;

import java.sql.Date;

/**
 *
 * @author Carlo
 */
public class Clase_Factura {

    private String idFactura;
    private Date fechaFactura;
    private Double total;
    private String cedulaCliente;

    public Clase_Factura() {
    }

    public Clase_Factura(String idFactura) {
        this.idFactura = idFactura;
    }

    public Clase_Factura(String idFactura, Date fechaFactura, Double total, String cedulaCliente) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.total = total;
        this.cedulaCliente = cedulaCliente;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

}
