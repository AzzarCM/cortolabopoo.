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

public class Consulta extends JFrame{
    
   public JLabel lblCodigo, lblMarca, lblStock, lblExistencia, lblPrecio, lblNombre;

    public JTextField nombre, codigo, precio, cantidad;
    public JComboBox tipo;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable RESULTS;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 20;

    DefaultTableModel tm;

    public Consulta() {
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(nombre);
        container.add(precio);
        container.add(lblNombre);
        container.add(lblPrecio);
        container.add(lblCodigo);
        container.add(lblMarca);
        container.add(lblStock);
        container.add(lblExistencia);
        container.add(codigo);
        container.add(tipo);
        container.add(cantidad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();

    }

    private void agregarLabels() {
        lblCodigo = new JLabel("Codigo");
        lblMarca = new JLabel("Tipo");
        lblStock = new JLabel("Cantidad");
        lblExistencia = new JLabel("Disponibilidad");
        lblNombre = new JLabel("Nombre");
        lblPrecio = new JLabel("Precio");
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblMarca.setBounds(10, 60, ANCHOC, ALTOC);
        lblStock.setBounds(10, 85, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 140, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 35, ANCHOC, ALTOC);
        lblPrecio.setBounds(10, 110, ANCHOC, ALTOC);
    }

    private void formulario() {
        codigo = new JTextField();
        nombre = new JTextField();
        tipo = new JComboBox();
        cantidad = new JTextField();
        precio = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        RESULTS = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        tipo.addItem("Fruta");
        tipo.addItem("Verdura");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        //-------------------------------------------
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 35, ANCHOC, ALTOC);
        tipo.setBounds(140, 60, ANCHOC, ALTOC);
        cantidad.setBounds(140, 85, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);
        precio.setBounds(140, 110, ANCHOC, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);

        RESULTS = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(RESULTS));

    }

    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Tipo");
        tm.addColumn("Cantidad");
        tm.addColumn("Precio");
        tm.addColumn("Disponible");

        ProductoDao fd = new ProductoDao();
        ArrayList<Producto> filtros = fd.readAll();

        for (Producto fi : filtros) {
            tm.addRow(new Object[]{fi.getNombre(), fi.getCodigo(), fi.getTipo(), fi.getCantidad(), (float) fi.getPrecio(), fi.isDisponibilidad()});
        }
        RESULTS.setModel(tm);

    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = new Producto(nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Float.valueOf(precio.getText()), true);
                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "SU PRODUCTO A SIDO REGISTRADO ..");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL MOMENTO DE INSERTAR....");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = new Producto(codigo.getText(),tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Float.valueOf(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL MOMENTO DE ACTUALIZAR....");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = new Producto(codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Float.valueOf(precio.getText()), true);
                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL MOMENTO DE ELIMINAR....");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "EL PRODUCTO NO ESTA EN LA BASE DE DATOS");
                } else {

                    codigo.setText(f.getCodigo());
                    tipo.setSelectedItem(f.getTipo());
                    cantidad.setText(Integer.toString(f.getCantidad()));
                    precio.setText(Float.toString(f.getPrecio()));

                    if (f.isDisponibilidad()) {
                        si.setSelected(true);
                    } else {
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

    public void limpiarCampos() {
        nombre.setText("");
        precio.setText("");
        codigo.setText("");
        tipo.setSelectedItem("Fruta");
        cantidad.setText("");

    }
    
}
