package Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conn.DbConnect;
public class Prevision {
    public Prevision() {
    }

    public int id;
    
    public Prevision(int id, String libelle, int montant) {
        this.id = id;
        Libelle = libelle;
        this.montant = montant;
    }

    public String Libelle;
    public int montant;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return Libelle;
    }
    public void setLibelle(String libelle) {
        Libelle = libelle;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void save() throws SQLException {
        String sql = "INSERT INTO Prevision (libelle,montant) VALUES (?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.Libelle);
            preparedStatement.setInt(2, this.montant);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Prevision read(int id) throws SQLException {
        String sql = "SELECT * FROM Prevision WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Prevision prevision = null;

        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                prevision = new Prevision();
                prevision.setId(resultSet.getInt("id"));
                prevision.setMontant(resultSet.getInt("montant"));

                prevision.setLibelle(resultSet.getString("libelle"));
                
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return prevision;
    }

    public static List<Prevision> readAll() throws SQLException {
        String sql = "SELECT * FROM Prevision";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Prevision> Previsions = new ArrayList<>();

        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Prevision prevision = new Prevision();
                prevision.setId(resultSet.getInt("id"));
                prevision.setMontant(resultSet.getInt("montant"));

                prevision.setLibelle(resultSet.getString("libelle"));
                Previsions.add(prevision);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return Previsions;
    }
    public static int getTotalDepensesForPrevision(int idPrevision) throws SQLException {
        String sql = "SELECT SUM(montantDepense) as total FROM Depense WHERE idPrevision = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int total = 0;
    
        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idPrevision);
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return total;
    }
    public static List<Etat> getEtats() throws SQLException {
        String sql = "SELECT p.id, p.libelle, p.montant, COALESCE(SUM(d.montantDepense), 0) as totalDepenses " +
                     "FROM Prevision p LEFT JOIN Depense d ON p.id = d.idPrevision " +
                     "GROUP BY p.id, p.libelle, p.montant";
        
        List<Etat> etats = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                Etat etat = new Etat(
                    resultSet.getInt("id"),
                    resultSet.getString("libelle"),
                    resultSet.getInt("montant"),
                    resultSet.getInt("totalDepenses")
                );
                etats.add(etat);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return etats;
    }

 
}
