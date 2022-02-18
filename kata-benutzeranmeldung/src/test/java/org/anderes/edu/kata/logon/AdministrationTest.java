package org.anderes.edu.kata.logon;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class AdministrationTest {

    private Administration administration;
    
    @Before
    public void setup() {
        administration = new Administration();
    }
    
    @Test
    public void shouldBeAddRegistration() {
        // given
        final Registration registration = new Registration(24);
        registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        // when
        final boolean ok = administration.addRegistration(registration);
        // then
        assertThat(ok, is(true));
    }
    
    @Test
    public void shouldBeConfirmRegistration() {
        // given
        final Registration registration = new Registration(24);
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        administration.addRegistration(registration);
        // when
        boolean ok = administration.confirmRegistration(registernumber);
        // then
        assertThat(ok, is(true));
    }
    
    @Test
    public void shouldBeConfirmRegistrationNok() {
        // given
        final Registration registration = new Registration(24);
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        administration.addRegistration(registration);
        administration.confirmRegistration(registernumber);
        // when
        boolean ok = administration.confirmRegistration(registernumber);
        // then
        assertThat(ok, is(false));
    }
    
    @Test
    public void shouldBeGetUserByLogon() {
        // given
        final Registration registration = new Registration(24);
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        administration.addRegistration(registration);
        administration.confirmRegistration(registernumber);
        final Logon logon = new Logon(24);
        logon.login("bill", "password4bill");
        // when
        final Optional<User> user = administration.getUserByLogon(logon);
        // then
        assertThat(user, is(not(nullValue())));
        assertThat(user.isPresent(), is(true));
    }
    
    @Test
    public void shouldBeGetUserByEmail() {
        // given
        final Registration registration = new Registration(24);
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        administration.addRegistration(registration);
        administration.confirmRegistration(registernumber);
        // when
        final Optional<User> user = administration.getUserByEmail("bill.gates@microsoft.com");
        // then
        assertThat(user, is(not(nullValue())));
        assertThat(user.isPresent(), is(true));
    }
    
    @Test
    public void shouldBeGetUserByPasswordResetId() {
        // given
        final Registration registration = new Registration(24);
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        administration.addRegistration(registration);
        administration.confirmRegistration(registernumber);
        final Optional<User> user = administration.getUserByEmail("bill.gates@microsoft.com");
        String passwordResetId = user.orElseThrow(() -> new IllegalArgumentException()).getLogon().applyPasswordReset(24);
        // when
        final Optional<User> userByPassworResetId = administration.getUserByPasswordResetId(passwordResetId );
        // then
        assertThat(userByPassworResetId, is(not(nullValue())));
        assertThat(userByPassworResetId.isPresent(), is(true));
    }
}
