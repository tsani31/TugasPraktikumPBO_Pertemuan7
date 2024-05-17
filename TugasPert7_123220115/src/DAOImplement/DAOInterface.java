
package DAOImplement;

import Model.DataMovie;
import java.util.List;



public interface DAOInterface {
    public void insert(DataMovie dm);
    public void update(DataMovie dm);
    public void delete(String judul);
    public List<DataMovie> getAll();
}
