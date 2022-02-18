package org.anderes.edu.dojo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

public class PersonRepository {

    private Properties databaseProperties;

    public PersonRepository(final Properties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public Optional<Person> findPersonByPersonNo(int personNo) throws RepositoryException {
        try (Connection connection = createConnection()) {
            final String sql = "SELECT * FROM PERSON WHERE PERSONNO = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personNo);
            final ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            final Person person = mapResultSetToPerson(rs);
            final Collection<Address> addresses = findAddressByPersonId(connection, person.getId());
            addresses.forEach(address -> person.addAddress(address));
            return Optional.of(person);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        } 
    }

    private Collection<Address> findAddressByPersonId(final Connection connection, int id) throws SQLException {
        final String sql = "SELECT * FROM ADDRESS WHERE PERSONID = ?";
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        final ResultSet rs = statement.executeQuery();
        return mapResultSetToAddresses(rs);
    }

    private Collection<Address> mapResultSetToAddresses(final ResultSet resultSet) throws SQLException {
        final ArrayList<Address> addresses = new ArrayList<>();
        while (resultSet.next()) {
            final Address address = new Address();
            address.setAddressType(resultSet.getString("ADDRESSTYPE"));
            address.setStreet(resultSet.getString("STREET"));
            address.setZipCode(resultSet.getInt("ZIPCODE"));
            address.setCity(resultSet.getString("CITY"));
            addresses.add(address);
        }
        return addresses;
    }

    private Person mapResultSetToPerson(final ResultSet resultSet) throws SQLException {
        switch (resultSet.getString("PERSONTYPE")) {
        case "L":
            return mapToLegalPerson(resultSet);
        case "N":
            return mapToNaturalPerson(resultSet);
        default:
            throw new IllegalArgumentException("Ungültiges ResultSet");
        }
    }

    private Connection createConnection() throws SQLException {
        final String url = databaseProperties.getProperty("url");
        final String user = databaseProperties.getProperty("user");
        final String password = databaseProperties.getProperty("password");
        return DriverManager.getConnection(url, user, password);
    }

    public Collection<LegalPerson> findAllLegalPerson() throws RepositoryException {
        Collection<LegalPerson> persons = new ArrayList<>();
        try (Connection connection = createConnection()) {
            final String sql = "SELECT ID, PERSONNO, LEGALFORM, NAME FROM PERSON WHERE PERSONTYPE = 'L'";
            Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                final LegalPerson person = mapToLegalPerson(rs);
                final Collection<Address> addresses = findAddressByPersonId(connection, person.getId());
                addresses.forEach(address -> person.addAddress(address));
                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        } 
    }

    private LegalPerson mapToLegalPerson(final ResultSet resultSet) throws SQLException {
        final LegalPerson person = new LegalPerson();
        person.setId(resultSet.getInt("ID"));
        person.setPersonNo(resultSet.getInt("PERSONNO"));
        person.setLegalForm(resultSet.getString("LEGALFORM"));
        person.setName(resultSet.getString("NAME"));
        return person;
    }

    public Collection<NaturalPerson> findAllNaturalPerson() throws RepositoryException {
        Collection<NaturalPerson> persons = new ArrayList<>();
        try (Connection connection = createConnection()) {
            final String sql = "SELECT ID, PERSONNO, FIRSTNAME, LASTNAME FROM PERSON WHERE PERSONTYPE = 'N'";
            Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                final NaturalPerson person = mapToNaturalPerson(rs);
                final Collection<Address> addresses = findAddressByPersonId(connection, person.getId());
                addresses.forEach(address -> person.addAddress(address));
                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        } 
    }

    private NaturalPerson mapToNaturalPerson(final ResultSet resultSet) throws SQLException {
        final NaturalPerson person = new NaturalPerson();
        person.setId(resultSet.getInt("ID"));
        person.setPersonNo(resultSet.getInt("PERSONNO"));
        person.setFirstname(resultSet.getString("FIRSTNAME"));
        person.setLastname(resultSet.getString("LASTNAME"));
        return person;
    }

}
