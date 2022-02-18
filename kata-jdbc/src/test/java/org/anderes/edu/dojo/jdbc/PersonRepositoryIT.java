package org.anderes.edu.dojo.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PersonRepositoryIT {

    private PersonRepository repository;
    
    @Before
    public void setup() {
        final String dbUrl = Paths.get("derbyEmbeddedDatabase").toAbsolutePath().toString();
        final Properties databaseProperties = new Properties();
        databaseProperties.setProperty("driver", "org.apache.derby.jdbc.EmbeddedDriver");
        databaseProperties.setProperty("url", "jdbc:derby:" + dbUrl);
        databaseProperties.setProperty("user", "APP");
        databaseProperties.setProperty("password", "APP");
        repository = new PersonRepository(databaseProperties);
    }
    
    @Test
    public void shouldBeFindPersonByPersonNo() throws RepositoryException {
        
        // when
        Optional<Person> optPerson = repository.findPersonByPersonNo(200);
        
        // then
        assertThat(optPerson.isPresent(), is(true));
        assertThat(optPerson.get().getPersonNo(), is(200));
        Optional<Address> optAddress = optPerson.get().getAddress("Hauptsitz");
        assertThat(optAddress.isPresent(), is(true));
        assertThat(optAddress.get().getAddressType(), is("Hauptsitz"));
        assertThat(optAddress.get().getStreet(), is("500 Fifth Avenue North"));
        assertThat(optAddress.get().getZipCode(), is(98102));
        assertThat(optAddress.get().getCity(), is("Seattle"));
    }
    
    @Test
    public void shouldBeNotFindPersonByPersonNo() throws RepositoryException {
        
        // when
        Optional<Person> optPerson = repository.findPersonByPersonNo(888888); 
        
        // then
        assertThat(optPerson.isPresent(), is(false));
    }
    
    @Test
    public void shouldBeFindAllLegalPerson() throws RepositoryException {
        
        // when
        Collection<LegalPerson> legalPersons = repository.findAllLegalPerson();
        
        // then
        assertThat(legalPersons, is(not(nullValue())));
        assertThat(legalPersons.size(), is(2));
        for (LegalPerson person : legalPersons) {
            assertThat(person.getName(), is(not(nullValue())));
            assertThat(person.getLegalForm(), is(not(nullValue())));
        }
    } 
    
    @Test
    public void shouldBeFindAllNaturalPerson() throws RepositoryException {
        
        // when
        final Collection<NaturalPerson> persons = repository.findAllNaturalPerson();
        
        // then
        assertThat(persons, is(not(nullValue())));
        assertThat(persons.size(), is(2));
        for (NaturalPerson person : persons) {
            assertThat(person.getFirstname(), is(not(nullValue())));
            assertThat(person.getLastname(), is(not(nullValue())));
        }
    }
}
