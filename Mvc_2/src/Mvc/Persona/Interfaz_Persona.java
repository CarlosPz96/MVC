/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.Persona;

import Mvc.conexion.Conexion_Base_Datos;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlo
 */
public class Interfaz_Persona extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz_Persona
     */
    public Interfaz_Persona() {

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelConsulta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_personas = new javax.swing.JTable();
        txtBuscarTodo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        PanelCrear = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_foto = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_cupo = new javax.swing.JTextField();
        txt_sueldo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_sexo = new javax.swing.JComboBox<>();
        txt_telefono = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_fecha_nacimiento = new com.toedter.calendar.JDateChooser();
        txt_edad = new javax.swing.JTextField();
        btnFoto = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelConsulta.setBackground(new java.awt.Color(102, 102, 0));
        PanelConsulta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_personas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabla_personas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRES", "APELLIDOS", "FECHA NACIMIENTO", "TELEFONO", "SEXO", "SUELDO", "CUPO", "FOTO", "CORREO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_personas);

        PanelConsulta.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 820, 150));

        txtBuscarTodo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtBuscarTodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarTodoKeyPressed(evt);
            }
        });
        PanelConsulta.add(txtBuscarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 139, -1));

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        PanelConsulta.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 100, -1));

        btnModificar.setBackground(new java.awt.Color(0, 102, 102));
        btnModificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        PanelConsulta.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 100, -1));

        btnEliminar.setBackground(new java.awt.Color(0, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        PanelConsulta.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 90, -1));

        btnCancelar.setBackground(new java.awt.Color(0, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        PanelConsulta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 100, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscador:");
        PanelConsulta.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        getContentPane().add(PanelConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 241));

        PanelCrear.setBackground(new java.awt.Color(0, 102, 102));
        PanelCrear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CEDULA:");
        PanelCrear.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("NOMBRE:");
        PanelCrear.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 160, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("APELLIDO:");
        PanelCrear.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 90, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("FECHA NACIMIENTO:");
        PanelCrear.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 160, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("EDAD:");
        PanelCrear.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 120, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FOTOS:");
        PanelCrear.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 70, -1));

        txt_foto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelCrear.add(txt_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 180, -1));

        txt_correo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelCrear.add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 180, -1));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("CORREO:");
        PanelCrear.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 70, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CUPO:");
        PanelCrear.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 70, -1));

        txt_cupo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelCrear.add(txt_cupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 180, -1));

        txt_sueldo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelCrear.add(txt_sueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 180, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("SUELDO:");
        PanelCrear.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 70, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("SEXO:");
        PanelCrear.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 60, -1));

        txt_sexo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "MASCULINO", "FEMENINO", "OTRO" }));
        PanelCrear.add(txt_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 180, -1));
        PanelCrear.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 180, 26));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("TELEFONO:");
        PanelCrear.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 90, -1));

        txt_cedula.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelCrear.add(txt_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 180, 26));

        txt_nombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });
        PanelCrear.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 180, 26));

        txt_apellido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PanelCrear.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 180, 26));

        txt_fecha_nacimiento.setDateFormatString("yyyy-MM-dd");
        PanelCrear.add(txt_fecha_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 150, -1));

        txt_edad.setEditable(false);
        txt_edad.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_edad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_edadMouseClicked(evt);
            }
        });
        PanelCrear.add(txt_edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 40, 26));

        btnFoto.setBackground(new java.awt.Color(102, 102, 0));
        btnFoto.setText("BUSCAR");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        PanelCrear.add(btnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, -1));

        imagen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelCrear.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 160, 170));

        btnCalcular.setBackground(new java.awt.Color(102, 102, 0));
        btnCalcular.setText("CALCULAR");
        PanelCrear.add(btnCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 100, -1));

        getContentPane().add(PanelCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 238, 850, 290));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarTodoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTodoKeyPressed

        /*String[] titulos = {"ID", "NOMBRES","APELLIDOS","FECHA NACIMIENTO","TELEFONO","SEXO","SUELDO","CUPO","FOTO","CORREO"};
        Object[] registros = new Object[50];

        String sql = "SELECT * FROM clase.persona WHERE idpersona LIKE '%" + txtBuscarTodo.getText() + "%' "
                + "OR nombres LIKE '%" + txtBuscarTodo.getText() + "%'"
                + "OR apellidos LIKE '%" + txtBuscarTodo.getText() + "%'";

        DefaultTableModel model = new DefaultTableModel(null, titulos);
        Conexion_Base_Datos miconector = new Conexion_Base_Datos();
        Connection conect = miconector.ConectarBase();

        try {
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idpersona");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                //registros[3] = rs.getString("fechanacimiento");
                registros[4] = rs.getString("telefono");
                registros[5] = rs.getString("sexo");
                registros[6] = rs.getDouble("sueldo");
                registros[7] = rs.getInt("cupo");
                registros[8] = rs.getString("foto");
                registros[9] = rs.getString("correo");

                model.addRow(registros);
            }
            tabla_personas.setModel(model);
            miconector.close(conect);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }*/
    }//GEN-LAST:event_txtBuscarTodoKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped

        /*char dato = evt.getKeyChar();
        boolean numeros = dato >= 48 && dato <= 57;
        boolean backspace = dato == 8;
        boolean espacio = dato == 32;
        boolean mayusculas = dato >= 65 && dato <= 90;
        boolean minusculas = dato >= 97 && dato <= 122;
        boolean punto = dato == 46;
        boolean guion = dato == 45;
        boolean tildesMinusculas = dato >= 160 && dato <= 163;
        boolean tildeE = dato == 130;
        boolean ñ = dato == 164;
        boolean Ñ = dato == 165;
        boolean ETILDE = dato == 144;
        boolean ATILDE = dato == 181;
        boolean ITILDE = dato == 214;
        boolean OTILDE = dato == 224;
        boolean UTILDE = dato == 233;

        if (!(mayusculas || backspace || ETILDE || ATILDE || ITILDE || OTILDE || UTILDE || Ñ || espacio || minusculas)) {

            evt.consume();
            JOptionPane.showMessageDialog(null, "Digito Incorrecto");
            System.out.println("Dato es:" + dato);

        }*/

    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_edadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_edadMouseClicked

    }//GEN-LAST:event_txt_edadMouseClicked

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed


    }//GEN-LAST:event_btnFotoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelConsulta;
    public javax.swing.JPanel PanelCrear;
    public javax.swing.JButton btnCalcular;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnFoto;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JLabel imagen;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tabla_personas;
    public javax.swing.JTextField txtBuscarTodo;
    public javax.swing.JTextField txt_apellido;
    public javax.swing.JTextField txt_cedula;
    public javax.swing.JTextField txt_correo;
    public javax.swing.JTextField txt_cupo;
    public javax.swing.JTextField txt_edad;
    public com.toedter.calendar.JDateChooser txt_fecha_nacimiento;
    public javax.swing.JTextField txt_foto;
    public javax.swing.JTextField txt_nombre;
    public javax.swing.JComboBox<String> txt_sexo;
    public javax.swing.JTextField txt_sueldo;
    public javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}