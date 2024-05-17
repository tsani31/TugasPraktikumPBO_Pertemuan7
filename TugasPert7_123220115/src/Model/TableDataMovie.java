package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableDataMovie extends AbstractTableModel {

    List<DataMovie> dataMovie;

    public TableDataMovie(List<DataMovie> dataMovie) {
        this.dataMovie = dataMovie;
    }

    @Override
    public int getRowCount() {
        return dataMovie.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Judul";
            case 1:
                return "Alur";
            case 2:
                return "Penokohan";
            case 3:
                return "Akting";
            case 4:
                return "Rating Keseluruhan";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return dataMovie.get(row).getJudul();
            case 1:
                return dataMovie.get(row).getAlur();
            case 2:
                return dataMovie.get(row).getPenokohan();
            case 3:
                return dataMovie.get(row).getAkting();
            case 4:
                return dataMovie.get(row).getRating();
            default:
                return null;
        }
    }

}
