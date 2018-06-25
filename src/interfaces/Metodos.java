
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author 
 * @param <Generic>
 */
public interface Metodos <Generic> {
    public boolean create(Generic g);
    public boolean delete (Object key);
    public boolean update (Generic c);
    public Generic read(Object key);
    public ArrayList<Generic> readAll();
    
}
