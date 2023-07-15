/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controlador;

import Mvc.Modelo.Clase_Detalle_Factura;
import Mvc.Modelo.Clase_Factura;
import Mvc.Modelo.Clase_Persona;
import Mvc.Modelo.Clase_Productos;
import Mvc.Modelo.SQL_Detalle_Factura;
import Mvc.Modelo.SQL_Factura;
import Mvc.Modelo.SQL_Persona;
import Mvc.Modelo.SQL_Producto;
import Mvc.Vista.Interfaz_Factura;
import Mvc.conexion.Conexion_Base_Datos;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author CyJ
 */
public class Controlador_Factura implements ActionListener {

    SQL_Factura sql = new SQL_Factura();
    Clase_Factura clase = new Clase_Factura();
    SQL_Persona sqlPersona = new SQL_Persona();
    Clase_Persona clasePersona = new Clase_Persona();
    Clase_Detalle_Factura claseDetalle = new Clase_Detalle_Factura();
    SQL_Detalle_Factura sqlDetalle = new SQL_Detalle_Factura();
    Clase_Productos claseProductos = new Clase_Productos();
    SQL_Producto sqlProductos = new SQL_Producto();
    Interfaz_Factura insertAre = new Interfaz_Factura();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloper = new DefaultTableModel();
    DefaultTableModel modeloProd = new DefaultTableModel();
    DefaultTableModel modelodeta = new DefaultTableModel();
    Conexion_Base_Datos miconector = new Conexion_Base_Datos();
    Connection cn = miconector.ConectarBase();

    //BOTONES
    public Controlador_Factura(Interfaz_Factura evento1) {
        this.insertAre = evento1;

        //BOTONES
        this.insertAre.btnGuardar.addActionListener(this);
        this.insertAre.btnEliminar.addActionListener(this);
        this.insertAre.btnCancelar.addActionListener(this);
        this.insertAre.brnAbrirGuardar.addActionListener(this);
        this.insertAre.brnAbrirModificar.addActionListener(this);
        this.insertAre.btn_Buscar.addActionListener(this);
        this.insertAre.btnLimpiar.addActionListener(this);
        this.insertAre.btn_salir.addActionListener(this);
        this.insertAre.btn_listo.addActionListener(this);
        this.insertAre.btn_cancelar_Eliminar.addActionListener(this);
        this.insertAre.boton_calcular_total.addActionListener(this);
        this.insertAre.btn_agregar_producto.addActionListener(this);
        this.insertAre.btn_salir_reporte.addActionListener(this);
        this.insertAre.btn_imprimir.addActionListener(this);

        seleccionarFactura();
        limpiarTabla();
        limpiarTablaPersonas();
        listar(insertAre.tabla_factura);
        insertAre.dialogoInterfaz.setText("ON");
        insertAre.panelColor.setBackground(Color.GREEN);
        insertAre.btnLimpiar.setVisible(false);
        insertAre.brnAbrirModificar.setEnabled(false);

    }

    //ACCIONES BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertAre.boton_calcular_total) {
            calcularTotalDetalle();
        }
        if (e.getSource() == insertAre.btn_imprimir) {
            generarreporte();
        }
        if (e.getSource() == insertAre.btn_salir_reporte) {
            salirReporte();
        }
        if (e.getSource() == insertAre.btn_listo) {
            insertAre.MiDialogo.dispose();
            insertAre.tabla_factura.setEnabled(true);
        }
        if (e.getSource() == insertAre.btn_agregar_producto) {
            agregarDetalle();
            listardetalles();
            buscardetalle();
        }
        if (e.getSource() == insertAre.btn_cancelar_Eliminar) {
            eliminarCancelar();
            limpiarTablaPersonas();
            limpiarTablaProducto();
        }
        if (e.getSource() == insertAre.btnGuardar) {
            agregar();
            insertAre.btnGuardar.setEnabled(false);
        }
        if (e.getSource() == insertAre.btn_salir) {
            salir();
        }
        if (e.getSource() == insertAre.btnCancelar) {
            limpiar();
        }
        if (e.getSource() == insertAre.btnLimpiar) {
            listar(insertAre.tabla_factura);
            insertAre.txtBuscarTodo.setText("");
            insertAre.btnLimpiar.setVisible(false);
        }
        if (e.getSource() == insertAre.btn_Buscar) {
            buscar();
            insertAre.btnLimpiar.setVisible(true);
        }
        if (e.getSource() == insertAre.brnAbrirGuardar) {
            id();
            limpiarTablaPersonas();
            limpiarTablaProducto();
            listarPersFact(insertAre.tabla_cliente);
            listarProducto(insertAre.tabla_productos);
            seleccionarPersona();
            seleccionarProducto();
            insertAre.btnGuardar.setEnabled(true);
            abrirdialog();
            insertAre.btn_listo.setEnabled(false);
            insertAre.btn_agregar_producto.setEnabled(false);
            limFac();

        }
        if (e.getSource() == insertAre.brnAbrirModificar) {

            abrirdialogReporte();

        }
        if (e.getSource() == insertAre.btnEliminar) {
            eliminar();
        }

    }

    //GENERAR ID
    public void id() {
        UUID id = UUID.randomUUID();
        insertAre.txt_id_factura.setText(String.valueOf(id));
        insertAre.txt_id_factura_detalle.setText(String.valueOf(id));
    }

    //LIMPIAR FACTURA
    public void limFac() {

        insertAre.txt_cedula_cliente.setText("");
        insertAre.txt_id_producto.setText("");
        insertAre.txt_precio.setText("");
        insertAre.txt_cantidad_detalle.setValue(0);
        insertAre.txt_total_detalle.setText("");
        insertAre.txt_total_factura.setText("0");

    }

    //METODO LISTAR
    public void listar(JTable tabla) {

        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Clase_Factura> listaArea = sql.seleccionar();
        Object[] objecto = new Object[4];

        for (int i = 0; i < listaArea.size(); i++) {

            objecto[0] = listaArea.get(i).getIdFactura();
            objecto[1] = listaArea.get(i).getFechaFactura();
            objecto[2] = listaArea.get(i).getTotal();
            objecto[3] = listaArea.get(i).getCedulaCliente();

            modelo.addRow(objecto);
        }

        insertAre.tabla_factura.setModel(modelo);

    }

    //METODO LISTAR
    public void listarPersFact(JTable tabla) {

        centrarCeldasPerson(tabla);
        modeloper = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloper);
        List<Clase_Persona> listaArea = sqlPersona.seleccionar();
        Object[] objecto = new Object[3];

        for (int i = 0; i < listaArea.size(); i++) {

            objecto[0] = listaArea.get(i).getIdPersona();
            objecto[1] = listaArea.get(i).getNombresPersona();
            objecto[2] = listaArea.get(i).getApellidosPersona();

            modeloper.addRow(objecto);
        }

        insertAre.tabla_cliente.setModel(modeloper);

    }

    //METODO AGREGAR
    public void agregar() {

        if (insertAre.txt_cedula_cliente.getText().isEmpty() || insertAre.txt_fecha_factura.getText().isEmpty() || insertAre.txt_total_factura.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            if (validaciones() == false) {

                JOptionPane.showMessageDialog(null, "Verifique los datos Ingresados");

            } else {

                //EXTRAER DATOS
                String id = insertAre.txt_id_factura.getText();
                String fechaS = insertAre.txt_fecha_factura.getText();
                Date fecha = Date.valueOf(fechaS);
                double total = Double.valueOf(insertAre.txt_total_factura.getText());
                String cliente = insertAre.txt_cedula_cliente.getText();

                //ENVIAR DATOS
                clase.setIdFactura(id);
                clase.setFechaFactura(fecha);
                clase.setTotal(total);
                clase.setCedulaCliente(cliente);

                try {

                    sql.insertar(clase);
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiarTabla();
                    listar(insertAre.tabla_factura);
                    insertAre.tabla_factura.setEnabled(false);
                    insertAre.btnCancelar.setVisible(false);
                    insertAre.btn_agregar_producto.setEnabled(true);

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "No se pudo guardar");

                }
            }
        }
    }
//FIN METODO AGREGAR

    //ELIMINAR 
    public void eliminar() {
        int fila = insertAre.tabla_factura.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una factura");
        } else {
            String i = insertAre.tabla_factura.getValueAt(fila, 0).toString();
            Clase_Factura factura = new Clase_Factura(i);
            sql.eliminar(factura);
            JOptionPane.showMessageDialog(insertAre, "Eliminado correctamente");
            limpiarTabla();
            listar(insertAre.tabla_factura);
            limpiar();

        }
    }

    //ELIMINAR_ CANCELAR 
    public void eliminarCancelar() {
        if (insertAre.txt_id_factura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ERROR");
        } else {
            String i = insertAre.txt_id_factura.getText();
            Clase_Factura factura = new Clase_Factura(i);
            sql.eliminar(factura);
            JOptionPane.showMessageDialog(insertAre, "Eliminado correctamente");
            limpiarTabla();
            listar(insertAre.tabla_factura);
            limpiar();
            insertAre.tabla_factura.setEnabled(true);
            insertAre.btnCancelar.setVisible(true);
        }
    }

//METODO ACTUALIZAR
    public void actualizar() {

        if (insertAre.txt_cedula_cliente.getText().isEmpty() || insertAre.txt_fecha_factura.getText().isEmpty() || insertAre.txt_total_factura.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            //EXTRAER DATOS
            String id = insertAre.txt_id_factura.getText();
            String fechaS = insertAre.txt_fecha_factura.getText();
            Date fecha = Date.valueOf(fechaS);
            double total = Double.valueOf(insertAre.txt_total_factura.getText());
            String cliente = insertAre.txt_cedula_cliente.getText();

            //ENVIAR DATOS
            clase.setIdFactura(id);
            clase.setFechaFactura(fecha);
            clase.setTotal(total);
            clase.setCedulaCliente(cliente);

            try {

                sql.actualizar(clase);

                JOptionPane.showMessageDialog(null, "Datos Actualizados");

                limpiarTabla();

                limpiarTablaPersonas();

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
        for (int i = 0; i < insertAre.tabla_factura.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    //CENTRADOR DE CELDAS
    public void centrarCeldasPerson(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < insertAre.tabla_cliente.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    //LIMPIADOR DE TABLAS
    public void limpiarTabla() {
        for (int i = 0; i < insertAre.tabla_factura.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }

    }

    public void limpiarTablaPersonas() {
        for (int i = 0; i < insertAre.tabla_cliente.getRowCount(); i++) {
            modeloper.removeRow(i);
            i = i - 1;
        }

    }

    //SELECCIONAR
    public void seleccionarFactura() {

        insertAre.tabla_factura.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {

                if (Mouse_evt.getClickCount() == 1) {

                    insertAre.brnAbrirModificar.setEnabled(true);
                    try {
                        insertAre.txt_id_factura_reporte.setText(insertAre.tabla_factura.getValueAt(insertAre.tabla_factura.getSelectedRow(), 0).toString());

                        insertAre.txt_fecha_factura_reporte.setText(insertAre.tabla_factura.getValueAt(insertAre.tabla_factura.getSelectedRow(), 1).toString());

                        insertAre.txt_total_factura_reporte.setText(insertAre.tabla_factura.getValueAt(insertAre.tabla_factura.getSelectedRow(), 2).toString());

                        insertAre.txt_cedula_cliente_reporte.setText(insertAre.tabla_factura.getValueAt(insertAre.tabla_factura.getSelectedRow(), 3).toString());
                    } catch (Exception e) {
                    }
                }
            }
        });

    }

    //SELECCIONAR
    public void seleccionarPersona() {
        insertAre.tabla_cliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                if (Mouse_evt.getClickCount() == 1) {

                    insertAre.txt_cedula_cliente.setText(insertAre.tabla_cliente.getValueAt(insertAre.tabla_cliente.getSelectedRow(), 0).toString());

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
        insertAre.txt_fecha_factura.setText(LocalDate.now().toString());

        insertAre.MiDialogo.setSize(760, 655);
    }

    //DIALOG
    public void abrirdialogReporte() {

        insertAre.Reporte.setVisible(true);
        insertAre.Reporte.setTitle("REPORTE");

        insertAre.Reporte.setSize(538, 520);

        buscardetalleReporte();

    }

    public void salirReporte() {
        insertAre.Reporte.dispose();
        insertAre.brnAbrirModificar.setEnabled(false);
    }

    //VALIDACION CAMPOS DE TEXTO
    public boolean validaciones() {
        boolean x = true;
        return x;
    }

    public List<Clase_Factura> listarProducto() {
        List<Clase_Factura> listaFactura = new ArrayList<Clase_Factura>();

        try {
            String sql = "select * from clase.producto";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Clase_Factura factura = new Clase_Factura();
                factura.setIdFactura(rs.getString("idfactura"));
                factura.setFechaFactura(rs.getDate("fechafactura"));
                factura.setTotal(rs.getDouble("totalfactura"));
                factura.setCedulaCliente(rs.getString("clientefactura"));

                listaFactura.add(factura);

                //CIERRO CONEXION
            }
            return listaFactura;
        } catch (SQLException ex) {
            System.out.println("error");
        }
        return null;
    }

    public void buscar() {
        List<Clase_Factura> listprod = listarProducto();
        String idBuscado = insertAre.getTxtBuscarTodo().getText();

        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("idfactura");
        modeloTabla.addColumn("fechafactura");
        modeloTabla.addColumn("totalfactura");
        modeloTabla.addColumn("clientefactura");

        for (Clase_Factura p : listprod) {
            if (p.getIdFactura() == idBuscado) {
                Object[] fila = {
                    p.getIdFactura(),
                    p.getFechaFactura(),
                    p.getTotal(),
                    p.getCedulaCliente()};
                modeloTabla.addRow(fila);
            }
        }

        insertAre.getTabla_factura().setModel(modeloTabla);
    }

    public void salir() {
        insertAre.dispose();
    }
//LIMPIADOR DE TEXTO

    public void limpiar() {
        insertAre.btnGuardar.setEnabled(true);
        insertAre.brnAbrirModificar.setEnabled(false);
        insertAre.txt_id_factura.setText("");
        insertAre.txt_cedula_cliente.setText("");
        insertAre.txt_fecha_factura.setText("");
        insertAre.txt_total_factura.setText("");
        insertAre.MiDialogo.dispose();
    }

    /////////////////////////////////////DETALLE//////////////////////////////
    //METODO AGREGAR
    public void agregarDetalle() {
        int cantidadverf = (int) insertAre.txt_cantidad_detalle.getValue();
        if (insertAre.txt_id_producto.getText().isEmpty() || insertAre.txt_precio.getText().isEmpty() || cantidadverf <= 0 || insertAre.txt_total_detalle.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {
            //EXTRAER DATOS
            String id = insertAre.txt_id_factura_detalle.getText();
            int idProducto = Integer.valueOf(insertAre.txt_id_producto.getText());
            int cantidad = (int) insertAre.txt_cantidad_detalle.getValue();
            double precio = Double.valueOf(insertAre.txt_precio.getText());
            double total = Double.valueOf(insertAre.txt_total_detalle.getText());

            //ENVIAR DATOS
            claseDetalle.setIdFactura(id);
            claseDetalle.setIdProducto(idProducto);
            claseDetalle.setCantidadDetalle(cantidad);
            claseDetalle.setPrecioDetalle(precio);
            claseDetalle.setTotalDetalle(total);

            totalFactura();
            insertAre.btn_listo.setEnabled(true);

            try {

                sqlDetalle.insertar(claseDetalle);
                JOptionPane.showMessageDialog(null, "Datos guardados");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "No se pudo guardar");

            }
        }
    }
//FIN METODO AGREGAR

    //METODO LISTAR
    public void listarProducto(JTable tabla) {

        centrarCeldasProducto(tabla);
        modeloProd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProd);
        List<Clase_Productos> listaArea = sqlProductos.seleccionar();
        Object[] objecto = new Object[10];

        for (int i = 0; i < listaArea.size(); i++) {

            objecto[0] = listaArea.get(i).getIdProducto();
            objecto[1] = listaArea.get(i).getNombreProducto();
            objecto[2] = listaArea.get(i).getPrecioProducto();
            objecto[3] = listaArea.get(i).getCantidadProducto();

            modeloProd.addRow(objecto);
        }

        insertAre.tabla_productos.setModel(modeloProd);

    }
    //CENTRADOR DE CELDAS

    public void centrarCeldasProducto(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < insertAre.tabla_productos.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    //LIMPIADOR DE TABLAS
    public void limpiarTablaProducto() {
        for (int i = 0; i < insertAre.tabla_productos.getRowCount(); i++) {
            modeloProd.removeRow(i);
            i = i - 1;
        }
    }

    //SELECCIONAR
    public void seleccionarProducto() {
        insertAre.tabla_productos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                if (Mouse_evt.getClickCount() == 1) {

                    insertAre.txt_id_producto.setText(insertAre.tabla_productos.getValueAt(insertAre.tabla_productos.getSelectedRow(), 0).toString());
                    insertAre.txt_precio.setText(insertAre.tabla_productos.getValueAt(insertAre.tabla_productos.getSelectedRow(), 2).toString());

                }
            }
        });
    }

    public void calcularTotalDetalle() {

        double precio = Double.valueOf(insertAre.txt_precio.getText());
        int cantidad = (int) insertAre.txt_cantidad_detalle.getValue();
        int fila = insertAre.tabla_productos.getSelectedRow();
        int cantidadexis = Integer.valueOf(insertAre.tabla_productos.getValueAt(fila, 3).toString());

        if (insertAre.txt_precio.getText().isEmpty() || cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");
        } else {
            if (cantidad > cantidadexis || cantidadexis <= 0) {

                JOptionPane.showMessageDialog(null, "Cantidad de producto insuficiente");

            } else {
                double totalDetalle = precio * cantidad;
                insertAre.txt_total_detalle.setText(String.valueOf(totalDetalle));
            }

        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Clase_Detalle_Factura> listardetalles() {
        List<Clase_Detalle_Factura> listardetalles = new ArrayList<Clase_Detalle_Factura>();

        try {
            String sql = "select * from clase.detallefactura";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Clase_Detalle_Factura detalle = new Clase_Detalle_Factura();

                detalle.setIdFactura(rs.getString("idfactura"));

                detalle.setIdProducto(rs.getInt("idproducto"));
                detalle.setCantidadDetalle(rs.getInt("cantidaddetalle"));
                detalle.setPrecioDetalle(rs.getDouble("preciodetalle"));
                detalle.setTotalDetalle(rs.getDouble("totaldetalle"));

                listardetalles.add(detalle);

                //CIERRO CONEXION
            }
            return listardetalles;
        } catch (SQLException ex) {
            System.out.println("error");
        }
        return null;
    }

    public void buscardetalle() {
        List<Clase_Detalle_Factura> listdetall = listardetalles();
        String idBuscado = insertAre.txt_id_factura.getText();

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("CANTIDAD");
        modeloTabla.addColumn("PRECIO");
        modeloTabla.addColumn("TOTAL");

        for (Clase_Detalle_Factura p : listdetall) {
            if (p.getIdFactura().equals(idBuscado)) {
                Object[] fila = {
                    p.getIdProducto(),
                    p.getCantidadDetalle(),
                    p.getPrecioDetalle(),
                    p.getTotalDetalle(),};
                modeloTabla.addRow(fila);
            }
        }

        insertAre.tabla_detlle.setModel(modeloTabla);
    }

    public void buscardetalleReporte() {
        List<Clase_Detalle_Factura> listdetall = listardetalles();
        String idBuscado = insertAre.txt_id_factura_reporte.getText();

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("CANTIDAD");
        modeloTabla.addColumn("PRECIO");
        modeloTabla.addColumn("TOTAL");

        for (Clase_Detalle_Factura p : listdetall) {
            if (p.getIdFactura().equals(idBuscado)) {
                Object[] fila = {
                    p.getIdProducto(),
                    p.getCantidadDetalle(),
                    p.getPrecioDetalle(),
                    p.getTotalDetalle(),};
                modeloTabla.addRow(fila);
            }
        }

        insertAre.tabla_reporte.setModel(modeloTabla);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void totalFactura() {
        String idFac = insertAre.txt_id_factura.getText();
        double factot = Double.valueOf(insertAre.txt_total_factura.getText());
        double detallTota = Double.valueOf(insertAre.txt_total_detalle.getText());
        double total = detallTota + factot;
        insertAre.txt_total_factura.setText(String.valueOf(total));

        clase.setIdFactura(idFac);
        clase.setTotal(total);

        try {

            sql.actualizarTotal(clase);

            limpiarTabla();

            listar(insertAre.tabla_factura);

            stock();

            limpiarTablaProducto();

            listarProducto(insertAre.tabla_productos);

        } catch (Exception e) {

        }

    }

    public void stock() {

        int idprod = Integer.valueOf(insertAre.txt_id_producto.getText());

        int cantidadcompra = (int) insertAre.txt_cantidad_detalle.getValue();

        int fila = insertAre.tabla_productos.getSelectedRow();
        int cantidadBase = Integer.valueOf(insertAre.tabla_productos.getValueAt(fila, 3).toString());

        int result = cantidadBase - cantidadcompra;

        claseProductos.setIdProducto(idprod);
        claseProductos.setCantidadProducto(result);

        try {

            sqlProductos.actualizarSotck(claseProductos);

        } catch (Exception e) {

        }
    }

    private void generarreporte() {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/factura.jasper"));
            Map<String, Object> params = new HashMap<String, Object>();

            try {
                String Max = JOptionPane.showInputDialog("Ingresa el maximo");
                Double p = Double.valueOf(Max);
                params.put("Factura1", p);
                String Min = JOptionPane.showInputDialog("Ingresa el minimo");
                Double m = Double.valueOf(Min);
                params.put("Factura2", m);
                String titulo = JOptionPane.showInputDialog("Ingresa el título");
                params.put("FacturaTitulo", titulo);
                String dato = JOptionPane.showInputDialog("Ingresa la cedula del cliente");
                params.put("FacturaParametro", dato);

                JasperPrint jp = JasperFillManager.fillReport(jr, params, miconector.getCon());
                JasperViewer pv = new JasperViewer(jp, false);
                pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                pv.setVisible(true);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "PARAMETRO MAL INGRESADO");
            }
        } catch (JRException ex) {
            Logger.getLogger(Controlador_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
