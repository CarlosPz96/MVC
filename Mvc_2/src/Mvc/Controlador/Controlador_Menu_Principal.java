/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controlador;

import Mvc.Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlo
 */
public class Controlador_Menu_Principal implements ActionListener {

    Menu_Principal vistaMenu;

    public Controlador_Menu_Principal(Menu_Principal evento1) {
        this.vistaMenu = evento1;

        //BOTONES
        this.vistaMenu.btnClientes.addActionListener(this);
        this.vistaMenu.btnProducto.addActionListener(this);
        this.vistaMenu.btnFactura.addActionListener(this);
        this.vistaMenu.btn_persona.addActionListener(this);
        this.vistaMenu.btn_producto.addActionListener(this);
        this.vistaMenu.btn_factura.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaMenu.btnClientes) {
            menuPersonas();
        }
        if (e.getSource() == vistaMenu.btn_persona) {
            menuPersonas();
        }
        if (e.getSource() == vistaMenu.btnProducto) {
            menuProductos();
        }
        if (e.getSource() == vistaMenu.btn_producto) {
            menuProductos();
        }
        if (e.getSource() == vistaMenu.btnFactura) {
            menuFactura();
        }
        if (e.getSource() == vistaMenu.btn_factura) {
            menuFactura();
        }
    }

    private void menuPersonas() {
        Interfaz_Persona vistaPersona = new Interfaz_Persona();
        Controlador_Persona area = new Controlador_Persona(vistaPersona);
        vistaMenu.DskPrincipal.add(vistaPersona);
        vistaPersona.show();
    }

    private void menuProductos() {
        Interfaz_Producto vistaPersona = new Interfaz_Producto();
        Controlador_Producto area = new Controlador_Producto(vistaPersona);
        vistaMenu.DskPrincipal.add(vistaPersona);
        vistaPersona.show();
    }

    private void menuFactura() {
        Interfaz_Factura vistaPersona = new Interfaz_Factura();
        Controlador_Factura area = new Controlador_Factura(vistaPersona);
        vistaMenu.DskPrincipal.add(vistaPersona);
        vistaPersona.show();
    }
}
