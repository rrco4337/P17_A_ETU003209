package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conn.DbConnect;

public class Depense {
    public int id;
    public int  idPrevision ;
    public int montantDepense;

    public Depense(int id, int idPrevision, int montantDepense) {
        this.id = id;
        this.idPrevision = idPrevision;
        this.montantDepense = montantDepense;
    }

    public Depense() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(int idPrevision) {
        this.idPrevision = idPrevision;
    }

    public int getMontantDepense() {
        return montantDepense;
    }

    public void setMontantDepense(int montantDepense) {
        this.montantDepense = montantDepense;
    }
    
    public void save() throws SQLException, Exception {
        
        Prevision prevision = Prevision.read(this.idPrevision);
        if (prevision == null) {
            throw new Exception("Prévision introuvable");
        }
    
        
        int totalDepenses = Prevision.getTotalDepensesForPrevision(this.idPrevision);
        int nouveauTotal = totalDepenses + this.montantDepense;
    
       
        if (nouveauTotal > prevision.getMontant()) {
            throw new Exception("Le montant total des dépenses (" + nouveauTotal + 
                              ") dépasse le montant prévisionnel (" + prevision.getMontant() + ")");
        }
    

        String sql = "INSERT INTO Depense (idPrevision, montantDepense) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
    
        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, this.idPrevision);
            preparedStatement.setInt(2, this.montantDepense);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
    
    public static Depense read(int id) throws SQLException {
        String sql = "SELECT * FROM Depense WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Depense Depense = null;

        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Depense = new Depense();
                Depense.setId(resultSet.getInt("id"));
                Depense.setIdPrevision(resultSet.getInt("idPrevision"));
                Depense.setMontantDepense(resultSet.getInt("montantDepense"));
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

        return Depense;
    }

    public static List<Depense> readAll() throws SQLException {
        String sql = "SELECT * FROM Depense";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Depense> Depenses = new ArrayList<>();

        try {
            connection = DbConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Depense Depense = new Depense();
                Depense.setId(resultSet.getInt("id"));
                Depense.setIdPrevision(resultSet.getInt("idPrevision"));
                Depense.setMontantDepense(resultSet.getInt("montantDepense"));
                Depenses.add(Depense);
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
        return Depenses;
    }

    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
