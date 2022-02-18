package org.anderes.edu.kata.logon;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

public class RegistrationTest {

    
    @Test
    public void shouldBeRegister() {
        // given
        final Registration registration = new Registration(24);
        // when
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.of("bill"));
        // then
        assertThat(registernumber, is(not(nullValue())));
        assertThat(registernumber.length(), is(36));  
        assertThat(registration.getUser(), is(not(nullValue())));
        assertThat(registration.getUser().isConfirmed(), is(false));
    }
    
    @Test
    public void shouldBeRegisterWithoutPassword() {
        // given
        final Registration registration = new Registration(24);
        // when
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.empty(), Optional.of("bill"));
        // then
        assertThat(registernumber, is(not(nullValue())));
        assertThat(registernumber.length(), is(36));  
        assertThat(registration.getUser(), is(not(nullValue())));
        assertThat(registration.getUser().getEmail(), is("bill.gates@microsoft.com"));
        assertThat(registration.getUser().getPassword(), is(not(nullValue())));
        assertThat(registration.getUser().getPassword().length(), is(8));
        assertThat(registration.getUser().isConfirmed(), is(false));
    }
    
    @Test
    public void shouldBeConfirm() {
        // given
        final Registration registration = new Registration(24);
        final String registernumber = registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.empty());
        // when
        final User user = registration.confirmed(registernumber);
        // then
        assertThat(user, is(not(nullValue())));
        assertThat(user.getRegistrationDate(), is(LocalDate.now()));
        assertThat(user.isConfirmed(), is(true));
    }
    
    @Test
    public void shouldBeValid() {
        // given
        final Registration registration = new Registration(24);
        // when
        registration.register("bill.gates@microsoft.com", Optional.of("password4bill"), Optional.empty());
        // then
        assertThat(registration.isValid(), is(true));
        
    }
}
