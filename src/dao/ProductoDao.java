/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import com.sun.istack.internal.logging.Logger;
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


/**
 *
 * @author LN710Q
 */
public class ProductoDao implements Metodos <Producto>{

    private static final String SQL_INSERT = "INSERT INTO productos(nombre,codigo,tipo,cantidad,precio,disponibilidad) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET marca = ? ,stock = ?,existencia = ? WHERE codFiltro=?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE codigo=?";
    private static final String SQL_READ = "SELECT * FROM productos WHERE codigo=?";
    private static final String SQL_READALL = "SELECT * FROM productos";
    private static final Conexion con = Conexion.conectar();
    
    /**
     *
     *
     *
     */
    @Override
    public boolean create(Producto g) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCodigo());
            ps.setString(2, g.getMarca());
            ps.setInt(3, g.getStock());
            ps.setBoolean(4, true);
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,ex);
        } finally{
            con.cerrarConexion();
        }
        return false;
                
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,ex);
                    } finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Producto c) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getMarca());
            ps.setInt(2, c.getStock());
            ps.setBoolean(3, c.getExistencia());
            ps.setString(4, c.getCodigo());
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,ex);
        } finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Producto read(Object key) {
        Producto f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                f = new Producto(rs.getString(1),rs.getInt(2),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
            }
            rs.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,ex);
        } finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Producto> readAll() {
        ArrayList<Producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            
            rs = s.executeQuery(SQL_READALL);
            
            while(rs.next()){
                all.add(new Producto(rs.getString(1),rs.getInt(2),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6)));
            }
            rs.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
        }
        return all;
    }
    
}
