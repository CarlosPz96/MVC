/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controlador;

import Mvc.Modelo.*;
import Mvc.Vista.*;
import Mvc.conexion.*;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.util.ArrayList;
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
 * @author CARLOS
 */
public class Controlador_Persona implements ActionListener {

    SQL_Persona sql = new SQL_Persona();
    Clase_Persona clasePersona = new Clase_Persona();
    Interfaz_Persona insertAre = new Interfaz_Persona();
    DefaultTableModel modelo = new DefaultTableModel();
    Conexion_Base_Datos miconector = new Conexion_Base_Datos();
    Connection cn = miconector.ConectarBase();

    //BOTONES
    public Controlador_Persona(Interfaz_Persona evento1) {
        this.insertAre = evento1;

        //BOTONES
        this.insertAre.btnGuardar.addActionListener(this);
        this.insertAre.btnModificar.addActionListener(this);
        this.insertAre.btnEliminar.addActionListener(this);
        this.insertAre.btnCancelar.addActionListener(this);
        this.insertAre.btnCalcular.addActionListener(this);
        this.insertAre.btnFoto.addActionListener(this);
        this.insertAre.brnAbrirGuardar.addActionListener(this);
        this.insertAre.brnAbrirModificar.addActionListener(this);
        this.insertAre.btn_Buscar.addActionListener(this);
        this.insertAre.btnLimpiar.addActionListener(this);
        this.insertAre.btn_salir.addActionListener(this);

        seleccionarPersona();
        limpiarTabla();
        listar(insertAre.tabla_personas);
        insertAre.dialogoInterfaz.setText("ON");
        insertAre.panelColor.setBackground(Color.GREEN);
        insertAre.btnLimpiar.setVisible(false);
        insertAre.brnAbrirModificar.setEnabled(false);
    }

    //ACCIONES BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insertAre.btnGuardar) {
            agregar();
            limpiarTabla();
            listar(insertAre.tabla_personas);
        }
        if (e.getSource() == insertAre.btn_salir) {
            salir();
        }
        if (e.getSource() == insertAre.btnCancelar) {
            limpiar();
        }
        if (e.getSource() == insertAre.btnLimpiar) {
            listar(insertAre.tabla_personas);
            insertAre.txtBuscarTodo.setText("");
            insertAre.btnLimpiar.setVisible(false);
        }
        if (e.getSource() == insertAre.btn_Buscar) {
            buscar();
            insertAre.btnLimpiar.setVisible(true);
        }
        if (e.getSource() == insertAre.brnAbrirGuardar) {

            abrirdialog();

        }
        if (e.getSource() == insertAre.brnAbrirModificar) {

            abrirdialogMod();

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
                limpiar();

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

    //METODO LISTAR
    public void listarPersFact(JTable tabla) {

        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Clase_Persona> listaArea = sql.seleccionar();
        Object[] objecto = new Object[3];

        for (int i = 0; i < listaArea.size(); i++) {

            objecto[0] = listaArea.get(i).getIdPersona();
            objecto[1] = listaArea.get(i).getNombresPersona();
            objecto[2] = listaArea.get(i).getApellidosPersona();

            modelo.addRow(objecto);
        }

        insertAre.tabla_personas.setModel(modelo);

    }

    //METODO AGREGAR
    public void agregar() {

        if (insertAre.txt_cedula.getText().isEmpty() || insertAre.txt_nombre.getText().isEmpty() || insertAre.txt_apellido.getText().isEmpty() || insertAre.txt_fecha_nacimiento == null || insertAre.txt_telefono.getText().isEmpty() || insertAre.txt_sexo.getSelectedItem().equals("SELECCIONAR") || insertAre.txt_sueldo.getText().equals("") || insertAre.txt_cupo.getText().isEmpty() || insertAre.txt_foto.getText().isEmpty() || insertAre.txt_correo.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            if (validaciones() == false) {

                JOptionPane.showMessageDialog(null, "Verifique los datos Ingresados");

            } else {

                if (validarCedula() == false) {

                    JOptionPane.showMessageDialog(null, "Cedula incorrecta");

                } else {
                    
                    if (ValidarID() == false) {

                        JOptionPane.showMessageDialog(null, "Cedula ya registrada");

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
                        clasePersona.setIdPersona(cedula);
                        clasePersona.setNombresPersona(nombre);
                        clasePersona.setApellidosPersona(apellido);
                        clasePersona.setFechaNacimiento(fecha_nacimiento);
                        clasePersona.setTelefonoPersona(telefono);
                        clasePersona.setSexoPersona(sexo);
                        clasePersona.setSueldoPersona(sueldo);
                        clasePersona.setCupoPersona(cupo);
                        clasePersona.setFotoPersona(foto);
                        clasePersona.setCorreoPersona(correo);

                        try {

                            sql.insertar(clasePersona);
                            JOptionPane.showMessageDialog(null, "Datos guardados");
                            limpiarTabla();
                            limpiar();

                        } catch (Exception e) {
                            //System.out.println(e.toString());
                            JOptionPane.showMessageDialog(null, "No se pudo guardar");
                        }
                    }
                }
            }
        }
    }
//FIN METODO AGREGAR

//METODO ACTUALIZAR
    public void actualizar() {

        if (insertAre.txt_cedula.getText().isEmpty() || insertAre.txt_nombre.getText().isEmpty() || insertAre.txt_apellido.getText().isEmpty() || insertAre.txt_fecha_nacimiento == null || insertAre.txt_telefono.getText().isEmpty() || insertAre.txt_sexo.getSelectedItem().equals("SELECCIONAR") || Double.valueOf(insertAre.txt_sueldo.getText()) <= 0 || Integer.parseInt(insertAre.txt_cupo.getText()) < 0 || insertAre.txt_foto.getText().isEmpty() || insertAre.txt_correo.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            if (validaciones() == false) {

                JOptionPane.showMessageDialog(null, "Verifique los datos Ingresados");

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
                clasePersona.setIdPersona(cedula);
                clasePersona.setNombresPersona(nombre);
                clasePersona.setApellidosPersona(apellido);
                clasePersona.setFechaNacimiento(fecha_nacimiento);
                clasePersona.setTelefonoPersona(telefono);
                clasePersona.setSexoPersona(sexo);
                clasePersona.setSueldoPersona(sueldo);
                clasePersona.setCupoPersona(cupo);
                clasePersona.setFotoPersona(foto);
                clasePersona.setCorreoPersona(correo);

                try {

                    sql.actualizar(clasePersona);

                    JOptionPane.showMessageDialog(null, "Datos Actualizados");

                    limpiarTabla();

                    limpiar();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "No se pudo guardar");

                }
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

                    insertAre.brnAbrirModificar.setEnabled(true);

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
                        System.out.println("Error Imagen");
                    }
                }
            }
        });
    }

    //DIALOG
    public void abrirdialog() {

        insertAre.MiDialogo.setVisible(true);
        insertAre.MiDialogo.setTitle("GUARDAR");
        insertAre.btnGuardar.setVisible(true);
        insertAre.btnEliminar.setVisible(true);
        insertAre.btnModificar.setVisible(false);

        insertAre.MiDialogo.setSize(850, 360);
    }

    //DIALOG
    public void abrirdialogMod() {

        insertAre.MiDialogo.setVisible(true);
        insertAre.MiDialogo.setTitle("MODIFICAR");
        insertAre.btnModificar.setVisible(true);
        insertAre.btnGuardar.setVisible(false);
        insertAre.btnEliminar.setVisible(true);
        insertAre.MiDialogo.setSize(850, 360);

    }

    //VALIDACION CAMPOS DE TEXTO
    public boolean validaciones() {
        boolean x = false;

        if (insertAre.txt_cedula.getText().matches("\\d+") && insertAre.txt_cedula.getText().trim().length() == 10) {
            if (insertAre.txt_nombre.getText().matches("[A-Z][a-z]*") && insertAre.txt_nombre.getText().trim().length() <= 50) {
                if (insertAre.txt_apellido.getText().matches("[A-Z][a-z]*") && insertAre.txt_apellido.getText().trim().length() <= 50) {
                    if (insertAre.txt_telefono.getText().matches("\\d+") && insertAre.txt_telefono.getText().trim().length() == 10) {
                        if (insertAre.txt_correo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && insertAre.txt_correo.getText().trim().length() < 100) {
                            if (insertAre.txt_sueldo.getText().matches("\\d+(.\\d+)?")) {
                                if (insertAre.txt_cupo.getText().matches("\\d+") && insertAre.txt_cupo.getText().trim().length() < 11) {
                                    if (insertAre.txt_foto.getText().trim().length() < 150) {
                                        x = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Foto Incorrecta");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cupo Incorrecto");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Sueldo Incorrecto");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Correo Electronico Incorrecto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Telefono Incorrecto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Apellido Incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nombre Incorrecto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cedula Incorrecta");
        }
        return x;
    }

    public boolean validarCedula() {
        String cedula = insertAre.txt_cedula.getText();
        if (cedula.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        int lastDigit = Character.getNumericValue(cedula.charAt(9));
        int calculatedDigit = (10 - (sum % 10)) % 10;

        return lastDigit == calculatedDigit;
    }

    //VALIDAR ID
    public boolean ValidarID() {

        boolean validar = false;

        try {
            int codigo = 0;
            String consulta = "select count(*) from clase.persona where idpersona='" + insertAre.txt_cedula.getText() + "';";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                codigo = rs.getInt("count(*)");
            }
            if (codigo == 0) {
                validar = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }

        return validar;
    }

    public List<Clase_Persona> listarPersonas() {
        List<Clase_Persona> listaPersona = new ArrayList<Clase_Persona>();

        try {
            String sql = "select * from clase.persona";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Clase_Persona persona = new Clase_Persona();
                persona.setIdPersona(rs.getString("idpersona"));
                persona.setNombresPersona(rs.getString("nombres"));
                persona.setApellidosPersona(rs.getString("apellidos"));
                persona.setFechaNacimiento(rs.getDate("fechanacimiento"));
                persona.setTelefonoPersona(rs.getString("telefono"));
                persona.setSexoPersona(rs.getString("sexo"));
                persona.setSueldoPersona(rs.getDouble("sueldo"));
                persona.setCupoPersona(rs.getInt("cupo"));
                persona.setCorreoPersona(rs.getString("correo"));
                persona.setFotoPersona(rs.getString("foto"));

                listaPersona.add(persona);

                //CIERRO CONEXION
            }
            return listaPersona;
        } catch (SQLException ex) {
            System.out.println("error");
        }
        return null;
    }

    public void buscar() {
        List<Clase_Persona> listper = listarPersonas();
        String idBuscado = insertAre.getTxtBuscarTodo().getText();

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cedula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Sexo");
        modeloTabla.addColumn("Sueldo");
        modeloTabla.addColumn("Cupo");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("foto");

        for (Clase_Persona p : listper) {
            if (p.getIdPersona().equals(idBuscado)) {
                Object[] fila = {
                    p.getIdPersona(),
                    p.getNombresPersona(),
                    p.getApellidosPersona(),
                    p.getFechaNacimiento(),
                    p.getTelefonoPersona(),
                    p.getSexoPersona(),
                    p.getSueldoPersona(),
                    p.getCupoPersona(),
                    p.getCorreoPersona(),
                    p.getFotoPersona()

                };
                modeloTabla.addRow(fila);
            }
        }

        insertAre.getTabla_personas().setModel(modeloTabla);
    }

    public void salir() {
        insertAre.dispose();
    }

//LIMPIADOR DE TEXTO
    public void limpiar() {
        insertAre.txt_cedula.setEnabled(true);
        insertAre.btnGuardar.setEnabled(true);
        insertAre.brnAbrirModificar.setEnabled(false);
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
        insertAre.MiDialogo.dispose();
    }
}
