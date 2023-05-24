/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_2;

import Mvc.Persona.Controlador_Persona;
import Mvc.Persona.Interfaz_Persona;
import Mvc.Persona.SQL_Persona;

/**
 *
 * @author Carlo
 */
public class Mvc_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        Interfaz_Persona areaTrabajo = new Interfaz_Persona();
        Controlador_Persona area = new Controlador_Persona(areaTrabajo);
        areaTrabajo.setVisible(true);
    }
}
