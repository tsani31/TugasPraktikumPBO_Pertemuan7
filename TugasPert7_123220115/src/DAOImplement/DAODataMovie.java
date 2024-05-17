package DAOImplement;

import Config.Connector;
import Model.DataMovie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DAODataMovie implements DAOInterface {

    Connection conn;
    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?,?,?,?,?)";
    final String update = "UPDATE movie SET alur=?, penokohan=?, akting=?, nilai=? WHERE judul=?";
    final String delete = "DELETE FROM movie WHERE judul=?";

    public DAODataMovie() {
        conn = Connector.getConnection();
    }

    @Override
    public void insert(DataMovie dm) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(insert);
            statement.setString(1, dm.getJudul());
            statement.setDouble(2, dm.getAlur());
            statement.setDouble(3, dm.getPenokohan());
            statement.setDouble(4, dm.getAkting());
            statement.setDouble(5, dm.getRating());
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } 
        catch (SQLIntegrityConstraintViolationException exc) {
            JOptionPane.showMessageDialog(null, "Film dengan judul tersebut sudah ada, Data gagal ditambahkan",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menambahkan data, coba lagi",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } 
        finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(DataMovie dm) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(update);
            statement.setDouble(1, dm.getAlur());
            statement.setDouble(2, dm.getPenokohan());
            statement.setDouble(3, dm.getAkting());
            statement.setDouble(4, dm.getRating());
            statement.setString(5, dm.getJudul());
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menambahkan data, coba lagi",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } 
        finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(delete);
            statement.setString(1, judul);
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menambahkan data, coba lagi",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } 
        finally {
            try {
                statement.close();
            } catch (SQLException ex) {

                Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<DataMovie> getAll() {
        List<DataMovie> dataMovie = null;
        try {
            dataMovie = new ArrayList<DataMovie>();
            PreparedStatement statement = conn.prepareStatement(select);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DataMovie movie = new DataMovie();
                movie.setJudul(result.getString("judul"));
                movie.setAlur(result.getDouble("alur"));
                movie.setPenokohan(result.getDouble("penokohan"));
                movie.setAkting(result.getDouble("akting"));
                movie.setRating(result.getDouble("nilai"));
                dataMovie.add(movie);
            }
            statement.close();
        } catch (SQLException ex) {

            Logger.getLogger(DAODataMovie.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return dataMovie;
    }
}
