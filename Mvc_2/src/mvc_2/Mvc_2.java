/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_2;

import Mvc.Controlador.Controlador_Menu_Principal;
import Mvc.Controlador.Controlador_Persona;
import Mvc.Modelo.SQL_Persona;
import Mvc.Controlador.Controlador_Producto;
import Mvc.Vista.Interfaz_Producto;
import Mvc.Modelo.SQL_Producto;
import Mvc.Vista.Interfaz_Persona;
import Mvc.Vista.Menu_Principal;
import java.util.UUID;

/**
 *
 * @author Carlo
 */
public class Mvc_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        /*Interfaz_Persona areaTrabajo = new Interfaz_Persona();
        Controlador_Persona area = new Controlador_Persona(areaTrabajo);
        areaTrabajo.setVisible(true);*/
        
        /*Interfaz_Producto areaTrabajo = new Interfaz_Producto();
        Controlador_Producto area = new Controlador_Producto(areaTrabajo);
        areaTrabajo.setVisible(true);*/
        
        Menu_Principal areaTrabajo = new Menu_Principal();
        Controlador_Menu_Principal area = new Controlador_Menu_Principal(areaTrabajo);
        areaTrabajo.setVisible(true);
        
         

    }
}
