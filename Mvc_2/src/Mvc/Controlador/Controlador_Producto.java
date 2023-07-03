/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Controlador;

import Mvc.Modelo.Clase_Productos;
import Mvc.Modelo.SQL_Producto;
import Mvc.Vista.Interfaz_Producto;
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
public class Controlador_Producto implements ActionListener {

    SQL_Producto sql = new SQL_Producto();
    Clase_Productos clase = new Clase_Productos();
    Interfaz_Producto insertAre = new Interfaz_Producto();
    DefaultTableModel modelo = new DefaultTableModel();
    Conexion_Base_Datos miconector = new Conexion_Base_Datos();
    Connection cn = miconector.ConectarBase();

    //BOTONES
    public Controlador_Producto(Interfaz_Producto evento1) {
        this.insertAre = evento1;

        //BOTONES
        this.insertAre.btnGuardar.addActionListener(this);
        this.insertAre.btnModificar.addActionListener(this);
        this.insertAre.btnEliminar.addActionListener(this);
        this.insertAre.btnCancelar.addActionListener(this);
        this.insertAre.btnFoto.addActionListener(this);
        this.insertAre.brnAbrirGuardar.addActionListener(this);
        this.insertAre.brnAbrirModificar.addActionListener(this);
        this.insertAre.btn_Buscar.addActionListener(this);
        this.insertAre.btnLimpiar.addActionListener(this);
        this.insertAre.btn_salir.addActionListener(this);
        this.insertAre.btn_imprimir.addActionListener(this);

        seleccionarProducto();
        limpiarTabla();
        listar(insertAre.tabla_producto);
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
            listar(insertAre.tabla_producto);
        }
         if (e.getSource() == insertAre.btn_imprimir) {
            generarreporte();
        }
        if (e.getSource() == insertAre.btn_salir) {
            salir();
        }
        if (e.getSource() == insertAre.btnCancelar) {
            limpiar();
        }
        if (e.getSource() == insertAre.btnLimpiar) {
            listar(insertAre.tabla_producto);
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
                Logger.getLogger(Controlador_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == insertAre.btnModificar) {
            actualizar();
            limpiarTabla();
            listar(insertAre.tabla_producto);
        }

        if (e.getSource() == insertAre.btnEliminar) {
            int fila = insertAre.tabla_producto.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
            } else {

                String i = insertAre.tabla_producto.getValueAt(fila, 0).toString();
                int x = Integer.valueOf(i);
                Clase_Productos producto = new Clase_Productos(x);

                sql.eliminar(producto);
                JOptionPane.showMessageDialog(insertAre, "Eliminado correctamente");
                limpiarTabla();
                listar(insertAre.tabla_producto);
                limpiar();

            }
        }

    }

    //METODO LISTAR
    public void listar(JTable tabla) {

        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Clase_Productos> listaArea = sql.seleccionar();
        Object[] objecto = new Object[6];

        for (int i = 0; i < listaArea.size(); i++) {

            objecto[0] = listaArea.get(i).getIdProducto();
            objecto[1] = listaArea.get(i).getNombreProducto();
            objecto[2] = listaArea.get(i).getPrecioProducto();
            objecto[3] = listaArea.get(i).getCantidadProducto();
            objecto[4] = listaArea.get(i).getFotoProducto();
            objecto[5] = listaArea.get(i).getDescripcionProducto();

            modelo.addRow(objecto);
        }

        insertAre.tabla_producto.setModel(modelo);

    }

    //METODO AGREGAR
    public void agregar() {

        if (insertAre.txt_nombre.getText().isEmpty() || insertAre.txt_precio.getText().isEmpty() || insertAre.txt_catidad.getText().isEmpty() || insertAre.txt_descripcion.getText().isEmpty() || insertAre.txt_foto.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {

            if (validaciones() == false) {

                JOptionPane.showMessageDialog(null, "Verifique los datos Ingresados");

            } else {

                if (ValidarID() == false) {

                    JOptionPane.showMessageDialog(null, "ID ya registrado");

                } else {

                    //EXTRAER DATOS
                    //int id = Integer.valueOf(insertAre.txt_id.getText());
                    String nombre = insertAre.txt_nombre.getText();
                    double precio = Double.valueOf(insertAre.txt_precio.getText());
                    int cantidad = Integer.valueOf(insertAre.txt_catidad.getText());
                    String foto = insertAre.txt_foto.getText();
                    String descripcion = insertAre.txt_descripcion.getText();

                    //ENVIAR DATOS
                    //clase.setIdProducto(id);
                    clase.setNombreProducto(nombre);
                    clase.setPrecioProducto(precio);
                    clase.setCantidadProducto(cantidad);
                    clase.setFotoProducto(foto);
                    clase.setDescripcionProducto(descripcion);

                    try {

                        sql.insertar(clase);
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
//FIN METODO AGREGAR

//METODO ACTUALIZAR
    public void actualizar() {

        if (insertAre.txt_id.getText().isEmpty() || insertAre.txt_nombre.getText().isEmpty() || insertAre.txt_precio.getText().isEmpty() || insertAre.txt_catidad.getText().isEmpty() || insertAre.txt_descripcion.getText().isEmpty() || insertAre.txt_foto.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe Completar la informacion");

        } else {
            if (validaciones() == false) {

                JOptionPane.showMessageDialog(null, "Verifique los datos Ingresados");

            } else {

                //EXTRAER DATOS
                int id = Integer.valueOf(insertAre.txt_id.getText());
                String nombre = insertAre.txt_nombre.getText();
                double precio = Double.valueOf(insertAre.txt_precio.getText());
                int cantidad = Integer.valueOf(insertAre.txt_catidad.getText());
                String foto = insertAre.txt_foto.getText();
                String descripcion = insertAre.txt_descripcion.getText();

                //ENVIAR DATOS
                clase.setIdProducto(id);
                clase.setNombreProducto(nombre);
                clase.setPrecioProducto(precio);
                clase.setCantidadProducto(cantidad);
                clase.setFotoProducto(foto);
                clase.setDescripcionProducto(descripcion);

                try {

                    sql.actualizar(clase);

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
        for (int i = 0; i < insertAre.tabla_producto.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    //LIMPIADOR DE TABLAS
    public void limpiarTabla() {
        for (int i = 0; i < insertAre.tabla_producto.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
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
    public void seleccionarProducto() {
        insertAre.tabla_producto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                if (Mouse_evt.getClickCount() == 1) {

                    insertAre.btnGuardar.setEnabled(false);

                    insertAre.brnAbrirModificar.setEnabled(true);

                    insertAre.txt_id.setText(insertAre.tabla_producto.getValueAt(insertAre.tabla_producto.getSelectedRow(), 0).toString());

                    insertAre.txt_nombre.setText(insertAre.tabla_producto.getValueAt(insertAre.tabla_producto.getSelectedRow(), 1).toString());

                    insertAre.txt_precio.setText(insertAre.tabla_producto.getValueAt(insertAre.tabla_producto.getSelectedRow(), 2).toString());

                    insertAre.txt_catidad.setText(insertAre.tabla_producto.getValueAt(insertAre.tabla_producto.getSelectedRow(), 3).toString());

                    insertAre.txt_foto.setText(insertAre.tabla_producto.getValueAt(insertAre.tabla_producto.getSelectedRow(), 4).toString());

                    insertAre.txt_descripcion.setText(insertAre.tabla_producto.getValueAt(insertAre.tabla_producto.getSelectedRow(), 5).toString());

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

        insertAre.MiDialogo.setSize(730, 365);
    }

    //DIALOG
    public void abrirdialogMod() {

        insertAre.MiDialogo.setVisible(true);
        insertAre.MiDialogo.setTitle("MODIFICAR");
        insertAre.btnModificar.setVisible(true);
        insertAre.btnGuardar.setVisible(false);
        insertAre.btnEliminar.setVisible(true);
        insertAre.MiDialogo.setSize(730, 365);

    }

    //VALIDACION CAMPOS DE TEXTO
    public boolean validaciones() {
        boolean x = false;

        if (insertAre.txt_nombre.getText().matches("[A-Z][a-z]*") && insertAre.txt_nombre.getText().trim().length() <= 50) {
            if (insertAre.txt_precio.getText().matches("\\d+(.\\d+)?")) {
                if (insertAre.txt_catidad.getText().matches("\\d+")) {
                    x = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad incorrecta");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Precio incorrecto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nombre incorrecto");
        }
        return x;
    }

    //VALIDAR ID
    public boolean ValidarID() {

        boolean validar = false;

        try {
            int codigo = 0;
            String consulta = "select count(*) from clase.persona where idpersona='" + insertAre.txt_id.getText() + "';";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                codigo = rs.getInt("count(*)");
            }
            if (codigo == 0) {
                validar = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return validar;
    }

    public List<Clase_Productos> listarProducto() {
        List<Clase_Productos> listaProducto = new ArrayList<Clase_Productos>();

        try {
            String sql = "select * from clase.producto";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Clase_Productos producto = new Clase_Productos();
                producto.setIdProducto(rs.getInt("idproducto"));
                producto.setNombreProducto(rs.getString("nombreproducto"));
                producto.setPrecioProducto(rs.getDouble("precioproducto"));
                producto.setCantidadProducto(rs.getInt("cantidadproducto"));
                producto.setFotoProducto(rs.getString("fotoproducto"));
                producto.setDescripcionProducto(rs.getString("descripcionproducto"));

                listaProducto.add(producto);

                //CIERRO CONEXION
            }
            return listaProducto;
        } catch (SQLException ex) {
            System.out.println("error");
        }
        return null;
    }

    public void buscar() {
        List<Clase_Productos> listprod = listarProducto();
        int idBuscado = Integer.valueOf(insertAre.getTxtBuscarTodo().getText());

        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("idproducto");
        modeloTabla.addColumn("nombreproducto");
        modeloTabla.addColumn("precioproducto");
        modeloTabla.addColumn("cantidadproducto");
        modeloTabla.addColumn("fotoproducto");
        modeloTabla.addColumn("descripcionproducto");

        for (Clase_Productos p : listprod) {
            if (p.getIdProducto() == idBuscado) {
                Object[] fila = {
                    p.getIdProducto(),
                    p.getNombreProducto(),
                    p.getPrecioProducto(),
                    p.getCantidadProducto(),
                    p.getFotoProducto(),
                    p.getDescripcionProducto(),};
                modeloTabla.addRow(fila);
            }
        }

        insertAre.getTabla_producto().setModel(modeloTabla);
    }

    public void salir() {
        insertAre.dispose();
    }
//LIMPIADOR DE TEXTO

    public void limpiar() {
        insertAre.btnGuardar.setEnabled(true);
        insertAre.brnAbrirModificar.setEnabled(false);
        insertAre.txt_id.setText("");
        insertAre.txt_nombre.setText("");
        insertAre.txt_precio.setText("");
        insertAre.txt_catidad.setText("");
        insertAre.txt_foto.setText("");
        insertAre.txt_descripcion.setText("");
        insertAre.imagen.setIcon(null);
        insertAre.MiDialogo.dispose();
    }

    private void generarreporte() {
        try {
            System.out.println("Imprimiendo");
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/producto.jasper"));
            Map<String, Object> params = new HashMap<String, Object>();
            JasperPrint jp = JasperFillManager.fillReport(jr, params, miconector.getCon());
            JasperViewer pv = new JasperViewer(jp, false);
            pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Controlador_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
