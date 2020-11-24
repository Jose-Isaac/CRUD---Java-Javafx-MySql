package br.com.gestorCA.model.dao.impl;

import br.com.gestorCA.model.dao.AssociationDao;
import br.com.gestorCA.model.entities.Address;
import br.com.gestorCA.model.entities.Association;

import java.sql.*;
import java.time.LocalDate;

public class AssociationDaoJDBC implements AssociationDao {
    private Connection connection;

    public AssociationDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Association findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT association.*" +
                            " FROM association WHERE association.Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Association association = instantiateAssociation(resultSet);

                return association;
            }

            return null;
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

    @Override
    public Association findByCnpj(String cnpj) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT association.*" +
                            " FROM association WHERE association.Cnpj = ?");

            preparedStatement.setString(1, cnpj);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Association association = instantiateAssociation(resultSet);

                return association;
            }

            return null;
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

    @Override
    public void insert(Association association) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO association" +
                            " (Name, Cnpj, FundationDate, City, State, Zone, Locality, Complement, Cep, Number," +
                            " Communities) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, association.getName());
            preparedStatement.setString(2, association.getCnpj());

            LocalDate fundationDate = association.getFundationDate();
            preparedStatement.setDate(3, new Date(
                    fundationDate.getYear() + fundationDate.getDayOfMonth() + fundationDate.getMonth().getValue()
            ));

            preparedStatement.setString(4, association.getAddress().getCity());
            preparedStatement.setString(5, association.getAddress().getState());
            preparedStatement.setString(6, association.getAddress().getZone());
            preparedStatement.setString(7, association.getAddress().getLocality());
            preparedStatement.setString(8, association.getAddress().getComplement());
            preparedStatement.setString(9, Integer.toString(association.getAddress().getCepNumber()));
            preparedStatement.setString(10, Integer.toString(association.getAddress().getNumber()));
            preparedStatement.setString(11, association.getCommunities());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    association.setId(id);
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

    private Association instantiateAssociation(ResultSet resultSet) throws SQLException {
        Association association = new Association();

        association.setId(resultSet.getInt("Id"));
        association.setCnpj(resultSet.getString("Cnpj"));
        association.setName(resultSet.getString("Name"));
        association.setFundationDate(resultSet.getDate("FundationDate").toLocalDate());
        association.setCommunities(resultSet.getString("Communities"));

        association.setAddress(instantiateAddress(resultSet));

        return association;
    }

    private Address instantiateAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();

        address.setCity(resultSet.getString("City"));
        address.setState(resultSet.getString("State"));
        address.setZone(resultSet.getString("Zone"));
        address.setLocality(resultSet.getString("Locality"));
        address.setComplement(resultSet.getString("Complement"));
        address.setCepNumber(resultSet.getInt("Cep"));
        address.setNumber(resultSet.getInt("Number"));

        return address;
    }
}
