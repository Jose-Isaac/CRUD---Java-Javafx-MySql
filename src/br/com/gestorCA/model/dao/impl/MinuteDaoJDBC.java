package br.com.gestorCA.model.dao.impl;

import br.com.gestorCA.model.dao.MinuteDao;
import br.com.gestorCA.model.entities.Association;
import br.com.gestorCA.model.entities.Minute;
import br.com.gestorCA.model.entities.Partner;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinuteDaoJDBC implements MinuteDao {
    private Connection connection;

    public MinuteDaoJDBC(Connection connection) {this.connection = connection;}

    @Override
    public void insert(Minute minute) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO minute" +
                            " (PartnerId, AssociationId, MinuteText, Date) " +
                            "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, minute.getPartnerId().toString());
            preparedStatement.setString(2, minute.getAssociationId().toString());
            preparedStatement.setString(3, minute.getText());

            LocalDate minuteDate = minute.getDate();
            Date date = Date.valueOf(minuteDate.toString());
            preparedStatement.setDate(4, date);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    minute.setId(id);
                }

                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                throw new SQLException("Unexpected error! No rows affected!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<Minute> findByAssociation(int associationId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT minute.* FROM minute" +
                            " INNER JOIN association" +
                            " ON minute.AssociationId = association.Id" +
                            " WHERE AssociationId = ? ORDER BY Date");

            preparedStatement.setInt(1, associationId);

            resultSet = preparedStatement.executeQuery();

            List<Minute> list = new ArrayList<>();

            Map<Integer, Association> map = new HashMap<>();

            while (resultSet.next()) {
                Minute minute = instantiateMinute(resultSet);
                list.add(minute);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private Minute instantiateMinute(ResultSet resultSet) throws SQLException {
        Minute minute = new Minute();
        minute.setId(resultSet.getInt("Id"));
        minute.setPartnerId(resultSet.getInt("PartnerId"));
        minute.setAssociationId(resultSet.getInt("AssociationId"));
        minute.setText(resultSet.getString("MinuteText"));
        minute.setDate(resultSet.getDate("Date").toLocalDate());

        return minute;
    }
}
