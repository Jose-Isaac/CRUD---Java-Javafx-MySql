package br.com.gestorCA.model.dao.impl;

import br.com.gestorCA.model.dao.DaoFactory;
import br.com.gestorCA.model.dao.PartnerDao;
import br.com.gestorCA.model.entities.Address;
import br.com.gestorCA.model.entities.Association;
import br.com.gestorCA.model.entities.Partner;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartnerDaoJDBC implements PartnerDao {

    private Connection connection;

    public PartnerDaoJDBC(Connection connection) { this.connection = connection;};

    @Override
    public void insert(Partner partner) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO partner" +
                            " (Name, FatherName, MotherName, Nationality, MaritalStatus, Nationalness, Cpf, Rg," +
                            " WorkCard, QuantifyChildren, VoteRegistration, ChildrensName, DateBirth, City, State," +
                            " Zone, Locality, Complement, Cep, RegistrationDate, Phone, Username, Password," +
                            " AssociationId, AccessLevel, Sex, Series, Schooling, Profession, LandTenure," +
                            " OutherAssociation, Syndicate, ActivityAddressLocality, ActivityAddressCity," +
                            " ActivityAddressUf, ActivityAddressCep, LandArea, SpouseId, SpouseName, SpouseCpf, Image) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                            " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, partner.getName());
            preparedStatement.setString(2, partner.getFatherName());
            preparedStatement.setString(3, partner.getMotherName());
            preparedStatement.setString(4, partner.getNationality());
            preparedStatement.setString(5,partner.getMaritalStatus());
            preparedStatement.setString(6, partner.getNaturalness());
            preparedStatement.setString(7, partner.getCpf());
            preparedStatement.setString(8, partner.getRg());
            preparedStatement.setInt(9, partner.getWorkCardNumber());
            preparedStatement.setInt(10, partner.getQuantifyChildren());
            preparedStatement.setString(11, partner.getVoterRegistration());
            preparedStatement.setString(12, partner.getChildrensName());

            LocalDate dateBirth = partner.getDateBirth();
            Date date = Date.valueOf(dateBirth.toString());
            preparedStatement.setDate(13, date);

            preparedStatement.setString(14, partner.getAddress().getCity());
            preparedStatement.setString(15, partner.getAddress().getState());
            preparedStatement.setString(16, partner.getAddress().getZone());
            preparedStatement.setString(17, partner.getAddress().getLocality());
            preparedStatement.setString(18, partner.getAddress().getComplement());
            preparedStatement.setInt(19, partner.getAddress().getCepNumber());


            LocalDate registrationDate = partner.getRegistrationDate();
            date = Date.valueOf(registrationDate.toString());
            preparedStatement.setDate(20, date);

            preparedStatement.setString(21, partner.getNumberPhone());
            preparedStatement.setString(22, partner.getUsername());
            preparedStatement.setString(23, partner.getPassword());
            preparedStatement.setInt(24, partner.getAssociation().getId());

            preparedStatement.setInt(25,partner.getAccessLevel());
            preparedStatement.setString(26, String.valueOf(partner.getSex()));
            preparedStatement.setString(27, partner.getSeries());
            preparedStatement.setString(28, partner.getSchooling());
            preparedStatement.setString(29, partner.getProfession());
            preparedStatement.setString(30, partner.getLandTenure());
            preparedStatement.setString(31, partner.getOuthersAssociations());
            preparedStatement.setString(32, partner.getSyndicate());
            preparedStatement.setString(33, partner.getActivityAddress().getLocality());
            preparedStatement.setString(34, partner.getActivityAddress().getCity());
            preparedStatement.setString(35, partner.getActivityAddress().getState());
            preparedStatement.setString(36, Integer.toString(partner.getActivityAddress().getCepNumber()));
            preparedStatement.setString(37, partner.getLandArea());

            if (partner.getSpouse() != null) {
                preparedStatement.setInt(38, partner.getSpouse().getId());
            } else {
                preparedStatement.setInt(38, 0);
                preparedStatement.setString(39, partner.getSpouseName());
                preparedStatement.setString(40, partner.getSpouseCpf());
            }

//            preparedStatement.setBinaryStream(41, partner.getImage(), partner.getImageLength());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    partner.setId(id);
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
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void update(Partner partner) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE partner" +
                            " SET Name = ?, FatherName = ?, MotherName = ?, Nationality = ?, MaritalStatus = ?," +
                            " Nationalness = ?, Cpf = ?, Rg = ?, WorkCard  = ?, QuantifyChildren = ?," +
                            " VoteRegistration  = ?, ChildrensName = ?, DateBirth = ?, City = ?, State = ?," +
                            " Zone = ?, Locality = ?, Complement = ?, Cep = ?, RegistrationDate = ?, Phone = ?," +
                            " Username = ?, Password = ?, AssociationId = ?, AccessLevel = ?, Sex = ?, Series = ?," +
                            " Schooling = ?, Profession = ?, LandTenure = ?, OutherAssociation = ?, Syndicate = ?," +
                            " ActivityAddressLocality = ?, ActivityAddressCity = ?, ActivityAddressUf = ?," +
                            " ActivityAddressCep = ?, LandArea = ?, SpouseId = ?, SpouseName = ?, SpouseCpf = ?," +
                            " Image = ?" +
                            " WHERE Id = ?");


            preparedStatement.setString(1, partner.getName());
            preparedStatement.setString(2, partner.getFatherName());
            preparedStatement.setString(3, partner.getMotherName());
            preparedStatement.setString(4, partner.getNationality());
            preparedStatement.setString(5,partner.getMaritalStatus());
            preparedStatement.setString(6, partner.getNaturalness());
            preparedStatement.setString(7, partner.getCpf());
            preparedStatement.setString(8, partner.getRg());
            preparedStatement.setInt(9, partner.getWorkCardNumber());
            preparedStatement.setInt(10, partner.getQuantifyChildren());
            preparedStatement.setString(11, partner.getVoterRegistration());
            preparedStatement.setString(12, partner.getChildrensName());

            LocalDate dateBirth = partner.getDateBirth();
            Date date = Date.valueOf(dateBirth.toString());
            preparedStatement.setDate(13, date);

            preparedStatement.setString(14, partner.getAddress().getCity());
            preparedStatement.setString(15, partner.getAddress().getState());
            preparedStatement.setString(16, partner.getAddress().getZone());
            preparedStatement.setString(17, partner.getAddress().getLocality());
            preparedStatement.setString(18, partner.getAddress().getComplement());
            preparedStatement.setInt(19, partner.getAddress().getCepNumber());

            LocalDate registrationDate = partner.getRegistrationDate();
            date = Date.valueOf(registrationDate.toString());
            preparedStatement.setDate(20, date);

            preparedStatement.setString(21, partner.getNumberPhone());
            preparedStatement.setString(22, partner.getUsername());
            preparedStatement.setString(23, partner.getPassword());
//            preparedStatement.setBlob(24, ); image
            preparedStatement.setInt(24, partner.getAssociation().getId());
            preparedStatement.setInt(25, partner.getAccessLevel());
            preparedStatement.setString(26, partner.getSex() + "");
            preparedStatement.setString(27, partner.getSeries());
            preparedStatement.setString(28, partner.getSchooling());
            preparedStatement.setString(29, partner.getProfession());
            preparedStatement.setString(30, partner.getLandTenure());
            preparedStatement.setString(31, partner.getOuthersAssociations());
            preparedStatement.setString(32, partner.getSyndicate());
            preparedStatement.setString(33, partner.getActivityAddress().getLocality());
            preparedStatement.setString(34, partner.getActivityAddress().getCity());
            preparedStatement.setString(35, partner.getActivityAddress().getState());
            preparedStatement.setString(36, Integer.toString(partner.getActivityAddress().getCepNumber()));
            preparedStatement.setString(37, partner.getLandArea());

            if (partner.getSpouse() != null) {
                preparedStatement.setInt(38, partner.getSpouse().getId());
            } else {
                preparedStatement.setInt(38, 0);
            }

            preparedStatement.setString(39, partner.getSpouseName());
            preparedStatement.setString(40, partner.getSpouseCpf());


            InputStream inputStream = partner.getImage();
            preparedStatement.setBinaryStream(41, partner.getImage(), partner.getImageLength());

            preparedStatement.setInt(42, partner.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

        @Override
    public void deleteById(int id) {
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement(
                        "DELETE FROM partner" +
                                " WHERE Id = ?");

                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
    }

    @Override
    public Partner findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT partner.*" +
                    " FROM partner INNER JOIN association" +
                    " ON partner.AssociationId = association.Id" +
                    " WHERE partner.Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Association association = instantiateAssociation(resultSet);

                Partner partner= instantiatePartner(resultSet, association);

                return partner;
            }

            return null;
        } catch (SQLException | IOException throwables) {
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
    public Partner findByUsername(String username) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT partner.*" +
                            " FROM partner INNER JOIN association" +
                            " ON partner.AssociationId = association.Id" +
                            " WHERE partner.Username = ?");

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Association association = instantiateAssociation(resultSet);

                Partner partner= instantiatePartner(resultSet, association);

                return partner;
            }

            return null;
        } catch (SQLException | IOException throwables) {
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
    public Partner findByCpf(String cpf) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT partner.*" +
                            " FROM partner INNER JOIN association" +
                            " ON partner.AssociationId = association.Id" +
                            " WHERE partner.Cpf = ?");

            preparedStatement.setString(1, cpf);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Association association = instantiateAssociation(resultSet);

                Partner partner= instantiatePartner(resultSet, association);

                return partner;
            }

            return null;
        } catch (SQLException | IOException throwables) {
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
    public List<Partner> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT partner.*" +
                            " FROM partner INNER JOIN association" +
                            " ON partner.Association.Id = association.Id" +
                            " ORDER BY Name"
            );

            resultSet = preparedStatement.executeQuery();

            List<Partner> list = new ArrayList<>();

            Map<Integer, Association> map = new HashMap<>();

            while (resultSet.next()) {

                Association association = map.get(resultSet.getInt("AssociationId"));

                if (association == null) {
                    association = instantiateAssociation(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), association);
                }

                Partner partner = instantiatePartner(resultSet, association);
                list.add(partner);
            }
            return list;
        } catch (SQLException | IOException throwables) {
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
    public List<Partner> findByAssociation(int associationId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT partner.*, association.Name as AssName" +
                            " FROM partner INNER JOIN association" +
                            " ON partner.AssociationId = association.Id" +
                            " WHERE AssociationId = ?" +
                            " ORDER BY Name");

            preparedStatement.setInt(1, associationId);

            resultSet = preparedStatement.executeQuery();

            List<Partner> list = new ArrayList<>();

            Map<Integer, Association> map = new HashMap<>();

            while (resultSet.next()) {

                Association ass = map.get(resultSet.getInt("AssociationId"));

                if (ass == null) {
                    ass = instantiateAssociation(resultSet);
                    map.put(resultSet.getInt("AssociationId"), ass);
                }

                Partner partner = instantiatePartner(resultSet, ass);
                list.add(partner);
            }
            return list;
        } catch (SQLException | IOException throwables) {
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

    private Association instantiateAssociation(ResultSet resultSet) throws SQLException {
        return DaoFactory.createAssociationDao().findById(resultSet.getInt("AssociationId"));
    }

    private Address instantiateActivityAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();

        address.setCity(resultSet.getString("ActivityAddressCity"));
        address.setLocality(resultSet.getString("ActivityAddressLocality"));
        address.setCepNumber(resultSet.getInt("ActivityAddressCep"));
        address.setState(resultSet.getString("ActivityAddressUf").toUpperCase());

        return address;
    }

    private Address instantiateAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();

        address.setCity(resultSet.getString("City"));
        address.setState(resultSet.getString("State").toUpperCase());
        address.setZone(resultSet.getString("Zone"));
        address.setLocality(resultSet.getString("Locality"));
        address.setComplement(resultSet.getString("Complement"));
        address.setCepNumber(resultSet.getInt("Cep"));

        return address;
    }

    private Partner instantiatePartner(ResultSet resultSet, Association association) throws SQLException, IOException {
        Partner partner = new Partner();

        partner.setId(resultSet.getInt("Id"));
        partner.setAccessLevel(resultSet.getInt("AccessLevel"));
        partner.setName(resultSet.getString("Name"));
        partner.setFatherName(resultSet.getString("FatherName"));
        partner.setMotherName(resultSet.getString("MotherName"));
        partner.setNationality(resultSet.getString("Nationality"));
        partner.setMaritalStatus(resultSet.getString("MaritalStatus"));
        partner.setNaturalness(resultSet.getString("Nationalness"));
        partner.setCpf(resultSet.getString("Cpf"));
        partner.setRg(resultSet.getString("Rg"));
        partner.setWorkCardNumber(resultSet.getInt("WorkCard"));
        partner.setQuantifyChildren(resultSet.getInt("QuantifyChildren"));
        partner.setVoterRegistration(resultSet.getString("VoteRegistration"));
        partner.setChildrensName(resultSet.getString("ChildrensName"));
        partner.setDateBirth(resultSet.getDate("DateBirth").toLocalDate());
        partner.setRegistrationDate(resultSet.getDate("RegistrationDate").toLocalDate());
        partner.setNumberPhone(resultSet.getString("Phone"));
        partner.setUsername(resultSet.getString("Username"));
        partner.setPassword(resultSet.getString("Password"));
        partner.setSex(resultSet.getString("Sex").charAt(0));
        partner.setSeries(resultSet.getString("Series"));
        partner.setSchooling(resultSet.getString("Schooling"));
        partner.setProfession(resultSet.getString("Profession"));
        partner.setLandTenure(resultSet.getString("LandTenure"));
        partner.setSyndicate(resultSet.getString("Syndicate"));
        partner.setLandArea(resultSet.getString("LandArea"));
        partner.setSex(resultSet.getString("Sex").charAt(0));
        partner.setSeries(resultSet.getString("Series"));
        partner.setSchooling(resultSet.getString("Schooling"));
        partner.setProfession(resultSet.getString("Profession"));
        partner.setLandTenure(resultSet.getString("LandTenure"));
        partner.setOuthersAssociations(resultSet.getString("OutherAssociation"));
        partner.setSyndicate(resultSet.getString("Syndicate"));
        partner.setLandArea(resultSet.getString("LandArea"));

        partner.setAssociation(association);

        partner.setAddress(instantiateAddress(resultSet));
        partner.setActivityAddress(instantiateActivityAddress(resultSet));

        Integer spouseId = resultSet.getInt("SpouseId");

        if (spouseId != 0) {
            partner.setSpouse(DaoFactory.createPartnerdao().findById(spouseId));
        } else {
            partner.setSpouseName(resultSet.getString("SpouseName"));
            partner.setSpouseCpf(resultSet.getString("SpouseCpf"));
        }

        partner.setImage(resultSet.getBinaryStream("Image"));

        return partner;
    }
}
