
package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;



public class ProductoDao implements Metodos <Producto>{

   private static final String SQL_INSERT = "INSERT INTO productos (nombre,codigo, tipo, cantidad, precio, disponibilidad) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET tipo = ?, cantidad = ?, precio = ?, disponibilidad = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE codigo = ?";
    private static final String SQL_READ = "SELECT * FROM productos WHERE codigo = ?";
    private static final String SQL_READALL = "SELECT * FROM productos";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Producto g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getCodigo());
            ps.setString(3, g.getTipo());
            ps.setInt(4, g.getCantidad());
            ps.setFloat(5, g.getPrecio());
            ps.setBoolean(6, g.isDisponibilidad());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Producto c) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getTipo());
            ps.setInt(2, c.getCantidad());
            ps.setFloat(3, c.getPrecio());
            ps.setBoolean(4, c.isDisponibilidad());
            ps.setString(5, c.getCodigo());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Producto read(Object key) {
        Producto f = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                f = new Producto(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6) ,rs.getBoolean(7));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return f;

    }

    @Override
    public ArrayList<Producto> readAll() {
        ArrayList<Producto> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                all.add(new Producto(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6) ,rs.getBoolean(7)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}
