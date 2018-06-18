/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.ProductoDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    
    public JLabel lblCodigo,lblTipo,lblStock,lblExistencia, lblNombre, lblprecio, lblcantidad;
    public JTextField codigo, descripcion, stock, nombre, precio, cantidad;
    public JComboBox Tipo;
    
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar,eliminar,insertar,limpiar,actualizar;
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblTipo);
        container.add(lblStock);
        container.add(lblExistencia);
        container.add(codigo);
        container.add(Tipo);
        container.add(stock);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        container.add(nombre);
        container.add(lblNombre);
        container.add(precio);
        container.add(lblprecio);
        setSize(600,600);
        eventos();
    }
    
    public final void agregarLabels(){
        lblNombre = new JLabel("Nombre");
        lblCodigo = new JLabel("Codigo");
        lblTipo = new JLabel("Tipo");
        lblprecio = new JLabel("Precio");
        lblStock = new JLabel("Cantidad");
        lblExistencia = new JLabel("Disponibilidad");
        lblCodigo.setBounds(10,60,ANCHOC,ALTOC);
        lblTipo.setBounds(300,60,ANCHOC,ALTOC);
        lblStock.setBounds(10,140,ANCHOC,ALTOC);
        lblExistencia.setBounds(10,190,ANCHOC,ALTOC);
        lblNombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblprecio.setBounds(10,100,ANCHOC, ALTOC);
        
    }
    
    public final void formulario(){
        nombre = new JTextField();
        codigo = new JTextField();
        Tipo = new JComboBox();
        precio = new JTextField();
        stock = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar =  new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        Tipo.addItem("FRUTA");
        Tipo.addItem("BEBIDA");
        Tipo.addItem("DULCE");
        Tipo.addItem("VERDURA");
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        nombre.setBounds(140,10, ANCHOC, ALTOC);
        codigo.setBounds(140,50,ANCHOC,ALTOC);
        Tipo.setBounds(350,60,ANCHOC,ALTOC);
        precio.setBounds(140, 100, ANCHOC, ALTOC);
        stock.setBounds(140,140,ANCHOC,ALTOC);
        si.setBounds(100,190,50,ALTOC);
        no.setBounds(150,190,50,ALTOC);
        
        buscar.setBounds(300,10,ANCHOC,ALTOC);
        insertar.setBounds(10,310,ANCHOC,ALTOC);
        actualizar.setBounds(150,310,ANCHOC,ALTOC);
        eliminar.setBounds(300,310,ANCHOC,ALTOC);
        limpiar.setBounds(450,310,ANCHOC,ALTOC);
        resultados = new JTable();
        table.setBounds(10,350,500,200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla(){
        
        tm = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Tipo");
        tm.addColumn("Cantidad");
        tm.addColumn("Dispoonibilidad");
        
        
        ProductoDao fd = new ProductoDao();
        ArrayList <Producto> productos = fd.readAll();
        
        for (Producto fi: productos){
            tm.addRow(new Object[]{ fi.getCodigo(),fi.getMarca(),fi.getStock(),fi.getExistencia()});
        }
        
        resultados.setModel(tm);
    }
    
    public void eventos(){
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = new Producto(codigo.getText(),Tipo.getSelectedItem().toString(), Integer.parseInt(stock.getText()),true);
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Producto registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de insertar el producto");
                    
                }
            }        
        });
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = new Producto(codigo.getText(),Tipo.getSelectedItem().toString(),
                        Integer.parseInt(stock.getText()),true);
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"SE HAN ACTUALIZADO LOS PRODUCTOS!!");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ocurrio UN PROBLEMA AL ACTUALIZAR");
                    
                }
            } 
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                if(fd.delete(codigo.getText())){
                    JOptionPane.showMessageDialog(null,"Producto borrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null,"OCURRIO UN PROBLEMA AL ELIMIAR...");
                    
                }
            } 
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = fd.read(codigo.getText());
                if(f == null){
                    JOptionPane.showMessageDialog(null,"ENCONTRADO!! ");
                }
                else{
                    codigo.setText(f.getCodigo());
                    Tipo.setSelectedItem(f.getMarca());
                    stock.setText(Integer.toString(f.getStock()));
                    if(f.getExistencia()){
                        si.setSelected(true);
                    }
                    else{
                        no.setSelected(true);
                    }
                }
            } 
        });
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            } 
        });
    }
    
    public void limpiarCampos(){
        codigo.setText("");
        Tipo.setSelectedItem("FRUTAS");
        stock.setText("");
    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
            
        });
    }
}
