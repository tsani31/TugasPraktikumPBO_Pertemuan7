package Controller;

import DAOImplement.DAODataMovie;
import DAOImplement.DAOInterface;
import Model.DataMovie;
import Model.TableDataMovie;
import View.Main;
import java.util.List;
import javax.swing.JOptionPane;

public class DataMovieController {

    Main frame;
    DAOInterface movieDAO;
    List<DataMovie> dataMovie;

    public DataMovieController(Main frame) {
        this.frame = frame;
        movieDAO = new DAODataMovie();
        dataMovie = movieDAO.getAll();
    }

    public void fillTable() {
        dataMovie = movieDAO.getAll();
        TableDataMovie tdm = new TableDataMovie(dataMovie);
        frame.getTable().setModel(tdm);
    }

    public void insert() {
        try {
            DataMovie dm = new DataMovie();
            double alur = Double.parseDouble(frame.getInputAlur().getText());
            double penokohan = Double.parseDouble(frame.getInputPenokohan().getText());
            double akting = Double.parseDouble(frame.getInputAkting().getText());
            double rating = (alur + penokohan + akting) / 3;
            dm.setJudul(frame.getInputJudul().getText());
            dm.setAlur(alur);
            dm.setPenokohan(penokohan);
            dm.setAkting(akting);
            dm.setRating(rating);
            movieDAO.insert(dm);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harap masukan inputan berupa angka pada kolom Alur, Penokohan, dan Akting",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        try {
            DataMovie dm = new DataMovie();
            double alur = Double.parseDouble(frame.getInputAlur().getText());
            double penokohan = Double.parseDouble(frame.getInputPenokohan().getText());
            double akting = Double.parseDouble(frame.getInputAkting().getText());
            double rating = (alur + penokohan + akting) / 3;
            dm.setJudul(frame.getInputJudul().getText());
            dm.setAlur(alur);
            dm.setPenokohan(penokohan);
            dm.setAkting(akting);
            dm.setRating(rating);
            movieDAO.update(dm);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harap masukan inputan berupa angka pada kolom Alur, Penokohan, dan Akting",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        String judul = frame.getInputJudul().getText();
        movieDAO.delete(judul);
    }
}
