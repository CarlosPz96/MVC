/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Persona;

import Mvc.conexion.Conexion_Base_Datos;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author CyJ
 */
public class Controlador_Persona implements ActionListener {

    SQL_Persona sql = new SQL_Persona();
    Clase_Persona gg = new Clase_Persona();
    Interfaz_Persona insertAre = new Interfaz_Persona();
    DefaultTableModel modelo = new DefaultTableModel();
    Conexion_Base_Datos miconector = new Conexion_Base_Datos();
    Connection cn = miconector.ConectarBase();

    //BOTONES
    public Controlador_Persona(Interfaz_Persona evento1) {
        this.insertAre = evento1;

        this.insertAre.btnGuardar.addActionListener(this);
        this.insertAre.btnModificar.addActionListener(this);
        this.insertAre.btnEliminar.addActionListener(this);
        this.insertAre.btnCancelar.addActionListener(this);
        this.insertAre.btnCalcular.addActionListener(this);
        this.insertAre.btnFoto.addActionListener(this);

        seleccionarPersona();
        limpiarTabla();
        listar(insertAre.tabla_personas);

    }

    //ACCIONES BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insertAre.btnGuardar) {
            agregar();
            limpiarTabla();
            listar(insertAre.tabla_personas);

        }

        if (e.getSource() == insertAre.btnCancelar) {
            limpiar();
        }

        if (e.getSource() == insertAre.btnFoto) {
            try {
                seleccionarFoto();
            } catch (IOException ex) {
                Logger.getLogger(Controlador_Persona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == insertAre.btnCalcular) {
            fecha();
        }

        if (e.getSource() == insertAre.btnModificar) {

            actualizar();
            limpiarTabla();
            listar(insertAre.tabla_personas);
        }

        if (e.getSource() == insertAre.btnEliminar) {
            int fila = insertAre.tabla_personas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una Persona");
            } else {

                String i = insertAre.tabla_personas.getValueAt(fila, 0).toString();

                Clase_Persona persona = new Clase_Persona(i);

                sql.eliminar(persona);
                JOptionPane.showMessageDialog(insertAre, "Eliminado correctamente");
                limpiarTabla();
                listar(insertAre.tabla_personas);

            }
        }

    }

    //METODO LISTAR
    public void listar(JTable tabla) {

        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Clase_Persona> listaArea = sql.seleccionar();
        Object[] objecto = new Object[10];

        for (int i = 0; i < listaArea.size(); i++) {

            objecto[0] = listaArea.get(i).getIdPersona();
            objecto[1] = listaArea.get(i).getNombresPersona();
            objecto[2] = listaArea.get(i).getApellidosPersona();
            objecto[3] = listaArea.get(i).getFechaNacimiento();
            objecto[4] = listaArea.get(i).getTelefonoPersona();
            objecto[5] = listaArea.get(i).getSexoPersona();
            objecto[6] = listaArea.get(i).getSueldoPersona();
            objecto[7] = listaArea.get(i).getCupoPersona();
            objecto[8] = listaArea.get(i).getFotoPersona();
            objecto[9] = listaArea.get(i).getCorreoPersona();

            modelo.addRow(objecto);
        }

        insertAre.tabla_personas.setModel(modelo);

    }
    //FIN METODO LISTAR

    //METODO AGREGAR
    public void agregar() {

        if (insertAre.txt_cedula.getText().isEmpty() || insertAre.txt_nombre.getText().isEmpty() || insertAre.txt_apellido.getText().isEmpty() || insertAre.txt_fecha_nacimiento == null || insertAre.txt_telefono.getText().isEmpty() || insertAre.txt_sexo.getSelectedItem().equals("SELECCIONAR") || Double.valueOf(insertAre.txt_sueldo.getText()) <= 0 || Integer.parseInt(insertAre.txt_cupo.getText()) < 0 || insertAre.txt_foto.getText().isEmpty() || insertAre.txt_correo.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            //EXTRAER DATOS
            String cedula = insertAre.txt_cedula.getText();
            String nombre = insertAre.txt_nombre.getText();
            String apellido = insertAre.txt_apellido.getText();
            String transformar_fecha_nacimiento = ((JTextField) insertAre.txt_fecha_nacimiento.getDateEditor().getUiComponent()).getText();
            Date fecha_nacimiento = Date.valueOf(transformar_fecha_nacimiento);
            String telefono = insertAre.txt_telefono.getText();
            String sexo = insertAre.txt_sexo.getSelectedItem().toString();
            double sueldo = Double.valueOf(insertAre.txt_sueldo.getText());
            int cupo = Integer.parseInt(insertAre.txt_cupo.getText());
            String foto = insertAre.txt_foto.getText();
            String correo = insertAre.txt_correo.getText();

            //ENVIAR DATOS
            gg.setIdPersona(cedula);
            gg.setNombresPersona(nombre);
            gg.setApellidosPersona(apellido);
            gg.setFechaNacimiento(fecha_nacimiento);
            gg.setTelefonoPersona(telefono);
            gg.setSexoPersona(sexo);
            gg.setSueldoPersona(sueldo);
            gg.setCupoPersona(cupo);
            gg.setFotoPersona(foto);
            gg.setCorreoPersona(correo);

            try {

                sql.insertar(gg);
                JOptionPane.showMessageDialog(null, "Datos guardados");
                limpiarTabla();
                limpiar();

            } catch (Exception e) {
                //System.out.println(e.toString());
                JOptionPane.showMessageDialog(null, "No se pudo guardar");

            }
        }
    }
//FIN METODO AGREGAR

//METODO ACTUALIZAR
    public void actualizar() {

        if (insertAre.txt_cedula.getText().isEmpty() || insertAre.txt_nombre.getText().isEmpty() || insertAre.txt_apellido.getText().isEmpty() || insertAre.txt_fecha_nacimiento == null || insertAre.txt_telefono.getText().isEmpty() || insertAre.txt_sexo.getSelectedItem().equals("SELECCIONAR") || Double.valueOf(insertAre.txt_sueldo.getText()) <= 0 || Integer.parseInt(insertAre.txt_cupo.getText()) < 0 || insertAre.txt_foto.getText().isEmpty() || insertAre.txt_correo.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            //EXTRAER DATOS
            String cedula = insertAre.txt_cedula.getText();
            String nombre = insertAre.txt_nombre.getText();
            String apellido = insertAre.txt_apellido.getText();
            String transformar_fecha_nacimiento = ((JTextField) insertAre.txt_fecha_nacimiento.getDateEditor().getUiComponent()).getText();
            Date fecha_nacimiento = Date.valueOf(transformar_fecha_nacimiento);
            String telefono = insertAre.txt_telefono.getText();
            String sexo = insertAre.txt_sexo.getSelectedItem().toString();
            double sueldo = Double.valueOf(insertAre.txt_sueldo.getText());
            int cupo = Integer.parseInt(insertAre.txt_cupo.getText());
            String foto = insertAre.txt_foto.getText();
            String correo = insertAre.txt_correo.getText();

            //ENVIAR DATOS
            gg.setIdPersona(cedula);
            gg.setNombresPersona(nombre);
            gg.setApellidosPersona(apellido);
            gg.setFechaNacimiento(fecha_nacimiento);
            gg.setTelefonoPersona(telefono);
            gg.setSexoPersona(sexo);
            gg.setSueldoPersona(sueldo);
            gg.setCupoPersona(cupo);
            gg.setFotoPersona(foto);
            gg.setCorreoPersona(correo);

            try {

                sql.actualizar(gg);

                JOptionPane.showMessageDialog(null, "Datos Actualizados");

                limpiarTabla();

                limpiar();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "No se pudo guardar");

            }

        }
    }

    //CENTRADOR DE CELDAS
    public void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < insertAre.tabla_personas.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    //LIMPIADOR DE TABLAS
    public void limpiarTabla() {
        for (int i = 0; i < insertAre.tabla_personas.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }

    }

    //CALCULAR EDAD
    public void fecha() {

        try {

            Calendar fechaseleccionada = insertAre.txt_fecha_nacimiento.getCalendar();
            int dia = fechaseleccionada.get(Calendar.DAY_OF_WEEK);
            int mes = fechaseleccionada.get(Calendar.MONTH);
            int año = fechaseleccionada.get(Calendar.YEAR);

            SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

            LocalDate fechaDeNacimiento = LocalDate.of(año, mes, dia);
            LocalDate fechaActual = LocalDate.now();
            Period edad = Period.between(fechaDeNacimiento, fechaActual);

            int edadstr = edad.getYears();

            insertAre.txt_edad.setText(String.valueOf(edadstr));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Escoja una Fecha");

        }

    }

    //FOTO
    private void seleccionarFoto() throws IOException {

        File archivo;
        JFileChooser FlcAbrirArch = new JFileChooser();
        FlcAbrirArch.setFileFilter(new FileNameExtensionFilter("archivo de imagen", "jpg", "jpeg"));
        int respuesta = FlcAbrirArch.showOpenDialog(this.insertAre);

        if (respuesta == JFileChooser.APPROVE_OPTION) {

            archivo = FlcAbrirArch.getSelectedFile();
            insertAre.txt_foto.setText(archivo.getAbsolutePath());
            Image foto = ImageIO.read(new File(insertAre.txt_foto.getText()));
            foto = foto.getScaledInstance(262, 234, 1);
            insertAre.imagen.setIcon(new ImageIcon(foto));

        }
    }

    //SELECCIONAR
    public void seleccionarPersona() {
        insertAre.tabla_personas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                if (Mouse_evt.getClickCount() == 1) {

                    insertAre.txt_cedula.setEnabled(false);

                    insertAre.btnGuardar.setEnabled(false);

                    insertAre.txt_cedula.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 0).toString());

                    insertAre.txt_nombre.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 1).toString());

                    insertAre.txt_apellido.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 2).toString());

                    int fila = insertAre.tabla_personas.rowAtPoint(Mouse_evt.getPoint());
                    insertAre.txt_fecha_nacimiento.setDate(Date.valueOf(insertAre.tabla_personas.getValueAt(fila, 3).toString()));

                    insertAre.txt_telefono.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 4).toString());

                    insertAre.txt_sexo.setSelectedItem(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 5).toString());

                    insertAre.txt_sueldo.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 6).toString());

                    insertAre.txt_cupo.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 7).toString());

                    insertAre.txt_foto.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 8).toString());

                    insertAre.txt_correo.setText(insertAre.tabla_personas.getValueAt(insertAre.tabla_personas.getSelectedRow(), 9).toString());

                    Image foto;
                    try {
                        foto = ImageIO.read(new File(insertAre.txt_foto.getText()));
                        foto = foto.getScaledInstance(262, 234, 1);
                        insertAre.imagen.setIcon(new ImageIcon(foto));
                    } catch (IOException ex) {
                        Logger.getLogger(Controlador_Persona.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }

//LIMPIADOR DE TEXTO
    public void limpiar() {

        insertAre.txt_cedula.setEnabled(true);
        insertAre.btnGuardar.setEnabled(true);
        insertAre.txt_cedula.setText("");
        insertAre.txt_nombre.setText("");
        insertAre.txt_apellido.setText("");
        insertAre.txt_fecha_nacimiento.setDate(null);
        insertAre.txt_edad.setText("");
        insertAre.txt_telefono.setText("");
        insertAre.txt_sexo.setSelectedItem("SELECCIONAR");
        insertAre.txt_cupo.setText("");
        insertAre.txt_correo.setText("");
        insertAre.txt_foto.setText("");
        insertAre.txt_sueldo.setText("");
        insertAre.imagen.setIcon(null);

    }

}
